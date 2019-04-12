package com.mygdx.TileMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.NumberUtils;
import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class MyEditor extends ApplicationAdapter {

    private Editor editor;
    private int screenHeight = 850; //need this variable since libgdx starts from the left bottom corner
    private boolean tileSelected;
    private ShapeRenderer shapeRenderer;
    public static boolean grid;// if true the editor has grids if false it doesn't
    public Tile[][] map;
    int width = 40;
    int height = 40;
    int tileSize = 16;

    public void create(){
        editor = new Editor(width, height, tileSize);
        map = new Tile[width][height];
        map = editor.getMap();
        tileSelected = false;
        grid = true;
        shapeRenderer = new ShapeRenderer();
        MyInputProcessor myInputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(myInputProcessor);
        editor.loadTileSet();
        editor.loadDefaultMap();
    }

    public void render(){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        editor.draw();
        if(Gdx.input.isTouched(Input.Buttons.LEFT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                int x2 = Gdx.input.getX();
                int y2 = Gdx.input.getY();
                if(x > x2){
                    //crtaj od x2 do x

                }else{
                    //crtaj x pa onda x2
                    drawTransparentRectanleForTileSelection(x, y, x2, y2);
                    //editor.selectTile(Gdx.input.getX(), screenHeight - Gdx.input.getY());
                    //tileSelected = true;
                }
            }
        }else if(Gdx.input.isTouched(Input.Buttons.LEFT) && Gdx.input.getX() >= 1440-16){
            editor.selectTile(screenHeight - Gdx.input.getY());
            tileSelected = true;
        }else if(Gdx.input.isTouched(Input.Buttons.LEFT) && tileSelected){
            editor.updateTile(Gdx.input.getX(), screenHeight- Gdx.input.getY());
            //System.out.println("X: " + Gdx.input.getX() + " Y: " + (40*16+100 - Gdx.input.getY()));
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.G)){
            grid = !grid;
        }else if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.S)){
            saveMapFile();
        }else if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.O)){
            //open map file
            loadMap();
            editor.loadMap(map);
            editor.draw();
        }
    }

    private void drawTransparentRectanleForTileSelection(int x, int y, int x2, int y2){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);
        int width = x2 / tileSize - x / tileSize;
        int height = y2 / tileSize - y2 / tileSize;

        //draw rect

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(220,220, 220, 0.6f);
        shapeRenderer.rect(Gdx.input.getX()/16*16, (screenHeight - Gdx.input.getY())/16*16, tileSize*width,tileSize*height);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void saveMapFile(){
        //save the map file
        System.out.println("Saving the map file...");
        try{
            PrintWriter writer = new PrintWriter("./maps/palletTown.map", "UTF-8");
            for(int i=0; i<width; i++){
                for(int j=0; j<height; j++){
                    writer.print(map[i][j].getId() + ",");
                }
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadMap(){
        System.out.println("Loading the map...");
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
            for(int i=0; i<width; i++){
                for(int j=0; j<height; j++){
                    if(isNumeric(tokens[counter])) {
                        map[i][j] = new Tile(TileType.NORMAL, Integer.parseInt(tokens[counter]));
                    }
                    counter++;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void dispose(){
        editor = null;
    }
}
