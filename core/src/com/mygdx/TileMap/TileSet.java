package com.mygdx.TileMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.GUI.Component;

public class TileSet extends Component {

    private Texture texture;
    private TextureRegion[] tileRegions;
    private SpriteBatch batch;
    private int tileSize;
    private int gap;

    public TileSet(String path, int tilesize, int gap){
        this.tileSize = tilesize;
        this.batch = new SpriteBatch();
        this.gap = gap;
        loadTexture(path);
        int numOfTiles = calculateNumberOfTileRegions();
        this.tileRegions = new TextureRegion[numOfTiles];
        loadTileSet();
    }

    private void loadTexture(String path){
        this.texture = new Texture(path);
    }

    private void loadTileSet(){
        for(int i=0; i<tileRegions.length; i++){
            tileRegions[i] = new TextureRegion(texture, i*tileSize, 0,tileSize,tileSize);
        }
    }

    private int calculateNumberOfTileRegions(){
        int number = 0;
        number = texture.getWidth() / tileSize;
        return number;
    }

    private void drawTileset(){
        int coord = 0;
        batch.begin();
        for(int i=0; i<tileRegions.length; i++){
            batch.draw(tileRegions[i],1440-tileSize, coord);
            coord +=tileSize + gap;
        }
        batch.end();
    }

    public void draw(){
        drawTileset();
    }
}
