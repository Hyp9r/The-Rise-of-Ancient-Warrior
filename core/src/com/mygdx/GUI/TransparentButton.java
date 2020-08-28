package com.mygdx.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class TransparentButton extends Component{

    private String text;
    private int x, y;
    private int width, height;
    private int textX, textY;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private Color color1 = new Color(0.5137f, 0.9412f, 0.6941f, 1.0f);
    private Color color2 = new Color(0.10196f, 0.73725f, 0.61176f, 1.0f);


    private Color textColorNormal = new Color(0.92f, 0.92f, 0.92f, 1.0f);
    private Color textColorHover = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    private Color currentTextColor;


    private float textWidth;
    BitmapFont font = new BitmapFont(Gdx.files.internal("./fonts/lato.fnt"), false);
    GlyphLayout layout = new GlyphLayout();

    private ClickEvent listener;


    public TransparentButton(int x, int y){
        this.x = x;
        this.y = y;
        init();
        setWidth();
    }

    public TransparentButton(int x, int y, String text){
        this.x = x;
        this.y = y;
        this.text = text;
        init();
        setWidth();
        setTextPosition();
    }

    private void init(){
        height = 50;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        currentTextColor = textColorNormal;
    }

    /**This function determines the size(width) of the button based on how many characters does the text have**/
    private void setWidth(){
        layout.setText(font, text);
        // contains the width of the current set text
        this.textWidth = layout.width;
        this.width = (int)textWidth + 100;
    }

    private void setTextPosition(){
        textX = calculateTextPosition()[0];
        textY = calculateTextPosition()[1];
    }

    public void update(){
        /**Check for user interactioon**/
        onHover();
        onClick();
    }

    public void draw(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x,y, width-50, height, color1, color2, color2, color1);

        shapeRenderer.setColor(color2);
        shapeRenderer.arc(x+width-height, y+height-25, 25.0f, 0f, 90);
        shapeRenderer.arc(x+width-height, y+25, 25.0f, 270f, 90);

        shapeRenderer.setColor(color1);
        shapeRenderer.arc(x, y+25, 25.0f, 180f, 90);
        shapeRenderer.arc(x, y+height-25, 25.0f, 90f, 90);
        shapeRenderer.end();


        batch.begin();
        font.setColor(currentTextColor);
        font.draw(batch, this.text, textX, textY);
        batch.end();

        update();
    }

    private int[] calculateTextPosition(){
        /*Method that returns Array of 2 elements, first element has X position where the text should be drawn and the second element has Y position*/
        int xPosition[] = new int[2];
        xPosition[0] = x + (int)textWidth / 4;
        xPosition[1] = y + (height / 2 + 5);
        System.out.println("Button width = " + width);
        System.out.println("Text width = " + textWidth);
        return xPosition;
    }

    public void addClickListener(ClickEvent listener){
        this.listener = listener;
    }

    private void onHover(){
        if(isInsideBounds() ){
            currentTextColor = textColorHover;
        }else{
            currentTextColor = textColorNormal;
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



    /*private void createFonts() {
        FileHandle fontFile = Gdx.files.internal("data/Roboto-Bold.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 12;
        textFont = generator.generateFont(parameter);
        parameter.size = 24;
        titleFont = generator.generateFont(parameter);
        generator.dispose();
    }*/

    public void setText(String text){
        this.text = text;
    }

}
