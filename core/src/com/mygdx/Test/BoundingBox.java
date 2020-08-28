package com.mygdx.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static java.lang.StrictMath.abs;

/*This class is used to draw the box around selected tiles*/
public class BoundingBox {

    private int x;
    private int y;
    private int x2;
    private int y2;

    private ShapeRenderer shapeRenderer;

    private int tileSize;

    public BoundingBox(int x1, int y1, int x2, int y2){
        this.x = x1;
        this.y = y1;
        this.y2 = y2;
        this.x2 = x2;
        shapeRenderer = new ShapeRenderer();
    }

    public void setTileSize(int size){
        this.tileSize = size;
    }

    public void draw(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        Gdx.gl20.glLineWidth(2);
        if(x > x2){
            //dragged left
            x = (x / tileSize) * tileSize;
            x2 = (x2 / tileSize) * tileSize;
        }else{
            x = (x / tileSize) * tileSize;
            x2 = (x2 / tileSize) * tileSize + tileSize;
        }
        if(y > y2){
            //dragged down
            y = (y / tileSize) * tileSize + tileSize;
            y2 = (y2 / tileSize)* tileSize;
        }else{
            y = (y / tileSize) * tileSize;
            y2 = (y2 / tileSize)* tileSize + tileSize;
        }
        shapeRenderer.line(x, y, x, y2);
        shapeRenderer.line(x2, y, x2, y2);
        shapeRenderer.line(x,y,x2,y);
        shapeRenderer.line(x2,y2,x,y2);
        shapeRenderer.end();
        Gdx.gl20.glLineWidth(1);
    }

    public int getSelectionWidth(){
        return abs(x - x2) / 16;
    }


    public int getSelectionHeight(){
        return abs(y - y2) / 16;
    }


    public int[] getCoordinates(){
        int coordinates[] = {x, y, x2, y2};
        return coordinates;
    }


}
