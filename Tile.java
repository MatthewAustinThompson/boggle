// Matthew Thompson
// Tile class is used to create individual tiles for Boggle

/**
   Tile class represents one tile on the board
   @param letter String that holds letter on tile
   @param row int that holds tiles row
   @param column int that holds tiles column
   @param selected boolean that is true if tile selected, false if not
*/
public class Tile {
   // initiliazes variables
   int row;
   int column;
   boolean selected = false;
   String letter = "";
   
   
   /**
      Default constructor saves tiles letter and location
      @param l char that holds letter on tile
      @param r int that holds tiles row
      @param c int that holds tiles column
   */
   public Tile(char l, int r, int c) {
      // initiliazes variables
      letter += l;
      row = r;
      column = c;
      
      // converts letter string to upper case
      letter = letter.toUpperCase();
   }
   
   
   /**
      Constructor saves tiles letter and location
      @param l String that holds letter on tile
      @param r int that holds tiles row
      @param c int that holds tiles column
   */
   public Tile(String l, int r, int c) {
      // initiliazes variables
      letter += l;
      row = r;
      column = c;
      
      // converts letter string to upper case 
      letter = letter.toUpperCase();           
   }
   
   
   /**
      Copy constructor saves parameters of input object to new object
      @param t is a Tile object
   */
   public Tile(Tile t) {
      // initiliazes variables
      letter = t.letter;
      row = t.row;
      column = t.column;
      selected = t.selected;
   }
   
   
   /**
      setSelected() method is used to change the tile's selected boolean variable value 
      to true
   */
   public void setSelected() {
      selected = true;
   }
   
   
   /**
      toString method converts existing Tile object into a string and returns it
      @return letter String that contains Tile's letter string
   */
   @Override
   public String toString() { 
      return letter; 
   }
   
   
   /**
      equals() method used to compare two Tile objects and determines whether or not they are equal
      @param t Object representing Tile used to determine equality
      @return boolean true if equal, false otherwise
   */
   @Override
   public boolean equals(Object t) {
      if (t == null) 
         return false;

      Tile other = (Tile)t;
      
      if (getClass() != other.getClass())
         return false;
      if (this.letter.equals(other.letter) && this.row == other.row && this.column == other.column) 
         return true;
      else
         return false;
   }
   
}