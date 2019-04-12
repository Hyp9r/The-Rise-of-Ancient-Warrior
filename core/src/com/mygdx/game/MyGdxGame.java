package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Characters.Player;
import com.mygdx.Items.Coin;
import com.mygdx.Items.Heart;
import com.mygdx.Items.Potion;
import com.mygdx.TileMap.Map;

public class MyGdxGame extends ApplicationAdapter{
	SpriteBatch batch;
	private Player player;
	private Map map;
	private final int MENU_STATE = 1;
	private final int GAME_STATE = 0;
	private final int INVENTORY_STATE = 2;
	private int gameState;
	private Menu menu;
	public static boolean grid = false;


	/*Sound for the game*/
	Sound sound;


	/*Scaling the image*/
	public static int scale;

	@Override
	public void create () {
		scale = 1;
		gameState = GAME_STATE;
		menu = new Menu();
		batch = new SpriteBatch();
		player = new Player();
		player.getBag().addItem(new Heart(5));
		player.getBag().addItem(new Coin(1));
		player.getBag().addItem(new Potion(5));
		menu.setPlayer(player);
		map = new Map(640,640,16);
		map.loadTileSet();
		map.loadMapFromAFile();
		player.setMap(map);//temporary solution this would only work if I never change map since this only loads at the beginning of the game
		//load sound
		sound = Gdx.audio.newSound(Gdx.files.internal("./sounds/palletTown.mp3"));
		long id = sound.play(1.0f);
		sound.setLooping(id, true);
	}

	@Override
	public void render () {
		BitmapFont font = new BitmapFont();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		map.draw();
		player.draw();
		if(gameState == MENU_STATE){
			menu.draw();
			if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
				menu.updateCurrentChoice(Input.Keys.D);
			}else if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
				menu.updateCurrentChoice(Input.Keys.A);
			}else if(Gdx.input.isKeyPressed(Input.Keys.X)){
				gameState = INVENTORY_STATE;
			}
		}else if(gameState == INVENTORY_STATE){
			menu.openCurrentChoice();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.W) && gameState == GAME_STATE) {
			player.moveUp();
			wrapPlayer();
		} else if (Gdx.input.isKeyPressed(Input.Keys.S) && gameState == GAME_STATE) {
			player.moveDown();
			wrapPlayer();
		} else if (Gdx.input.isKeyPressed(Input.Keys.D) && gameState == GAME_STATE) {
			player.moveRight();
			wrapPlayer();
		} else if (Gdx.input.isKeyPressed(Input.Keys.A) && gameState == GAME_STATE) {
			player.moveLeft();
			wrapPlayer();
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
			if(gameState != MENU_STATE)
				gameState = MENU_STATE;
			else
				gameState = GAME_STATE;
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
			player.usePotion();
			batch.begin();
			font.setColor(Color.BLACK);
			font.draw(batch, "+100 HP", player.getX()-30, player.getY() + 90);
			batch.end();
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.G)){
			grid = !grid;
		}
	}


	private void wrapPlayer(){
		if(player.isStandingOnWrap()){
			//map.loadMap2();
			map.loadMap();
			player.setMap(map);
			map.draw();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		sound.dispose();
	}
}
