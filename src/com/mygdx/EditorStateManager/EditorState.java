package com.mygdx.EditorStateManager;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class EditorState extends State {


    private SpriteBatch bacth;
    private ShapeRenderer shapeRenderer;

    public EditorState(EditorStateManager esm){
        super(esm);
    }

    @Override
    public void init(){
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        bacth = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.graphics.setWindowedMode(1024, 768);
        }
    }

    @Override
    public void draw(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.6f, 0.6f, 0.6f, 1.0f);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
    }

    @Override
    public void handleInput() {

    }
}
