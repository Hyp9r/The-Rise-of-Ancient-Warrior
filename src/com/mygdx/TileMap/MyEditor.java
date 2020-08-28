package com.mygdx.TileMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.EditorStateManager.EditorStateManager;
import com.mygdx.GUI.*;
import com.mygdx.GameStateManager.GameStateManager;
import com.mygdx.Test.BoundingBox;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

import static java.lang.StrictMath.abs;


public class MyEditor extends ApplicationAdapter{
    /**GUI**/
    private TransparentButton tab1;
    private TransparentButton tab2;
    private TransparentButton tab3;

    private Panel panel;
    private Component tileset;
    /**Game state manager used so I can have few tabs in editor and it handles rendering and input for that state**/
    private EditorStateManager esm;


    /**/
    private Editor editor;
    private int screenHeight = 850; //need this variable since libgdx starts from the left bottom corner
    private boolean tileSelected;
    public static boolean grid;// if true the editor has grids if false it doesn't
    public Tile[][] map;
    int width =68;
    int height = 48;
    int tileSize = 16;
    private ArrayList<Integer> selectedTileIds;


    /*This variable is used to flag when right click is used so we could select multiple tiles*/
    private boolean rightClick = false;

    /*First click is used to determine x1 and y1 coordinates*/
    private boolean firstClick;//variable use for multiple tile selection

    private int boundingBoxX;
    private int boundingBoxY;

    private boolean selectedMultipleTiles;//boolean that tells us if we selected multiple tiles
    private int xSize, ySize;


    //Stack used for undo feature
    private Stack<Tile[][]> mapStack = new Stack<Tile[][]>();


    public void create(){
        esm = new EditorStateManager();
        initButtons();
        initPanel();
        init();
    }

    public void init() {
        editor = new Editor(width, height, tileSize);
        map = new Tile[width][height];
        map = editor.getMap();
        tileSelected = false;
        grid = true;
        editor.loadDefaultMap();
        firstClick = true;
        selectedMultipleTiles = false;
        selectedTileIds = new ArrayList<Integer>();
    }

    private void initPanel(){
        /*panel = new Panel(1240, 0, 200, 750, AncorSide.RIGHT);
        tileset = new TileSet("myTiles.png", 16, 0);
        panel.addComponent(tileset);*/
    }

    public void update(float delta){
        esm.update();
    }

    private void drawGUI(){
        /**Gui**/
        //Panels
        //panel.draw();
        //panel.update();

        //Buttons
        //tab1.draw();
        //tab1.update();
        //tab2.draw();
        //tab2.update();
        //tab3.draw();
        //tab3.update();
    }

    private void initButtons(){
        /***************Gui stuff***********/
        /*buttonGardientSave = new Button(100, 320, "SAVE MAP");
        ((Button) buttonGardientSave).addClickListener(() -> {
            JFileChooser fileChooser = new JFileChooser();
            int path = fileChooser.showSaveDialog(null);
            if(path == JFileChooser.APPROVE_OPTION){
                File fileToSave = fileChooser.getSelectedFile();
                saveMapFile(fileToSave.getAbsolutePath());
            }
        });*/


        /**********************************/

        /**Tabs for Map, Collision, NPC, Pokemon spawns**/
        /*tab1 = new TransparentButton(0, 770, "Map tab");
        tab1.addClickListener(() -> {
            //gsm.setState(GameStateManager.MAP_STATE);
        });


        tab2 = new TransparentButton(257, 770, "NPC tab");
        tab2.addClickListener(() -> {
            //gsm.setState(GameStateManager.NPC_STATE);
        });


        tab3 = new TransparentButton(514, 770, "Pokemon spawns");
        tab3.addClickListener(() -> {
            //gsm.setState(GameStateManager.POKEMON_SPAWNS_STATE);
        });*/
        /****/
    }

    public void render(){
        //draw();
        esm.draw();
    }



