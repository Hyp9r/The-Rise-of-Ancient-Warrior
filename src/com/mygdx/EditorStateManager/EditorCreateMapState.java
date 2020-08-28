package com.mygdx.EditorStateManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.GUI.*;

public class EditorCreateMapState extends State{

    private Component backButton;
    private Component createButton;
    private Component mapWidthLabel = new Label("Width in tiles", 155, Gdx.graphics.getHeight() / 2 + 100, 14);
    private Component mapWidthField = new TextField("", 100, Gdx.graphics.getHeight() / 2 + 70);

    private Component mapHeightLabel = new Label("Height in tiles", 155, Gdx.graphics.getHeight() / 2 + 30, 14);
    private Component mapHeightField = new TextField("", 100, Gdx.graphics.getHeight() / 2);

    private Component tileSizeLabel = new Label("Tile size", 155, Gdx.graphics.getHeight() / 2 - 50, 14);
    private Component tileSizeField = new TextField("", 100, Gdx.graphics.getHeight() / 2 - 80);

    private ShapeRenderer shapeRenderer;

    public EditorCreateMapState(EditorStateManager esm){
        super(esm);
    }

    @Override
    public void init() {

        shapeRenderer = new ShapeRenderer();

        createButton = new TransparentButton(120, 150, "CREATE MAP");
        ((TransparentButton) createButton).addClickListener(() -> {
            int tileSize = Integer.parseInt(((TextField) tileSizeField).getText());
            int mapWidth = Integer.parseInt(((TextField) mapWidthField).getText());
            int mapHeight = Integer.parseInt(((TextField) mapHeightField).getText());
            System.out.println("Tilesize: " + tileSize + " Map width: " + mapWidth + " Map height: " + mapHeight);
            esm.setState(EditorStateManager.EDITOR);
        });

        backButton = new ImageButton(20, 500, 48, 48, "./icons/back-normal.png", "./icons/back-hover.png");
        ((ImageButton) backButton).addClickListener(() -> {
            esm.setState(EditorStateManager.START);
        });

        ((TextField) mapWidthField).addClickListener(() -> {
           ((TextField) mapWidthField).setFocused();
        });

        ((TextField) mapHeightField).addClickListener(() -> {
            ((TextField) mapHeightField).setFocused();
        });

        ((TextField) tileSizeField).addClickListener(() -> {
            ((TextField) tileSizeField).setFocused();
        });
    }

    @Override
    public void update() {
        createButton.update();
        backButton.update();
        mapHeightField.update();
        mapWidthField.update();
        tileSizeField.update();
    }

    @Override
    public void draw() {
        drawBackground();

        createButton.draw();
        backButton.draw();

        mapHeightField.draw();
        mapHeightLabel.draw();

        mapWidthField.draw();
        mapWidthLabel.draw();

        tileSizeField.draw();
        tileSizeLabel.draw();
    }

    private void drawBackground(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.2f,0.6f, 0.73f,1.0f);
        shapeRenderer.rect(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
    }

    @Override
    public void handleInput() {

    }
}
