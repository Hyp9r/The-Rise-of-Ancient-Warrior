package com.mygdx.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ImageBox extends Component {


    private Texture texture;
    private SpriteBatch batch;
    private int x, y;

    public ImageBox(String path, int x, int y){
        this.x = x;
        this.y = y;
        this.texture = new Texture(path);
        batch = new SpriteBatch();
    }

    @Override
    public void draw(){
        batch.begin();
        batch.draw(texture, x, y);
        batch.end();
    }
}
