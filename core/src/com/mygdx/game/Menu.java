package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.Characters.Player;
import com.mygdx.Items.Item;

public class Menu {


    private final int STATS_MENU = 0;
    private final int SAVE_MENU = 1;
    private final int BAG_MENU = 2;

    private TextureRegion[] region;
    private Texture texture;
    private Texture inventoryGridTexture;
    private SpriteBatch batch;
    private int currentChoice = STATS_MENU;
    private BitmapFont font;
    private String[] menuOptions = {"STATS", "SAVE", "BAG", "CHARACTER", "CONTROLS", "EXIT"};


    private Player player;


    //inventory stuff
    private TextureRegion[] itemsRegions;
    private Texture itemsTexture;

    public Menu(){
        region = new TextureRegion[3];
        itemsRegions = new TextureRegion[3];
        texture = new Texture("menu.png");
        itemsTexture = new Texture("items.png");
        inventoryGridTexture = new Texture("inventory.png");
        region[1] = new TextureRegion(texture, 167, 72,60, 94);
        region[0] = new TextureRegion(texture, 355, 82,92, 84);
        region[2] = new TextureRegion(texture, 560, 82, 80, 80);

        itemsRegions[0] = new TextureRegion(itemsTexture, 40, 60, 14,14); //heart
        itemsRegions[1] = new TextureRegion(itemsTexture, 68, 60, 14,14); //coin
        itemsRegions[2] = new TextureRegion(itemsTexture, 40, 90, 14,14); //potion

        batch = new SpriteBatch();
        font = new BitmapFont();

    }

    public void draw(){
        batch.begin();
        batch.draw(region[currentChoice], 480,550);
        font.setColor(Color.BLACK);
        font.draw(batch, menuOptions[currentChoice], 505, 540);
        batch.end();
    }

    public void updateCurrentChoice(int key){
        if(key == Input.Keys.D){
            if(currentChoice == 2){
                currentChoice = 0;
            }else {
                currentChoice++;
            }
        }else if(key == Input.Keys.A){
            if(currentChoice == 0){
                currentChoice = 2;
            }else{
                currentChoice--;
            }
        }
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void openCurrentChoice(){
        int x = 390;
        int y = 576;
        if(currentChoice == BAG_MENU){
            batch.begin();
            batch.draw(inventoryGridTexture, 380, 176);
            for(Item item : player.getBag().getListOfItems()){
                batch.draw(itemsRegions[item.getId()], x,y);
                font.draw(batch, "x" + item.getAmount(), x, y);
                x+= 32;
            }
            batch.end();
        }else if(currentChoice == STATS_MENU){
            batch.begin();
            font.draw(batch, "STATS", 10, 10);
            batch.end();
        }else if(currentChoice == SAVE_MENU){
            batch.begin();
            font.draw(batch, "Saving...", 400, 400);
            batch.end();
        }
    }

}
