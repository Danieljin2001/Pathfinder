package application;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//basically node
public class Tile extends StackPane{
	private static final int TILE_SIZE = 20;
	private int x,y;
	private String typeOfNode; 
	private boolean visited = false;
	private double distance = Double.POSITIVE_INFINITY;
	private Tile previousNode = null;
	
	private Rectangle border = new Rectangle(TILE_SIZE-2, TILE_SIZE-2);
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.typeOfNode = "empty";
		border.setStroke(Color.LIGHTGRAY);
		border.setFill(Color.WHITE);
		
		getChildren().addAll(border);
		
		setTranslateX(x * TILE_SIZE);
		setTranslateY(y * TILE_SIZE);
		setOnMouseClicked(e -> tileButtonAction());
		
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
	public void setPath() {
		if(!isStartNode() && !isEndNode())
			border.setFill(Color.YELLOW);
	}
	//to set that it has been visited
	public void setVisited() {
		visited = true;
		if(!isStartNode() && !isEndNode())
			border.setFill(Color.DARKTURQUOISE);
	}
	
	private void tileButtonAction() {
		if(GameState.isStartPointState()) {
			Board.setStartPoint(x, y);
		} 
		else if(GameState.isEndPointState()) {
			Board.setEndPoint(x, y);
		}
		else if (GameState.isWallState()) {
			Board.setWall(x, y);
		}
		
	}
	
	public void setPreviousNode(Tile node) {
		this.previousNode = node;
	}
	
	public void setStartNode() {
		this.typeOfNode = "start";
		border.setFill(Color.CHARTREUSE);
	}
	
	public void setEndNode() {
		this.typeOfNode = "end";
		border.setFill(Color.RED);
	}
	
	public void setWallNode() {
		this.typeOfNode = "wall";
		border.setFill(Color.DIMGRAY);
	}
	
	public void resetNode() {
		this.typeOfNode = "empty";
		this.visited = false;
		this.previousNode = null;
		this.distance = Double.POSITIVE_INFINITY;
		border.setFill(Color.WHITE);
	}
	
	public boolean isStartNode() {
		if(this.typeOfNode.equals("start")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isEndNode() {
		if(this.typeOfNode.equals("end")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isWallNode() {
		if(this.typeOfNode.equals("wall")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isEmptyNode() {
		if(this.typeOfNode.equals("empty")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public boolean isVisited() {
		return this.visited;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Tile getPreviousNode() {
		return this.previousNode;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")" + " " + typeOfNode;
	}

}
