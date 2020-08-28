package com.mygdx.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;

public class TextField extends Component {
    //I need to catch input from user
    //and show it on the screen
    private String text;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private String fontSize = "16";
    BitmapFont font = new BitmapFont(Gdx.files.internal("./fonts/lato" + fontSize + ".fnt"), false);
    GlyphLayout layout = new GlyphLayout(); //dont do this every frame! Store it as member
    private int x;
    private int y;
    private InputListener listener;
    private ClickEvent clickListener;

    private boolean wait = false;
    private boolean focused = false;

    private float lastTime;
    private float delay;//1 sec
    private float hitTime;

    public TextField(){
        init();
    }

    public TextField(String text){
        this.text = text;
        this.x = 100;
        this.y = 200;
        init();
    }

    public TextField(String text, int x, int y){
        this.text = text;
        this.x = x;
        this.y = y;
        init();
    }

    private void init(){
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    private void onClick(){
        if(isInsideBounds() && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            //that means that the mouse is inside the button and the left click has been pressed
            clickListener.onClick();
        }else if(!isInsideBounds() && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            focused = false;
        }
    }

    private boolean isInsideBounds(){
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        if(mouseX >= x && mouseX <= x+150){
            //this means that the mouse is in the right position on x coordinates
            if(mouseY >= y && mouseY <= y+30){
                //mouse is inside bounds of the button
                return true;
            }
        }
        return false;
    }

    public void addClickListener(ClickEvent listener){
        this.clickListener = listener;
    }

    public void setFocused(){
        focused = true;
        lastTime = System.currentTimeMillis();
        delay = 1000L;//1 sec
        hitTime = lastTime + delay;
    }

    private void animate(){
        
    }

    @Override
    public void update(){
        onClick();
        if(focused) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
                text = text + 'Q';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                text = text + 'W';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                text = text + 'E';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                text = text + 'R';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
                text = text + 'T';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
                text = text + 'Z';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
                text = text + 'U';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
                text = text + 'I';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
                text = text + 'O';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                text = text + 'P';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
                text = text + 'A';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                text = text + 'S';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
                text = text + 'D';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
                text = text + 'F';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
                text = text + 'G';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
                text = text + 'H';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
                text = text + 'J';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
                text = text + 'K';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
                text = text + 'L';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
                text = text + 'Y';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
                text = text + 'X';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
                text = text + 'C';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.V)) {
                text = text + 'V';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
                text = text + 'B';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
                text = text + 'N';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
                text = text + 'M';
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
                if (!text.equals(""))
                    text = text.substring(0, text.length() - 1);
                else {
                    x = 100;
                }
            } else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
                text = text + '1';
            } else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
                text = text + '2';
            } else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
                text = text + '3';
            } else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)){
                text = text + '4';
            } else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)){
                text = text + '5';
            } else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)){
                text = text + '6';
            } else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)){
                text = text + '7';
            } else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)){
                text = text + '8';
            } else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)){
                text = text + '9';
            } else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)){
                text = text + '0';
            }
        }
    }

    @Override
    public void draw(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        //draw rectanle, or line
        shapeRenderer.setColor(0f, 0f, 0f, 1.0f);
        shapeRenderer.line(x, y, x+200, y);
        shapeRenderer.end();

        batch.begin();
        font.draw(batch, text, x, y + Integer.parseInt(fontSize));
        batch.end();

        layout.setText(font, text);
        float width = layout.width;// contains the width of the current set text

        if(focused){
            if(System.currentTimeMillis() <= hitTime){
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                //draw rectanle, or line
                shapeRenderer.setColor(0f, 0f, 0f, 1.0f);
                shapeRenderer.line(x + width, y+1, x + width, y+30);
                shapeRenderer.end();
            }
        }
    }

    public String getText(){
        return this.text;
    }


}
