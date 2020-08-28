package com.mygdx.Attacks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Attack {

    private int damage;
    private int cooldown;
    private Texture texture;
    private SpriteBatch batch;

    public Attack(int damage, int cooldown){
        init();
    }

    private void init(){
        batch = new SpriteBatch();
        loadTexture();
    }

    private void loadTexture(){
        texture = new Texture("lightning.png.png");
    }


    public void draw(int playerX, int playerY){
        batch.begin();
        batch.draw(texture, playerX+30 ,playerY+10);
        batch.end();
    }


}
