package states;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public final class StateManager{

    private static StateManager instance;

    public static StateManager getInstance() {
        if(instance == null){
            instance = new StateManager();
        }
        return instance;
    }

    private final Map<String, State> stateMap;
    private State currentState;

    private StateManager(){
        stateMap = new HashMap<>();
    }

    public void setCurrentState(final String name){
        if(currentState != null){
            currentState.handleExitTransition();
        }
        if(stateMap.containsKey(name)){
            currentState = stateMap.get(name);
            currentState.handleStartTransition();
        }else{
            throw new IllegalArgumentException(String.format("Invalid state name %s used", name));
        }
    }

    protected void addState(final State state){
        stateMap.put(state.getName(), state);
    }

    public State getCurrentState(){
        return currentState;
    }

    public void tick(){
        if(currentState != null){
            currentState.tick();
        }
    }

    public void render(Graphics g){
        if(currentState != null){
            currentState.render(g);
        }
    }

}
