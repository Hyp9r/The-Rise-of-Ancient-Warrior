package com.mygdx.EditorStateManager;

public class EditorStateManager {

        private int currentState;
        private int previousState;

        public static final int START = 0;
        public static final int EDITOR = 2;
        public static final int CREATE_MAP = 1;
        public static final int NUM_STATES = 3;
        private State[] editorStates;
        private EditorState editorState = null;

        public EditorStateManager(){
            editorStates = new State[NUM_STATES];
            setState(START);
        }

        public void setState(int state) {
            previousState = currentState;
            unloadState(previousState);
            currentState = state;
            switch (state) {
                case START:
                    editorStates[state] = new EditorStartState(this);
                    editorStates[state].init();
                    break;
                case EDITOR:
                    if (editorState == null) {
                        editorState = new EditorState(this);
                        editorState.init();
                        editorStates[state] = editorState;
                    }
                    break;
                case CREATE_MAP:
                    editorStates[state] = new EditorCreateMapState(this);
                    editorStates[state].init();
                    break;
            }
        }

        public void unloadState(int state){
            if(state != EDITOR)
                editorStates[state] = null;
        }

        public void update(){
            editorStates[currentState].update();
        }

        public void draw(){
            editorStates[currentState].draw();
            update();
        }

}
