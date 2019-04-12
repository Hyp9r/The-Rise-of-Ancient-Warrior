package com.mygdx.TileMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.mygdx.game.MyGdxGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map {
    private Tile[][] tiles;
    private int tileSize;
    private int mapWidth;
    private int mapHeight;
    private TextureRegion[] tileRegions;
    private Texture tileSet;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    public Map(int mapWidth, int mapHeight, int tileSize){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileSize = tileSize;
        this.tileRegions = new TextureRegion[39];
        init();
    }

    private void init(){
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    public void loadTileSet(){
        tileSet = new Texture("myTiles.png");
        for(int i=0; i<tileRegions.length; i++){
            tileRegions[i] = new TextureRegion(tileSet, i*16, 0,16,16);
        }
    }

    public void loadMapFromAFile(){
        System.out.println("Loading the map...");
        tiles = new Tile[mapWidth/16][mapHeight/16];
        BufferedReader br;
        String[] tokens;
        try{
            br = new BufferedReader(new FileReader("./maps/palletTown.map"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            everything = everything.substring(0, everything.length()-1);
            tokens = everything.split(",");

            int counter = 0;
            for(int i=0; i<mapWidth / 16; i++){
                for(int j=0; j<mapHeight/16; j++){
                    if(isNumeric(tokens[counter])) {
                        tiles[i][j] = new Tile(TileType.NORMAL, Integer.parseInt(tokens[counter]));
                    }
                    switch (tiles[i][j].getId()){
                        case 5:
                            tiles[i][j].setType(TileType.BLOCKED);
                            break;
                        case 6:
                            tiles[i][j].setType(TileType.WRAP);
                            break;
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                            tiles[i][j].setType(TileType.WATER);
                            break;
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                            tiles[i][j].setType(TileType.BLOCKED);
                            break;
                        default:
                            tiles[i][j].setType(TileType.NORMAL);
                            break;
                    }
                    counter++;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        tiles[0][36].setType(TileType.WRAP);
        tiles[0][36].setType(TileType.WRAP);
        tiles[0][36].setType(TileType.WRAP);
        tiles[0][36].setType(TileType.WRAP);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void loadMap(){
        tiles = new Tile[mapWidth/16][mapHeight/16];
        for(int i=0; i < mapWidth / tileSize; i++){
            for(int j=0; j<mapHeight / tileSize; j++){
                tiles[i][j] = new Tile(TileType.NORMAL, 2);
            }
        }
        tiles[20][25] = new Tile(TileType.BLOCKED, 5);
        tiles[21][25] = new Tile(TileType.BLOCKED, 5);
        tiles[22][25] = new Tile(TileType.BLOCKED, 5);
        tiles[23][25] = new Tile(TileType.BLOCKED, 5);
        tiles[20][24] = new Tile(TileType.BLOCKED, 5);
        tiles[21][24] = new Tile(TileType.BLOCKED, 5);
        tiles[22][24] = new Tile(TileType.BLOCKED, 5);


        //wrap
        tiles[10][10] = new Tile(TileType.WRAP, 6);

        tiles[30][36] = new Tile(TileType.NORMAL, 4);
        tiles[10][31] = new Tile(TileType.NORMAL, 1);
        tiles[20][34] = new Tile(TileType.NORMAL, 1);
        tiles[33][15] = new Tile(TileType.NORMAL, 1);
    }

    public void draw(){
        batch.begin();
        for(int i=0; i<mapWidth / tileSize; i++){
            for(int j=0; j<mapHeight / tileSize; j++){
                batch.draw(tileRegions[tiles[i][j].getId()], i*16*MyGdxGame.scale, j*16*MyGdxGame.scale, tileSize*MyGdxGame.scale, tileSize*MyGdxGame.scale);
            }
        }
        batch.end();
        if(MyGdxGame.grid){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.BLACK);
            for(int i=0; i<=mapHeight*16; i+= 16){
                shapeRenderer.line(0, i, 40*16, i);
            }
            for(int i=0; i<=mapWidth*16; i+= 16){
                shapeRenderer.line(i, 0, i, 40*16);
            }
            shapeRenderer.end();
        }
    }

    public Tile getTile(int x, int y){
        return tiles[x/16][y/16];
    }

}
