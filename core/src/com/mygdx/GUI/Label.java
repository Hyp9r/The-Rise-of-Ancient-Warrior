package com.mygdx.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Label extends Component{


    private String text;
    private SpriteBatch batch;
    private int x,y;
    private int fontSize;
    BitmapFont font;

    public Label(String text, int x, int y, int fontSize){
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        init();

    }

    private void init(){
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("./fonts/lato" + Integer.toString(fontSize) + ".fnt"), false);
    }

    @Override
    public void draw(){
        batch.begin();
        font.draw(batch, text, x , y + fontSize);
        batch.end();
    }

    @Override
    public void update(){

    }
}
