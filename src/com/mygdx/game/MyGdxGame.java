package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.GameStateManager.GameStateManager;
import java.lang.*;


public class MyGdxGame extends ApplicationAdapter{

	private GameStateManager gsm;

	@Override
	public void create () {
        gsm = new GameStateManager();
	}

	public void update(){
		gsm.update();
	}

	@Override
	public void render () {
		BitmapFont font = new BitmapFont();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.draw();
	}

	@Override
	public void dispose () {
	}
}
