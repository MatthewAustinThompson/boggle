// Matthew Thompson 
// TilePane extends HBox and is used to create individual tiles 
// that will go in each on of the grids squares

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.text.Font;

/**   
   TilePane class initializes key tile parameters and creates a
   tile to be used on the grid
*/
public class TilePane extends HBox {
   private int row; 
   private int col;
   private Text letter;
   
   
   /**
      TilePane method takes parameters and uses them to create
      tile for grid
      @param l String holds value of letter of tile
      @param r int holds value of row number
      @param c int holds value of column number
   */
   public TilePane(String l, int r, int c) {
      this.setStyle("-fx-border-color: blue;"
               +"-fx-border-width: 1;");
      this.setPrefSize(60,60);
      row = r;
      col = c;
      setTile(l);
   }
   
   
   /**
      getRow method returns row value of tile
      @return row int holds value of row number for tile
   */
   public int getRow() {
      return row;
   }
   
   
   /**
      getColumn method returns column value of tile
      @return col int holds value of column number for tile
   */
   public int getCol() {
      return col;
   }
   
   
   /**
      setTile method takes letter as parameter and displays it
      on front of tile
      @param l String letter to be displayed on tile in the grid
   */
   public void setTile(String l) {
      letter = new Text(l);
      letter.setFont(Font.font("Dialog",18));
      this.setAlignment(Pos.CENTER);
      this.getChildren().add(letter);
   }
   
}

