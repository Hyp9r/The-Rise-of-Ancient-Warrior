package com.mygdx.EditorStateManager;


public abstract class State {
    protected EditorStateManager esm;

    public State(EditorStateManager esm){
            this.esm = esm;
        }

        public abstract void init();
        public abstract void update();
        public abstract void draw();
        public abstract void handleInput();
}
