import states.MainMenuState;
import states.StateManager;

public class Main {

    static{
        MainMenuState.getInstance();
    }

    public static void main(String[] args){
        Game game  = new Game("Test", 600, 800);
        game.start();
        StateManager.getInstance().setCurrentState("MainMenuState");
    }
}
