// Matthew Thompson
// BoggleGUI contains everything for the Boggle game to run and to be 
// displayed using GUI

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application; 
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.effect.InnerShadow;
import javafx.util.Duration;

/**
   BoggleGUI class contains methods and logic to make the game 
   playable and for it to be displayed in a GUI
*/
public class BoggleGUI extends Application {
   // initializing all used objects and elements
   private Game game;
   private GridPane grid;
   private BorderPane mainPane;  
   private VBox leftPane;  
   private VBox rightPane;
   private HBox buttonPane; 
   private Text spacing;
   private Text status;
   private Text description;
   private Text points;
   private Text words;
   private Text pointsLabel;
   private Text wordsLabel;
   private Text statusLabel;
   private Image image;
   private Button endGame, newGame, testSelected;
   private InnerShadow innerShadow;
   private Integer timeSeconds;
   private Text timerLabel;
   private Text timer;
   private Timeline timeline;
   private final Integer START_TIME = 90;
   private final Integer SECONDS = 1;
   private final Integer END_TIME = 0;
   private final Integer TEN_REMAINING = 10;

   /**
      start method is called from main function, contains major game logic
      @param primaryStage Stage used to add GUI elements to
   */
   @Override
   public void start(Stage primaryStage) {
      primaryStage.setTitle("Boggle");
      
      mainPane = new BorderPane();
      
      // grid creation and settings
      grid = new GridPane();
      game = new Game();
      drawBoard();
      grid.setAlignment(Pos.CENTER);
      grid.setHgap(1);
      grid.setVgap(1);
      grid.setPadding(new Insets(10));
      mainPane.setCenter(grid);
      
      // image creation and setting
      image = new Image("file:BoggleTitle.jpeg");
      ImageView imageView = new ImageView(image);
      imageView.setFitWidth(210);
      imageView.setFitHeight(160);
      imageView.setPreserveRatio(true);
      
      // creating elements to go in leftPane
      description = new Text("Welcome to Boggle! Spell a word\nby selecting "+
      "horizontally, vertically,\nand diagonally adjacent letters,\nthen click "+
      "'Test Word' to earn\npoints based on your words'\nlength. "+
      "Earn as many points as\nyou can before the timer runs out!");
      
      status = new Text("Click 'New Game' to start!");
      
      statusLabel = new Text("Messages:");
      
      // creating leftPane, changing its' settings, and adding to mainPane
      leftPane = new VBox(9,imageView,description,statusLabel,status);
      leftPane.setAlignment(Pos.TOP_CENTER);
      leftPane.setPadding(new Insets(10));
               
      mainPane.setLeft(leftPane);
      
      // creates words list elements and rightPane, alters rightPane settings
      // and adds to mainPane
      words = new Text("");
      wordsLabel = new Text("     Word List:     ");
      
      rightPane = new VBox(10,wordsLabel,words);
      rightPane.setAlignment(Pos.TOP_CENTER);
      rightPane.setPadding(new Insets(10));      

      mainPane.setRight(rightPane);
    
      // creates elements going into buttonPane
      pointsLabel = new Text("Points:");     
      points = new Text("0");
      spacing = new Text("\t");
      timerLabel = new Text("\tSeconds Left:");
      timer = new Text("");
      timeSeconds = START_TIME;

      // update timer
      timer.setText(timeSeconds.toString());
      timeline = new Timeline();
      timeline.setCycleCount(Timeline.INDEFINITE);
      timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(SECONDS),new EventHandler<ActionEvent>() {
         // KeyFrame event handler
         public void handle(ActionEvent e) {
            timeSeconds--;
            // update timer
            if (timeSeconds >= TEN_REMAINING)
               timer.setText(timeSeconds.toString());
            else
               timer.setText("0"+timeSeconds.toString());
            if (timeSeconds <= END_TIME) {
               // closing message is displayed
               status.setText("  Thanks for playing!\nYou earned "+game.getPoints()+" points!");
               end();
            } 
         }
      }));
      
      // creates newGame button and fixes its' settings
      newGame = new Button("New Game");
      newGame.setStyle("-fx-font-size: 12pt; -fx-focus-color: transparent;"+
            "-fx-faint-focus-color: transparent; -fx-border-color: black");
      
      // sets how ActionEvent is handled when button pressed
      newGame.setOnAction(new EventHandler<ActionEvent>() {
         public void handle(ActionEvent e) {
            play();
         }
      });
      
      // creates testSelected button and fixes its' settings
      testSelected = new Button("Test Word");
      testSelected.setStyle("-fx-font-size: 12pt; -fx-focus-color: transparent;"+
            "-fx-faint-focus-color: transparent; -fx-border-color: black");
      
      // sets how ActionEvent is handled when button pressed
      testSelected.setOnAction(new EventHandler<ActionEvent>() {
         public void handle(ActionEvent e) {
            test();
         }
      }); 
      
      // creates endGame button and fixes its' settings
      endGame = new Button("End Game");
      endGame.setStyle("-fx-font-size: 12pt; -fx-focus-color: transparent;"+
            "-fx-faint-focus-color: transparent; -fx-border-color: black");
      
      // sets how ActionEvent is handled when button pressed
      endGame.setOnAction(new EventHandler<ActionEvent>() {
         public void handle(ActionEvent e) {
            Platform.exit();
         }
      }); 

      // creates buttonPane, adjusts its' settings, and adds it to mainPane
      buttonPane = new HBox(10,pointsLabel,points,timerLabel,timer,spacing,newGame,testSelected,endGame);
      buttonPane.setAlignment(Pos.CENTER);
      buttonPane.setPadding(new Insets(10));

      mainPane.setBottom(buttonPane);
      
      // sets game up for intro
      end();
      
      // complete setup
      Scene scene = new Scene(mainPane,700,420);
      primaryStage.setScene(scene);
      primaryStage.show();
   }
   
   
   /**
      handleClick method takes holds logic for what happens when a square is
      clicked on by the user
      @param e MouseEvent event triggered by mouseclick
   */
   // event handler for user clicking on a square
   public void handleClick(MouseEvent e) {  
      TilePane tp = (TilePane)(e.getSource());
      int row = tp.getRow();
      int column = tp.getCol();
      
      // if it is a valid pick, it will be added to the selected list and 
      // made visible that it is selected
      if (game.isValidSelection(row,column) && 
      !game.getSelectedTiles().contains(game.getTile(row,column))) {
         status.setText("");
         game.addToSelected(row,column);
         
         tp.setStyle("-fx-background-color: lightgrey;");
         
         // create an InnerShadow
         innerShadow = new InnerShadow();
         innerShadow.setOffsetX(5);
         innerShadow.setOffsetY(5);
         tp.setEffect(innerShadow);
      }
      
      // if the user is deselecting the tile it will be removed from selected
      // list and all styles will be reverted
      else if (game.getSelectedTiles().contains(game.getTile(row,column)) && 
      game.getTile(row,column).equals(game.newestSelected())) {
         status.setText("");
         game.removeFromSelected(row,column);
         tp.setEffect(null);
         tp.setStyle("-fx-border-color: blue;"+
            "-fx-border-width: 1;");
      }
      
      // if the user tried to deselect a tile that wasn't the most recently
      // selected
      else if (game.getSelectedTiles().contains(game.getTile(row,column)))
         status.setText("You may only deselect your most\nrecently selected tile.");
         
      // user selected non-adjacent tile
      else
         status.setText("You must select an adjacent tile.");        
   }   
   
   
   /**
      drawBoard method creates visual Boggle board using GUI elements
   */
   // using information from game, create tile panes
   public void drawBoard() {
      grid.getChildren().clear(); // clear the board 
      for (int r=0;r<4;r++)
         for (int c=0;c<4;c++) {
            final int row = r;
            final int column = c; 
            
            // creates individual tiles for grid and sets how they handle a mouse click
            TilePane tp = new TilePane(game.getTileLetter(row,column),row,column);
            tp.setOnMouseClicked(this::handleClick);
            grid.add(tp,row,column);
         }         
   }
   
   
   /**
      test method contains logic for testSelected button when pressed
   */
   public void test() {
      // resets effect and style for selected tiles
      for (Node t : grid.getChildren()) {
         t.setEffect(null);
         t.setStyle("-fx-border-color: blue;"+
                   "-fx-border-width: 1;");
      }
      
      // if word is valid add to points and word list, else update status 
      if (game.testSelected()) {
         points.setText(game.getPoints());
          
         words.setText("");
         words.setText(game.getWords()+"\n");
      }
      else
         status.setText("Your word was invalid.");
   }

   
   /**
      end method contains logic to be executed when the timer runs out
   */
   public void end() {
      // most of the board becomes grey to signal the game has ended
      grid.setStyle("-fx-background-color: grey");
      rightPane.setStyle("-fx-border-color: blue; -fx-border-width: 1;"+
         "-fx-font-family: dialog; -fx-font-size: 14pt; -fx-background-color: grey");
      leftPane.setStyle("-fx-border-color: blue; -fx-border-width: 1;"+
            "-fx-font-family: dialog; -fx-font-size: 12pt; -fx-background-color: white");
      buttonPane.setStyle("-fx-background-color: white; -fx-font-family: dialog;"+
         "-fx-font-size: 14pt");
      for (Node t : grid.getChildren()) {
         t.setEffect(null);
         t.setStyle("-fx-border-color: blue; -fx-border-width: 1;");
      }
      
      // all squares and testSelected button lose functionality
      for (Node pane: grid.getChildren()) {
         pane.setOnMouseClicked(null);
      }
      testSelected.setOnAction(null);
      
      // timer stops
      timeline.stop();
   }
   
   
   /**
      play method contains logic to make game playable
   */
   public void play() {
      // resets game
      game = new Game();
      drawBoard();
      status.setText("");
      points.setText("0");
      words.setText("");
      
      // game style changed to signal beginning of game
      grid.setStyle("-fx-background-color: white");
      rightPane.setStyle("-fx-border-color: blue; -fx-border-width: 1;"+
         "-fx-font-family: dialog; -fx-font-size: 14pt; -fx-background-color: white");
      for (Node t : grid.getChildren()) {
         t.setEffect(null);
         t.setStyle("-fx-border-color: blue; -fx-border-width: 1;");
      }
      
      // testSelected button gains functionality
      testSelected.setOnAction(new EventHandler<ActionEvent>() {
         public void handle(ActionEvent e) {
            test();
         }
      });
      
      // timer resets and starts
      timer.setText("");
      timeSeconds = START_TIME;
      timer.setText(timeSeconds.toString());
      timeline.playFromStart();
   }
   
   
   /**
      main method it run automatically, starts the start method
   */
   public static void main(String[] args) {
      launch(args);
   }

}