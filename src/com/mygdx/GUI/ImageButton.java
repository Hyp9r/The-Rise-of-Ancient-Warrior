package com.mygdx.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ImageButton extends Component{
    private Texture texture, textureNormal, textureHover;
    private SpriteBatch batch;
    private int x, y;
    private int height, width;

    private ClickEvent listener;

    public ImageButton(int x, int y, int width, int height, String path, String path2){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textureNormal = new Texture(path);
        this.textureHover = new Texture(path2);
        init();
    }

    private void init(){
        batch = new SpriteBatch();
    }

    public void update(){
        /**Check for user interactioon**/
        onHover();
        onClick();
    }

    public void draw(){
        batch.begin();
        batch.draw(texture, x, y);
        batch.end();
    }

    public void addClickListener(ClickEvent listener){
        this.listener = listener;
    }

    private void onHover(){
        if(isInsideBounds() ){
            //draw on hover image
            texture = textureHover;
        }else{
            texture = textureNormal;
        }
    }

    private void onClick(){
        if(isInsideBounds() && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            listener.onClick();
        }
    }

    private boolean isInsideBounds(){
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        if(mouseX >= x && mouseX <= x+width){
            //this means that the mouse is in the right position on x coordinates
            if(mouseY >= y && mouseY <= y+height){
                //mouse is inside bounds of the button
                return true;
            }
        }
        return false;
    }

    public void setY(int y){
        this.y = y;
    }

}
