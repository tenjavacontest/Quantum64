package ten.java.Quantum64;

public class GameManager {

	private GameState currentState;
	private Main plugin;

	public GameManager(Main plugin) {
		this.plugin = plugin;
		this.currentState = GameState.WAITING_FOR_PLAYERS;
	}

	public static enum GameState {
		WAITING_FOR_PLAYERS, STARTING_COUNTDOWN, IN_GAME, POST_GAME
	}

	public void setState(GameState state) {
		this.currentState = GameState.WAITING_FOR_PLAYERS;
		onStateChanged();
	}
	
	public GameState getState(){
		return currentState;
	}

	public void onStateChanged() {
		if (currentState == GameState.WAITING_FOR_PLAYERS) {
			
		}
	}
}
