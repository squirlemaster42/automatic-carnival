package states;

import java.awt.*;

public abstract class State {

    private final String name;

    public State(final String name){
        this.name = name;
        StateManager.getInstance().addState(this);
    }

    public String getName(){
        return name;
    }

    public abstract void handleStartTransition();
    public abstract void handleExitTransition();
    public abstract void tick();
    public abstract void render(Graphics g);
}
