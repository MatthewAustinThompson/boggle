// Matthew Thompson
// Dictionary class is used to put words from the dictionary into an ArrayList, and has a method to
// test if a word is valid 

import java.io.*;
import java.util.*;

/**
   Dictionary class takes contents of a file and adds it to an arraylist. It also contains a method for checking 
   if a user entered word is valid
*/
public class Dictionary {
   // creates ArrayList object
   private ArrayList<String> dictionary = new ArrayList<String>();
   
   
   /**
      default constructor takes the filename provided, opens the file, and reads it's contents into a dictionary
      @param f String that stores file name
   */
   public Dictionary(String f) {
      // tries to import file and store contents to dictionary, prints error if fails
      try {
         // initializes variable
         String filename = f;
         
         // creates file and scanner objects
         File file = new File(filename);
         Scanner inputFile = new Scanner(file);   
         
         // while loop that reads and stores each string from the infile in dictionary 
         while (inputFile.hasNext()) {
            String line = inputFile.nextLine();
            dictionary.add(line);
         }   
      }
      
      // catches IOException if it occurs
      catch (IOException e) {
         System.out.println("An error has occurred with importing the file");
      }
   }
   
   
   /**
      isValidWord() method takes in an arraylist<tile> and determines if the word formed appears in the dictionary
      @param tiles ArrayList<Tile> holds tile objects that contain user specified letters, row, and columns
      @return valid boolean true if valid, false otherwise
   */
   public boolean isValidWord(ArrayList<Tile> tiles) {
      // inititialize variables and objects
      String wordCheck = "";
      boolean valid = false;
      Tile copy;
   
      // for loop that saves letter variable from each object in list to wordCheck
      for (int i=0;i<tiles.size();i++) {
         copy = new Tile(tiles.get(i));
         wordCheck += copy.letter.toLowerCase(); 
      }
      
      // for loop that checks wordCheck against each string in the dictionary
      for (int i=0;i<dictionary.size() && valid==false;i++) {
         if (dictionary.get(i).equals(wordCheck)) 
            valid = true;   
      }
      return valid; 
   }
   
}