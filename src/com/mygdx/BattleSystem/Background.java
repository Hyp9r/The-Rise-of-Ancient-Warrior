package com.mygdx.BattleSystem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {

    private Texture texture;
    private SpriteBatch batch;

    public Background(){
        texture = new Texture("backgrounds/nature.jpg");
        batch = new SpriteBatch();
    }

    public void draw(){
        batch.begin();
        batch.draw(texture, 0, 268, 1080, 500);
        batch.end();
    }


}
