package application;

public class GameState {
	private static String state = "startPointState";
	
	//making start node
	public static void startPointState() {
		state = "startPointState";
	}
	
	//making end node
	public static void endPointState() {
		state = "endPointState";
	}
	
	//making walls state
	public static void wallState() {
		state = "wallState";
	}
	
	public static boolean isEndPointState() {
		if (state.equals("endPointState")) {
			return true;
		}
		else {
			return false;
		}	
	}
	
	public static boolean isStartPointState() {
		if (state.equals("startPointState")) {
			return true;
		}
		else {
			return false;
		}	
	}
	
	public static boolean isWallState() {
		if (state.equals("wallState")) {
			return true;
		}
		else {
			return false;
		}	
	}
		

}
