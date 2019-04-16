package com.mygdx.TileMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class MyInputProcessor implements InputProcessor {

    private Vector2 touchPosition = new Vector2();

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPosition.set(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println(touchPosition.x + " " + touchPosition.y);
        if(button == Input.Buttons.LEFT)
            return true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 vector2 = new Vector2();
        vector2.set(screenX, Gdx.graphics.getHeight() - screenY);
        if(vector2 != touchPosition){
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