    public void draw() {
        //gsm.draw();
        BoundingBox box = null;

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawGUI();

        //editor.draw();



        if(false){
            //draw those tiles all the time at the mouse coordinates
            editor.drawMultipleSelection(xSize, ySize, selectedTileIds, Gdx.input.getX(), screenHeight - Gdx.input.getY() - ySize*16);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            editor.loadMap(mapStack.pop());
            System.out.println("Undo");
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.G)){
            grid = !grid;
        }else if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            saveMapFile();
        }else if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            loadMap();
            editor.loadMap(map);
            editor.draw();
        }else if(Gdx.input.isTouched(Input.Buttons.LEFT) && Gdx.input.getX() >= 1440-16){
            //checking if mouse is at the position where tiles are, if it is then if the mouse is clicked it will select current tile in memory so we could use it to draw the map
            editor.selectTile(screenHeight - Gdx.input.getY());
            tileSelected = true;
            selectedMultipleTiles = false;
        }else if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            rightClick = true;
            int currentMouseX;
            int currentMouseY;
            //here we should get coordinates of the mouse position when
            // we first right clicked and draw the bounding box until the right click is released
            if(firstClick){
                boundingBoxX = Gdx.input.getX();
                boundingBoxY = screenHeight - Gdx.input.getY();
                firstClick = false;
            }else{
                //this means that we're still holding right click so we have to calculate x and y positions all the time
                //to check if the mouse was moved, so we could draw the box
                currentMouseX = Gdx.input.getX();
                currentMouseY = screenHeight - Gdx.input.getY();
                if(currentMouseY != boundingBoxY || currentMouseX != boundingBoxX){
                    //this means that we changed the position of the mouse
                    if(currentMouseX > boundingBoxX){
                        //the mouse was dragged right
                        box = new BoundingBox(boundingBoxX, boundingBoxY, currentMouseX, currentMouseY);
                        setupBox(box);
                    }else if(currentMouseX < boundingBoxX){
                        //the mouse was dragged left
                        box = new BoundingBox(currentMouseX, boundingBoxY, boundingBoxX, currentMouseY);
                        setupBox(box);
                    }
                }
            }
        }else if(!Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && rightClick) {
            System.out.println("Get current selection");
            rightClick = false;
            firstClick = true;
            //we need to get coordinates of mouse x and mouse y
            // and get the tile position of our mouse so we could get tiles we selected
            int currentMouseX = Gdx.input.getX() / tileSize;
            int currentMouseY = (screenHeight - Gdx.input.getY()) / tileSize;
            int startingMouseX = boundingBoxX / tileSize;
            int startingMouseY = boundingBoxY / tileSize;
            System.out.println("Selected: [" + currentMouseX + "][" + currentMouseY + "]");
            System.out.println(currentMouseX + "," + currentMouseY + "," + startingMouseX + "," + startingMouseY);
            int selectionCoordinates[] = {startingMouseX, startingMouseY, currentMouseX, currentMouseY};
            selectedTileIds.clear();
            selectedTileIds.addAll(getMultipleTiles(selectionCoordinates));
            if(selectedTileIds.size() > 1){
                selectedMultipleTiles = true;
            }
        }else if(!rightClick && Gdx.input.isTouched(Input.Buttons.LEFT) && tileSelected && !selectedMultipleTiles){
            //if some tile is selected and we press left button, it draws the tile on current position
            Tile[][] oldMap = new Tile[map.length][];
            for(int i=0; i<map.length; i++){
                oldMap[i] = map[i].clone();
            }
            mapStack.push(oldMap);
            System.out.println("Size of stack: " + mapStack.size());
            editor.updateTile(Gdx.input.getX(), screenHeight- Gdx.input.getY());
        }else if(!rightClick && Gdx.input.isTouched(Input.Buttons.LEFT) && selectedMultipleTiles){
            //if we're not clicking right click and if the left key is clicked and we're
            Tile[][] oldMap = map.clone();
            mapStack.push(oldMap);
            System.out.println("Size of stack: " + mapStack.size());
            editor.updateTiles(Gdx.input.getX(), screenHeight - Gdx.input.getY(), selectedTileIds, ySize, xSize);
        }
    }

    private void saveMapFile(){
        try{
            PrintWriter writer = new PrintWriter("./maps/bigmap.map", "UTF-8");
            for(int i=0; i<width; i++){
                for(int j=0; j<height; j++){
                    writer.print(map[i][j].getId() + ",");
                }
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Map saved...");
    }

    private void saveMapFile(String path){
        try{
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            for(int i=0; i<width; i++){
                for(int j=0; j<height; j++){
                    writer.print(map[i][j].getId() + ",");
                }
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Map saved...");
    }

    private void loadMap(){
        BufferedReader br;
        String[] tokens;
        try{
            br = new BufferedReader(new FileReader("./maps/bigmap.map"));
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

    private void loadMap(String path){
        BufferedReader br;
        String[] tokens;
        try{
            br = new BufferedReader(new FileReader(path));
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

    private ArrayList<Integer> getMultipleTiles(int[] boxCoordinates){
        /*First we need to determine which x is smaller and y*/
        int xArray[] = new int[2];
        int yArray[] = new int[2];
        if(boxCoordinates[0] > boxCoordinates[2]){
            xArray[0] = boxCoordinates[2];
            xArray[1] = boxCoordinates[0];
        }else{
            xArray[0] = boxCoordinates[0];
            xArray[1] = boxCoordinates[2];
        }

        if(boxCoordinates[1] > boxCoordinates[3]){
            yArray[0] = boxCoordinates[3];
            yArray[1] = boxCoordinates[1];
        }else{
            yArray[0] = boxCoordinates[1];
            yArray[1] = boxCoordinates[3];

        }
        //here we get the x1, x2, y1, y2 coordinates of the box we selected, now we have to get those tiles
        ArrayList<Integer> list = new ArrayList<Integer>();
        System.out.println("xArray=" + xArray[0] + "," +xArray[1]);
        System.out.println("yArray=" + yArray[0] + "," + yArray[1]);
        for(int i=xArray[0]; i<=xArray[1]; i+=1){
            for(int j=yArray[0]; j<=yArray[1]; j+=1){
                System.out.println("Added tile to list, tile id =" + map[i][j].getId());
                list.add(map[i][j].getId());
            }
        }
        return list;
    }

    public void dispose(){
        editor = null;
    }

    private void setupBox(BoundingBox box){
        box.setTileSize(tileSize);
        box.draw();
        //selectedTileIds.addAll(getMultipleTiles(box.getCoordinates()));
        ySize = box.getSelectionHeight();
        xSize = box.getSelectionWidth();
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
