package application;
	
import java.util.ArrayList;

import algorithm.Dijkstra;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	
	
	private static final int GRID_WIDTH = 800;
	private static final int GRID_HEIGHT = 800;
	
	private static final int TILE_SIZE = 20;
	private static final int X_TILES = GRID_WIDTH/ TILE_SIZE;
	private static final int Y_TILES = GRID_HEIGHT/ TILE_SIZE;
	
	
	private Parent createGridGUI() {
		Pane gridRoot = new Pane();
		gridRoot.setPrefSize(WIDTH, HEIGHT);
		
		for(int y = 0; y < Y_TILES; y++) {
			for(int x = 0; x < X_TILES; x++) {
				gridRoot.getChildren().add(Board.grid[x][y]);
			}
		}
		
		return gridRoot;
	}
	
	private Parent createSideButtons() {

		VBox vBox = new VBox();
		ToggleGroup buttonGroup = new ToggleGroup();
		
		//create buttons
		ToggleButton startNodeButton = new ToggleButton(" Add Start Point");
		ToggleButton wallNodeButton = new ToggleButton("Add Wall(s)");
		ToggleButton endNodeButton = new ToggleButton("Add End Point");
		
		// button styling
		startNodeButton.setPrefWidth(110);
		wallNodeButton.setPrefWidth(110);
		endNodeButton.setPrefWidth(110);
		
		//add into toggle group
		startNodeButton.setToggleGroup(buttonGroup);
		wallNodeButton.setToggleGroup(buttonGroup);
		endNodeButton.setToggleGroup(buttonGroup);
		
		Button startButton = new Button("START");
		Button resetButton = new Button("RESET");
		startButton.setPrefWidth(110);
		resetButton.setPrefWidth(110);
		
		//group styling
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.relocate(850,300);
		
		//setup the buttons
		startNodeButton.setSelected(true);
		GameState.startPointState(); //default start with startPointState
		
		startNodeButton.setOnAction(e -> GameState.startPointState());		
		endNodeButton.setOnAction(e -> GameState.endPointState());
		wallNodeButton.setOnAction(e -> GameState.wallState());
		startButton.setOnAction(e -> startButtonAction());
		resetButton.setOnAction(e -> resetButtonAction());
		
		
		
		vBox.getChildren().addAll(startNodeButton, wallNodeButton, endNodeButton, startButton, resetButton);	
		
		return vBox;
		
	}
	
	private void resetButtonAction() {
		Board.reset();
	}
	
	private void startButtonAction() {
		Dijkstra dij = new Dijkstra();
		dij.start();
		
		ArrayList<Tile> shortestPath = dij.getNodesInShortestPathOrder(Board.getEndNode());
		for (Tile i : shortestPath) {
			i.setPath();
		}
		
	}
	
	
	
	
	private Parent createContent() {
		Pane mainRoot = new Pane();
		mainRoot.setPrefSize(1000, 800);
		
		
		Parent gridRoot = createGridGUI();
		Parent vBox = createSideButtons();

		mainRoot.getChildren().addAll(vBox, gridRoot);
		
		return mainRoot;
	}
	
	
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(createContent());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) {
		new Board(X_TILES, Y_TILES);
		launch(args);
	}
}
