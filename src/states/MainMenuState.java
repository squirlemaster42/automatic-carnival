package states;

import world.World;

import java.awt.Graphics;

public class MainMenuState extends State{

    private static MainMenuState instance;

    public static MainMenuState getInstance(){
        if(instance == null){
            instance = new MainMenuState();
        }
        return instance;
    }

    private final World world;

    private MainMenuState() {
        super("MainMenuState");
        world = new World();
    }

    @Override
    public void handleStartTransition() {

    }

    @Override
    public void handleExitTransition() {

    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
