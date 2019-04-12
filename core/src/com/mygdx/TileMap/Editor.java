package com.mygdx.TileMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class Editor {


    private int width;
    private int height;
    private Tile[][] tiles;
    private int tileSize;
    private Tile selectedTile;

    //graphics stuff
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;


    //tileset
    private TextureRegion[] tileRegions;
    private Texture tileSet;

    public Editor(int tilesWidth, int tilesHeight, int tileSize){
        this.width = tilesWidth;
        this.height = tilesHeight;
        this.tileSize = tileSize;
        init();
    }

    private void init(){
        this.shapeRenderer = new ShapeRenderer();
        this.batch = new SpriteBatch();
        tiles = new Tile[width][height];
        tileRegions = new TextureRegion[39];
    }

    public void loadDefaultMap(){
        for(int i=0; i < width; i++){
            for(int j=0; j<height; j++){
                tiles[i][j] = new Tile(TileType.NORMAL, 2);
            }
        }
    }

    public void loadMap(Tile[][] map){
        for(int i=0; i < width; i++){
            for(int j=0; j<height; j++){
                tiles[i][j].setId(map[i][j].getId());
                tiles[i][j].setType(map[i][j].getType());
            }
        }
    }

    public void loadTileSet(){
        tileSet = new Texture("myTiles.png");
        for(int i=0; i<tileRegions.length; i++){
            tileRegions[i] = new TextureRegion(tileSet, i*16, 0,16,16);
        }
    }

    public void updateTile(int x, int y){
        int tileIndexX = x / 16;
        int tileIndexY = y / 16;
        if(tileIndexX > 39 || tileIndexY > 39){
            System.out.println("dsadsad");
        }else{
            tiles[tileIndexX][tileIndexY] = selectedTile;
        }
    }

    public void selectTile(int y){
        int tileIndex = y / 16;
        this.selectedTile = new Tile(TileType.NORMAL, tileIndex);
        System.out.println("Tile selected: " + tileIndex);
    }

    public void selectTile(int x, int y){
        int tileIndexY = y / tileSize;
        int tileIndexX = x / tileSize;
        this.selectedTile = new Tile(tiles[tileIndexX][tileIndexY].getType(), tiles[tileIndexX][tileIndexY].getId());
        System.out.println("Tile selected: " + tiles[tileIndexX][tileIndexY].getId());
    }


    public void draw(){
        /* This is drawing our tiles -> map */
        batch.begin();
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                batch.draw(tileRegions[tiles[i][j].getId()], i*16, j*16);
            }
        }
        batch.end();


        /* This is drawing our grid */
        if(MyEditor.grid){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.BLACK);
            for(int i=0; i<=height*16; i+= 16){
                shapeRenderer.line(0, i, 40*16, i);
            }
            for(int i=0; i<=width*16; i+= 16){
                shapeRenderer.line(i, 0, i, 40*16);
            }
            shapeRenderer.end();
        }



        /*
        * Drawing tileset at the right corner of the screen
        *
        * */
        int coord = 0;
        batch.begin();
        for(int i=0; i<tileRegions.length; i++){
            batch.draw(tileRegions[i],1440-16, coord);
            coord +=16;
        }
        batch.end();
    }

    public Tile[][] getMap(){
        return this.tiles;
    }


}
