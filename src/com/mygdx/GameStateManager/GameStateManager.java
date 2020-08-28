package com.mygdx.GameStateManager;

public class GameStateManager {

    private int currentState;
    private int previousState;

    public static final int BATTLE = 2;
    public static final int GAME = 1;
    public static final int MENU = 0;
    public static final int NUM_STATES = 3;
    private State[] gameStates;
    private GameState gameState = null;

    public GameStateManager(){
        gameStates = new State[NUM_STATES];
        setState(GAME);
    }

    public void setState(int state){
        previousState = currentState;
        unloadState(previousState);
        currentState = state;
        switch (state){
            case MENU:
                gameStates[state] = new MenuState(this);
                gameStates[state].init();
                break;
            case GAME:
                if(gameState == null){
                    gameState = new GameState(this);
                    gameState.init();
                    gameStates[state] = gameState;
                }
                break;
            case BATTLE:
                gameStates[state] = new BattleState(this);//tu je problem
                gameStates[state].init();
                break;
        }
    }

    public void unloadState(int state){
        if(state != GAME)
            gameStates[state] = null;
    }

    public void update(){
        gameStates[currentState].update();
    }

    public void draw(){
        gameStates[currentState].draw();
    }
}
