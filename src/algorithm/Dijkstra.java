package algorithm;

import java.util.ArrayList;

import application.Board;
import application.Tile;

public class Dijkstra {
	private ArrayList<Tile> visitedNodesInOrder = new ArrayList<Tile>();
	//ArrayList<Tile> unvisitedNodes = Board.allNodes;
	
	public Dijkstra() {
		Board.getStartNode().setDistance(0.0); //set start node distance to 0
	}
	
	public ArrayList<Tile> start() {
		int counter = 0;
		int len = Board.allNodes.size();
		while (counter < len) {
			sortNodesByDistance();
			Tile closestNode = Board.allNodes.remove(0); //basically a shift, brings out the first element
			
			if(closestNode.isWallNode()) //if its a wall we skip
				continue;
			
			if(closestNode.getDistance() == Double.POSITIVE_INFINITY) //if the closest node is infinity, we stop (its trapped) 
				return visitedNodesInOrder;
			
			closestNode.setVisited();
			visitedNodesInOrder.add(closestNode);
			
			if (closestNode.isEndNode())
				return visitedNodesInOrder;
			
			updateUnvisitedNeighbors(closestNode);
			
			counter ++;
		}
		return visitedNodesInOrder; //fishy
	}
	
	private void sortNodesByDistance(){
		Tile temp = null;
		for (int i = 0; i < Board.allNodes.size(); i++) {     
            for (int j = i+1; j < Board.allNodes.size(); j++) {   
               if(Board.allNodes.get(i).getDistance() > Board.allNodes.get(j).getDistance()) {    
                   temp = Board.allNodes.get(i);    
                   Board.allNodes.set(i, Board.allNodes.get(j));
                   Board.allNodes.set(j, temp);    
               }     
            }     
        }   
	}
	
	
	
	private void updateUnvisitedNeighbors(Tile node) {
		ArrayList<Tile> unvisitedNeighbors = getUnvisitedNeighbors(node);
		for (Tile neighbor : unvisitedNeighbors) {
			neighbor.setDistance(node.getDistance() + 1);
			neighbor.setPreviousNode(node);
		}
		
	}
	
	//rows and cols might be messed up here check again
	private ArrayList<Tile> getUnvisitedNeighbors(Tile node){
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		int row = node.getX();
		int col = node.getY();
		
		if (row > 0)
			neighbors.add(Board.grid[row -1][col]);
		if (row < Board.grid.length -1)
			neighbors.add(Board.grid[row +1][col]);
		if (col > 0)
			neighbors.add(Board.grid[row][col-1]);
		if (col < Board.grid[0].length - 1)
			neighbors.add(Board.grid[row][col + 1]);
		
		//filter out the visited tiles
		ArrayList<Tile> filteredList = new ArrayList<Tile>();
		for (Tile i : neighbors) {
			if (!i.isVisited()) {
				filteredList.add(i);
			}
		}
		
		return filteredList;
		
	}
	
	public ArrayList<Tile> getNodesInShortestPathOrder(Tile endNode){
		ArrayList<Tile> nodesInShortestPathOrder = new ArrayList<Tile>();
		Tile currentNode = endNode;
		while (currentNode != null) {
			nodesInShortestPathOrder.add(0, currentNode);
			currentNode = currentNode.getPreviousNode();
		}
		return nodesInShortestPathOrder;
	}
	
}
