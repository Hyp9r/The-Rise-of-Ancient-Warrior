package com.mygdx.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Button extends Component implements Transisionable{

    private Color color1 = new Color(0.92f, 0.92f, 0.92f, 1.0f);
    private Color color2 = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    private Color color;
    private String text;
    private SpriteBatch batch;
    private float x,y;
    private float width,height;
    private Texture texture;

    private boolean visible = true;
    private boolean animating = false;

    BitmapFont font = new BitmapFont(Gdx.files.internal("./fonts/lato.fnt"), false);

    private ClickEvent listener;


    private int currentSprite = 0;
    private TextureRegion[] regions = new TextureRegion[3];


    long timeInMillis;
    long delay;
    long hitTime;


    public Button(int x, int y, String text){
        this.x = x;
        this.y = y;
        this.text = text;
        this.width = 200;
        this.height = 50;
        init();
    }

    private void init(){
        batch = new SpriteBatch();
        texture = new Texture("./gui/gardient_button_200x50.png");
        /*for(int i=0; i<3; i++){
            regions[i] =  new TextureRegion(texture, 3, i*16+i*height+8, width, height);
        }*/
        color = color1;
    }

    @Override
    public void update(){
        onHover();
        onClick();
    }

    @Override
    public void animate() {
        if(System.currentTimeMillis() <= hitTime){
            if(visible){
                x-= 200.0f * Gdx.graphics.getDeltaTime();
                //y+= 200.f * Gdx.graphics.getDeltaTime();
            }else{
                x = 100;
                //y-= 200.f * Gdx.graphics.getDeltaTime();
            }
        }else{
            animating = false;
            visible = !visible;
        }
    }

    private void onHover(){
        if(isInsideBounds()){
            color = color2;
        }else{
            color = color1;
        }
    }

    public void draw(){
        if(visible){
            batch.begin();
            batch.draw(texture, x, y);
            font.setColor(color);
            font.draw(batch, this.text, x+40, y+32);
            batch.end();
            update();
        }

        if(animating){
            animate();
        }

    }

    private void onClick(){
        if(isInsideBounds() && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            //that means that the mouse is inside the button and the left click has been pressed
            listener.onClick();
        }
    }

    public void addClickListener(ClickEvent listener){
        this.listener = listener;
    }

    public void setVisible(boolean visible){
        if(visible){
            visible = true;
        }
        animating = true;
        timeInMillis = System.currentTimeMillis();
        delay = 100L;//1 sec
        hitTime = timeInMillis + delay;
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

}
