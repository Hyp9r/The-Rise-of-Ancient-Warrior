package com.mygdx.GameStateManager;

public abstract class State {
    protected GameStateManager gsm;

    public State(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void init();
    public abstract void update();
    public abstract void draw();
    public abstract void handleInput();
}
