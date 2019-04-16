package com.mygdx.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.Items.Item;
import com.mygdx.Pokemon.Pokemon;
import com.mygdx.TileMap.Map;
import com.mygdx.TileMap.TileType;
import com.mygdx.game.MyGdxGame;

import java.util.List;

public class Player {

    /*Player data*/
    private int health;
    private Bag playerBag;
    private List<Pokemon> pokemonList;
    private StateTypes state; //walking, not walking, talking

    /*Player graphics data*/
    private int playerWidth = 36;
    private int playerHeight = 55;
    private int playerFacing = 0; //0 for up, 1 for down, 2 for right, 3 for left
    private Texture texture;

    /*Player animation stuff*/
    private Animation<TextureRegion> animationUp;
    private Animation<TextureRegion> animationDown;
    private Animation<TextureRegion> animationRight;
    private Animation<TextureRegion> animationLeft;
    private TextureRegion[] upRegion;
    private TextureRegion[] downRegion;
    private TextureRegion[] leftRegion;
    private TextureRegion[] rightRegion;
    private float elapsed;

    /*Player texture split into 16 pieces*/
    private TextureRegion[] regions = new TextureRegion[16];

    /*Map data*/
    private Map map;
    private int tileSize = 16;
    private int playerX;
    private int playerY;

    /*Used to draw player onto the screen*/
    private SpriteBatch batch;


    public Player(){
        this.health = 1000;
        this.playerBag = new Bag();
        init();
    }

    private void init(){
        this.state = StateTypes.IDLE;
        playerX = 320;
        playerY = 256;
        texture = new Texture("spritest.png");
        batch = new SpriteBatch();
        upRegion = new TextureRegion[4];
        downRegion = new TextureRegion[4];
        leftRegion = new TextureRegion[4];
        rightRegion = new TextureRegion[4];
        loadPlayerTextures();
        animationDown = new Animation<TextureRegion>(2f/5f, downRegion);
        animationUp = new Animation<TextureRegion>(2f/5f, upRegion);
        animationLeft = new Animation<TextureRegion>(2f/5f, leftRegion);
        animationRight = new Animation<TextureRegion>(2f/5f, rightRegion);
    }

    private void loadPlayerTextures(){
        for(int i=0; i<16; i++){
            regions[i] = new TextureRegion(texture, i*36, 0, 36, 55);
        }
        for(int i=0; i<4; i++){
            downRegion[i] = regions[i];
            upRegion[i] = regions[i+4];
            leftRegion[i] = regions[i+8];
            rightRegion[i] = regions[i+12];
        }
    }

