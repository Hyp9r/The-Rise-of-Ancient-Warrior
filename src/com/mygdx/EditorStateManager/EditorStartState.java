package com.mygdx.EditorStateManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.GUI.*;
import javax.swing.*;
import java.io.File;

public class EditorStartState extends State {

    private Component newMapButton;
    private Component loadMapButton;
    private Component credits = new Label("Made by tkeskic", 155, 50, 12);
    private Component versionLabel = new Label("Version 0.2", 165, 35, 12);
    private Component title = new Label("Game Editor", 120, 430, 30);
    private Component icon = new ImageBox("./icons/icon.png", 165, 480);


    private TransparentButton tr = new TransparentButton(125, 380, "NEW MAP");

    private ShapeRenderer shapeRenderer = new ShapeRenderer();


    public EditorStartState(EditorStateManager esm) {
        super(esm);
    }

    @Override
    public void init(){
        newMapButton = new Button(100, 320, "NEW MAP");
        ((Button) newMapButton).addClickListener(() -> {
            //draw panel with few textboxes, [height, width, tilesize]
            ((Button) newMapButton).setVisible(false);
            ((Button) loadMapButton).setVisible(false);
            //Gdx.graphics.setWindowedMode(1440, 850);
            esm.setState(EditorStateManager.CREATE_MAP);
        });

        loadMapButton = new Button(100, 250, "LOAD MAP");
        ((Button) loadMapButton).addClickListener(() -> {
            JFileChooser fileChooser = new JFileChooser();
            int pathToAFile = fileChooser.showOpenDialog(null);
            if(pathToAFile == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                //loadMap(path);
            }
        });

        tr.addClickListener(() -> {
            System.out.println("da");
        });


        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void update(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            ((Button) newMapButton).setVisible(true);
            ((Button) loadMapButton).setVisible(true);
        }
        newMapButton.update();
        loadMapButton.update();
    }

    @Override
    public void draw(){

        drawBackground();

        credits.draw();
        versionLabel.draw();
        title.draw();
        icon.draw();

        newMapButton.draw();
        loadMapButton.draw();

        tr.draw();
    }

    public void handleInput(){

    }

    private void drawBackground(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.2f,0.6f, 0.73f,1.0f);
        shapeRenderer.rect(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
    }

    public void dispose(){
        newMapButton = null;
        loadMapButton = null;
        credits = null;
        versionLabel = null;
        title = null;
        icon = null;

        tr = null;

        shapeRenderer = null;
    }
}
