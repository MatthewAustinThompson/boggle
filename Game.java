// Matthew Thompson
// Game contains all methods required for Boggle game to run

import java.util.*;

/**
   Game class contains methods for manipulating the play of the game
*/
public class Game {
   // creates variables and instances of objects 
   private ArrayList<Tile> selected = new ArrayList<Tile>();
   private Board gameBoard = new Board();
   private Dictionary dict = new Dictionary("dictionary.txt");
   private Word word = new Word(selected);
   private int points = 0;
   private ArrayList<String> wordList = new ArrayList<String>();
   private boolean gameOver = false;
   
   
   /**
      isValidSelection() method takes row and column integers and determines 
      whether or not the given parameters are valid
      @param r int containing row of chosen tile
      @param c int containing column of chosen tile
      @return boolean true if valid, false otherwise
   */
   public boolean isValidSelection(int r, int c) {
      // if the list of selected tiles is empty then it must be valid
      if (selected.isEmpty())
         return true;
      
      // creates copy Tile of tile most recently added to selected list
      Tile copy = selected.get(selected.size()-1);
      
      // using if-else if-else statements determines whether or not the
      // chosen tile is adjacent to the previously selected tile
      if (copy.row == r-1 && copy.column == c-1)
         return true;
      else if (copy.row == r-1 && copy.column == c)
         return true;
      else if (copy.row == r-1 && copy.column == c+1)
         return true;
      else if (copy.row == r && copy.column == c-1)
         return true;
      else if (copy.row == r && copy.column == c+1)
         return true;
      else if (copy.row == r+1 && copy.column == c-1)
         return true;
      else if (copy.row == r+1 && copy.column == c)
         return true;
      else if (copy.row == r+1 && copy.column == c+1)
         return true;
      else
         return false; 
   }


   /**
      getTile() method takes row and column parameters, uses those values
      to determine the referenced tile object, creates a copy of it and
      returns it
      @param r int containing row of chosen tile
      @param c int containing column of chosen tile
      @return copy Tile is an exact copy of referenced tile
   */
   public Tile getTile(int r, int c) {
      Tile copy = gameBoard.getBoard().get(r).get(c);
      
      return copy;
   }
   
   
   /**
      getTile() method takes row and column parameters, uses those values
      to determine the referenced tile object, creates a copy of it and
      returns it
      @param r int containing row of chosen tile
      @param c int containing column of chosen tile
      @return copy Tile is an exact copy of referenced tile
   */
   public String getTileLetter(int r, int c) {
      Tile copy = gameBoard.getBoard().get(r).get(c);
      String letter = copy.toString();
      return letter;
   }


   /**
      getSelectedTiles() method returns the ArrayList<Tile> containing the
      selected tiles
      @return selected ArrayList<Tile> holds selected tiles
   */
   public ArrayList<Tile> getSelectedTiles() {
      return selected;
   }


   /**
      addToSelected() method takes row and column integers as parameters, uses
      them to create a copy of the tile object they are referencing, and adds 
      that object to the list of selected tiles
      @param r int containing row of chosen tile
      @param c int containing column of chosen tile
   */
   public void addToSelected(int r, int c) {
      Tile copy = gameBoard.getBoard().get(r).get(c);
      selected.add(copy);
   }
   
   
   /**
      removeFromSelected() method takes row and column integers as parameters, uses
      them to create a copy of the tile object they are referencing, and removes it
      from the selected list if it contains the object
      @param r int containing row of chosen tile
      @param c int containing column of chosen tile
   */
   public void removeFromSelected(int r, int c) {
      Tile copy = gameBoard.getBoard().get(r).get(c);
      
      // if statement that determines if the tile is in the selected list, and
      // if it is, removes it
      if (selected.contains(copy))
         selected.remove(copy);
   }
   
   
   /**
      clearSelected() method deletes everything within the selected list
   */
   public void clearSelected() {
      selected.clear();
   }

   
   /**
      testSelected() method determines if the selected tiles create a valid word,
      and if it is adds it to the word list, adds the corresponding points to the points 
      total, and clears the selected list  
      @return boolean true if tested successful, false otherwise 
   */
   public boolean testSelected() {
      Word word = new Word(selected);
      
      // if statement that determines if the selected word is valid, and if it
      // is adds it to the word list, adds the corresponding points to the points 
      // total, and clears the selected list
      if (dict.isValidWord(selected) && !wordList.contains(word.getWord())) {
         wordList.add(word.getWord());
         points += word.getPoints();
         selected.clear();
         return true;
      }
      else
         selected.clear();
         return false;
   } 
   
   
   /**
      getPoints method returns the value stored in the variable points
      @return points int holds value of points earned by player
   */
   public String getPoints() {
      String pointsString = ""+points;
      return pointsString;
   }
     
      
   /**
      getWords method returns value of wordsList in string form for adding
      to the players list of words
      @return wordsString String holding value of wordsList in String form
   */
   public String getWords() {
      String wordsString = "";
      for (int i=0;i<wordList.size();i++) {
         wordsString += wordList.get(i);
         wordsString += "\n";
      }
      return wordsString;
   }
   
   
   /**
      newestSelected method returns the Tile most recently added to the 
      selected list
      @return newest Tile most recently added used for determining
      deselect eligibility
   */
   public Tile newestSelected() {
      Tile newest = selected.get(selected.size()-1);
      return newest;
   }
   
   
   /**
      toString() saves string of instance of Board object and returns the 
      board as a string
      @return board String that contains the game board 
   */
   @Override
   public String toString() {
      // initializes variable and creates instance of Board object
      String board;
      
      // save String of board to board variable
      board = gameBoard.toString();
      board += ("\n\nselected: " + selected + "\n\n" +
      "words: " + wordList + "\n\n" +
      "points: " + points + "\n");
      
      return board;
   }
   
}