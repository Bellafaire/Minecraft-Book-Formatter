package minecraft.book.formatter;

import java.util.ArrayList;
import javax.swing.JFrame;

public class BookFormatter {

    public static void main(String[] args) {
        //initiate the GUI created in GUI.java
        GUI gui = new GUI();
        gui.setTitle("Book Formatter"); //give title
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setLocationRelativeTo(null); //set location to the middle of the screen, purelly astetic
        gui.setVisible(true); //make the GUI visible
    }

    public static String[] Format(String main) {
       
        //Minecraft books don't support the TAB charater, so before we split the string we'll replace all the tab charaters with three spaces
        String formatedString = main.replace("	", "   ");
        
        //now split the string into an array of words to be placed into the book
        String[] splitString = formatedString.split(" "); 
        
        //Array list which will have the words deposited into it so that they can be placed into the book properly, each index will represent a seperate page in the book
        ArrayList<String> outputArray = new ArrayList<>();
        
        //because the code following this line relies on removing the
        //index then replacing it with new data there has to be at least one bit of data in the 
        //array to prvent OutOfBoundsExceptions from being thrown
        outputArray.add(" ");

        //variables for later use
        int counter = 0;    //this variable will be incremented each time a word is added to the output array, this way we can keep track of what word needs to be added next
        String temp = "";   //variable used to hold the string data from the list, at each index for later concatenate.
        int length = 0;     //variable to store the length of the array at the end of each loop itteration 

        //for loop will do a pass for each page in the minecraft book
        for (int i = 0; i < 50; i++) {
            
            length = 0;              //because we're on a new page we need to set the length of the page back to zero
            outputArray.add("");     //add another string to start the page, this will be removed and replaced in each iteration of the loop
            
            while (length < 240 && counter < splitString.length) {

                temp = outputArray.get(i);                                           //store the data from the page we're working on currently
                outputArray.remove(i);                                               //remove the page as we have it from the array list
                outputArray.add(i, temp.concat(splitString[counter].concat(" ")));   //concatenate the stored data and the next word, then add a space (because the .split method removed the spaces
                counter++;                                                           //add to the counter to ensure the next itteration does not add the same word to the page twice

                length = outputArray.get(i).length();                                //an exception will be thrown if we try to reference the .get().length method directly in the condition of the while loop
            
            }
        }
        
        //convert the array list to a String array and pass it out of the method
        String[] array = outputArray.toArray(new String[outputArray.size()]);        
        return array;
    }
}