    public void draw(){
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        batch.begin();
        //I need to draw only the frames that I'm moving at
        if(playerFacing == 1){
            batch.draw(animationDown.getKeyFrame(elapsed, true), playerX, playerY, playerWidth* MyGdxGame.scale, playerHeight*MyGdxGame.scale);
            setPlayerFacing(4);
        }else if(playerFacing == 0){
            batch.draw(animationUp.getKeyFrame(elapsed,true), playerX, playerY, playerWidth* MyGdxGame.scale, playerHeight*MyGdxGame.scale);
            setPlayerFacing(5);
        }else if(playerFacing == 2){
            batch.draw(animationRight.getKeyFrame(elapsed, true), playerX, playerY, playerWidth* MyGdxGame.scale, playerHeight*MyGdxGame.scale);
            setPlayerFacing(6);
        }else if(playerFacing == 3){
            batch.draw(animationLeft.getKeyFrame(elapsed, true), playerX, playerY, playerWidth* MyGdxGame.scale, playerHeight*MyGdxGame.scale);
            setPlayerFacing(7);
        }
        //0->up, 1->down, 2 -> right, 3->left
        if(state == StateTypes.IDLE)
            batch.draw(regions[getIdleSprite()], playerX, playerY, playerWidth* MyGdxGame.scale, playerHeight*MyGdxGame.scale);
        batch.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private int getIdleSprite(){
        int idleSpriteIndex = 0;
        switch (playerFacing){
            case 5:
                idleSpriteIndex = 4;
                break;
            case 4:
                idleSpriteIndex = 0;
                break;
            case 7:
                idleSpriteIndex = 8;
                break;
            case 6:
                idleSpriteIndex = 12;
                break;
        }
        return idleSpriteIndex;
    }

    public void setState(StateTypes type){
        this.state = type;
    }

    public Bag getBag(){
        return this.playerBag;
    }

    public void usePotion(){
        for(Item item : playerBag.getListOfItems()){
            if(item.getName().equals("Potion")){
                item.useOne();
                if(item.getAmount() < 1){
                    playerBag.getListOfItems().remove(item);
                }
                health+= 100;
                break;
            }
        }
    }

    public void addPokemon(Pokemon pokemon){
        if(pokemonList.size() < 6)
            this.pokemonList.add(pokemon);
    }

    public List<Pokemon> getPokemonList(){
        return this.pokemonList;
    }

    public int getHealth(){
        return this.health;
    }

    public int getX(){
        return this.playerX;
    }

    public int getY(){
        return this.playerY;
    }

    public void setMap(Map map){
        this.map = map;
    }

    public void setPlayerFacing(int facing){
        this.playerFacing = facing;
    }

    private boolean isColliding(int coordinate1, int coordinate2){
        if(map.getTile(coordinate1, coordinate2).getType() == TileType.NORMAL){
            return false;
        }else if(map.getTile(coordinate1, coordinate2).getType() == TileType.BLOCKED){
            return true;
        }
        return false;
    }

    private boolean isTryingToSwim(int coordinate1, int coordinate2){
        if(map.getTile(coordinate1, coordinate2).getType() == TileType.WATER) {
            return true;
        }
        return false;
    }

    public boolean isStandingOnWrap(){
        if(map.getTile(playerX, playerY).getType() == TileType.WRAP){
            return true;
        }
        return false;
    }

    public void moveUp(){
        //TODO: Fix the movement, this is just temporary
        if(this.state == StateTypes.WALKING){
            //can't walk then need to wait for walking to end
        }else{
            setPlayerFacing(0);
            this.state = StateTypes.WALKING;
            //do all the walking stuff
            if(isColliding(playerX+5, playerY + 1)){
                playerY-=16;
            }else if(isTryingToSwim(playerX, playerY + 1)){
                System.out.println("You can't swim yet!");
                playerY-=16;
            }else{
                playerY++;
                //get the current tile that player stands on and then calculate then next tile and place player on it
                int getPlayerTileYIndex = playerY / tileSize;
                //playerY = getPlayerTileYIndex + 16;
                this.state = StateTypes.IDLE;
            }
        }
    }

    public void moveDown(){
        //TODO: Fix the movement, this is just temporary
        this.state = StateTypes.WALKING;
        setPlayerFacing(1);
        if(isColliding(playerX-10, playerY - 1)){
            playerY++;
        }else if(isTryingToSwim(playerX, playerY - 1)){
            System.out.println("You can't swim yet!");
            playerY++;
        }else{
            playerY--;
        }
        this.state = StateTypes.IDLE;
    }

    public void moveLeft(){
        //TODO: Fix the movement, this is just temporary
        this.state = StateTypes.WALKING;
        setPlayerFacing(3);
        if(isColliding(playerX+6, playerY)){
            playerX++;
        }else if (isTryingToSwim(playerX-1, playerY)){
            System.out.println("You can't swim yet!");
            playerX++;
        }else{
            playerX--;
        }
        this.state = StateTypes.IDLE;
    }

    public void moveRight(){
        //TODO: Fix the movement, this is just temporary
        this.state = StateTypes.WALKING;
        setPlayerFacing(2);
        if(isColliding(playerX+30, playerY)){
            playerX--;
        }else if(isTryingToSwim(playerX+6, playerY)){
            System.out.println("You can't swim yet!");
            playerX--;
        }else{
            playerX++;
        }
        this.state = StateTypes.IDLE;
    }

}
