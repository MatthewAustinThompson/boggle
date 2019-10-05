// Matthew Thompson
// Board contains all methods related to creating the Boggle board

import java.util.*;

/**
   Board class creates empty board, fills rows with random letters, and then adds rows to the board
*/
public class Board { 
   // initialize objects 
   private ArrayList<Tile> row;
   ArrayList<ArrayList<Tile>> board;
   
   /**
      Default constructor fills rows with random letters and then adds rows to the board
   */
   public Board() {
      // creates empty board
      this.board = new ArrayList<ArrayList<Tile>>();
      
      // creates die and assigns letters for picking letters 
      String[] die0 = {"R","I","F","O","B","X"};
      String[] die1 = {"I","F","E","H","E","Y"};
      String[] die2 = {"D","E","N","O","W","S"};
      String[] die3 = {"U","T","O","K","N","D"};
      String[] die4 = {"H","M","S","R","A","O"};
      String[] die5 = {"L","U","P","E","T","S"};
      String[] die6 = {"A","C","I","T","O","A"};
      String[] die7 = {"Y","L","G","K","U","E"};
      String[] die8 = {"Qu","B","M","J","O","A"};
      String[] die9 = {"I","H","I","S","P","N"};
      String[] die10 = {"V","E","T","I","G","N"};
      String[] die11 = {"B","A","L","I","Y","T"};
      String[] die12 = {"E","Z","A","V","N","D"};
      String[] die13 = {"R","A","L","E","S","C"};
      String[] die14 = {"U","W","I","L","R","G"};
      String[] die15 = {"P","A","C","E","M","D"};
      
      // creates die list of lists and adds all die lists to a it      
      String[][] die = {die0,die1,die2,die3,die4,die5,die6,die7,die8,die9,
      die10,die11,die12,die13,die14,die15};
      
      // creates random objects
      Random randomDie = new Random();
      Random randomSide = new Random();
      
      // initializes variables and objects
      int dieNumber;
      int dieSide;
      Tile tilePlace;
      final int ROW_SIZE = 4;
      final int COLUMN_SIZE = 4;
      final int NEW_ROW = 0;
      
      // for loop for filling all rows of board
      for (int j=0;j<COLUMN_SIZE;j++) {
      this.row = new ArrayList<Tile>();
         for (int i=0;i<ROW_SIZE;i++) {
            dieNumber = randomDie.nextInt(die.length);
            ArrayList<String> selectDie = new ArrayList<String>();
            selectDie.addAll(Arrays.asList(die[dieNumber]));
            dieSide = randomSide.nextInt(selectDie.size());
            tilePlace = new Tile(selectDie.get(dieSide),j,i);
            row.add(tilePlace); 
         }    
         board.add(row);
      } 
   }
      
   
   /**
      toString() method turns board into a String and returns it
      @return boardOutput String stores Boggle board
   */
   @Override
   public String toString() {
      String boardOutput = "";
      final int COLUMN_SIZE = 4;
      
      // for each Tile in each row add it to boardOutput
      for (int i=0;i<COLUMN_SIZE;i++) {
         for (Tile t : board.get(i)) {
            boardOutput += "\t";
            boardOutput += t; 
         }
         boardOutput += "\n\n";
      } 
      boardOutput = boardOutput.toUpperCase();  
           
      return boardOutput;
   }
   
   
   /**
      getBoard() method returns the contents of ArrayList<ArrayList<Tile>> board,
      which is used to create the game board
      @return board ArrayList<ArrayList<Tile>> containing rows of tile objects
   */
   public ArrayList<ArrayList<Tile>> getBoard() {
      return board;
   }
}
