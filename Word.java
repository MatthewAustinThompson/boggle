// Matthew Thompson
// Word class is used to store the player selected word and give it a point value

import java.util.*;

/**
   Word class represents a word entered by the player, stores word and points it's worth 
*/
public class Word {
   // initiliazes variable and creates object
   String word = "";
   int points;
   
   
   /**
      Default constructor takes ArrayList<Tile> and concatonates each Tile's letter to word String
      @param tiles ArrayList<Tile> stores Tile objects 
   */
   public Word(ArrayList<Tile> tiles) {
      for (int i=0;i<tiles.size();i++) {
         word += tiles.get(i).letter; 
      } 
   }
   
   
   /**
      getPoints() method returns points associated with word
      @return points int that stores value of points for the word
   */
   public int getPoints() {
      // switch statement for determining word points
      switch (word.length()) {
         case 0:  points = 0;
                  break;
         case 1:  points = 0;
                  break;
         case 2:  points = 1;
                  break;
         case 3:  points = 2; 
                  break;
         case 4:  points = 4; 
                  break;
         case 5:  points = 6;
                  break;
         case 6:  points = 9;
                  break;
         case 7:  points = 15;
                  break;
         default: points = 11;
                  break;  
      }
        
      return points;
   }
   
   
   /**
      getWord() method returns word
      @return word String that contains the users word
   */
   public String getWord() {
      return word;
   }
   
   
   /**
      toString() method returns word
      @return word String that contains the users word
   */
   @Override
   public String toString() {
      return word;
   
   }
   
   
   /** 
      equals() method concatonates each Tile's letter with ArrayList<Tile> to the word variable,
      determines whether it's equal to this.word, returns true if it is, false otherwise
      @return boolean true if equal, false otherwise
      @Override
   */
   public boolean equals(ArrayList<Tile> tiles) {
      // initializes variable 
      String word = "";
      
      // for loop takes ArrayList<Tile> and concatonates each Tile's letter to word String
      for (int i=0;i<tiles.size();i++) {
         word += tiles.get(i).letter; 
      } 
      
      // if statement that determines whether words are equal, returns boolean value
      if (this.word.equals(word)) 
         return true;
      else
         return false;
   }
   
}