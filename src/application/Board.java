package application;

import java.util.ArrayList;

public class Board {
	
	public static Tile[][] grid;
	public static ArrayList<Tile> allNodes;
	
	private static int X_TILES, Y_TILES;
	
	private static Tile startNode = null;
	private static Tile endNode = null;
	
	//create the board
	public Board(int X_TILES,int Y_TILES) {
		Board.grid = new Tile[X_TILES][Y_TILES];
		Board.X_TILES = X_TILES;
		Board.Y_TILES = Y_TILES;
		
		for(int y = 0; y < Y_TILES; y++) {
			for(int x = 0; x < X_TILES; x++) {
				Tile tile = new Tile(x,y);
				grid[x][y] = tile;
			}
		}
		
		allNodes = new ArrayList<Tile>();
		for(int y = 0; y < Y_TILES; y++) {
			for(int x = 0; x < X_TILES; x++) {
				allNodes.add(grid[x][y]);
			}
		}
		
		
	}
	
	public static void setStartPoint(int setX, int setY) {
		if(grid[setX][setY].isEmptyNode()) {
			for(int y = 0; y < Y_TILES; y++) {
				for(int x = 0; x < X_TILES; x++) {
					if(grid[x][y].isStartNode()) {
						grid[x][y].resetNode();
					}
				}
			}
			grid[setX][setY].setStartNode();
			startNode = grid[setX][setY];
		}
	}
	
	public static void setEndPoint(int setX, int setY) {
		if(grid[setX][setY].isEmptyNode()) {
			for(int y = 0; y < Y_TILES; y++) {
				for(int x = 0; x < X_TILES; x++) {
					if(grid[x][y].isEndNode()) {
						grid[x][y].resetNode();
					}
				}
			}
			grid[setX][setY].setEndNode();
			endNode = grid[setX][setY];
		}
	}
	
	public static void setWall(int setX, int setY) {
		if(grid[setX][setY].isEmptyNode()) {
			grid[setX][setY].setWallNode();
		}
	}
	
	public static Tile getStartNode() {
		return startNode;
	}
	
	public static Tile getEndNode() {
		return endNode;
	}

	public static void reset() {
		for(int y = 0; y < Y_TILES; y++) {
			for(int x = 0; x < X_TILES; x++) {
				grid[x][y].resetNode();	
			}
		}
		
		allNodes = new ArrayList<Tile>();
		for(int y = 0; y < Y_TILES; y++) {
			for(int x = 0; x < X_TILES; x++) {
				allNodes.add(grid[x][y]);
			}
		}
	}
}
