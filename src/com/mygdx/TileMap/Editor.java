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

    public void updateTile(int x, int y){
        int tileIndexX = x / tileSize;
        int tileIndexY = y / tileSize;
        if(tileIndexX > width || tileIndexY > height){
            System.out.println("dsadsad");
        }else{
            tiles[tileIndexX][tileIndexY] = selectedTile;
        }
    }

    public void updateTiles(int x, int y, ArrayList<Integer> listOfTiles, int ySize, int xSize){
        int tileIndexX = x / tileSize;
        int tileIndexY = y / tileSize;
        int tileNumber = 0;
        for(int i=0; i<xSize; i++){
            for(int j=0; j<ySize; j++){
                Tile tile = new Tile(TileType.NORMAL, listOfTiles.get(tileNumber));
                tiles[tileIndexX][tileIndexY] = tile;
                tileNumber++;
                //we need to increase tileIndexX and tileIndexY so we could change appropriate tile
                //we have to update only y index when
                tileIndexY++;
            }
            tileIndexY = y / tileSize;
            tileIndexX++;
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

    public void drawMultipleSelection(int xSize, int ySize, ArrayList<Integer> list, int mouseX, int mouseY){
        batch.begin();
        int counter = 0;
        System.out.println("list="+ list.size());
        for(int i=0; i<ySize; i++){
            for(int j=0; j<xSize; j++){
                batch.draw(tileRegions[list.get(counter)], mouseX + j * tileSize, mouseY + i * tileSize);
                counter++;
            }
        }
        batch.end();
    }

    private void drawGrid(){
        if(MyEditor.grid){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.BLACK);
            for(int i=0; i<=height*16; i+= 16){
                shapeRenderer.line(0, i, width*tileSize, i);
            }
            for(int i=0; i<=width*16; i+= 16){
                shapeRenderer.line(i, 0, i, height*tileSize);
            }
            shapeRenderer.end();
        }
    }

    //private void drawTiles(){
        /* This is drawing our tiles -> map */
        /*batch.begin();
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                batch.draw(tileRegions[tiles[i][j].getId()], i*16, j*16);
            }
        }
        batch.end();
    }*/

    public void draw(){
        //drawTiles();
        drawGrid();
    }

    public Tile[][] getMap(){
        return this.tiles;
    }


}
