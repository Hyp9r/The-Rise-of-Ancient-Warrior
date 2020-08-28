package com.mygdx.GameStateManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Characters.Player;
import com.mygdx.Items.Coin;
import com.mygdx.Items.Heart;
import com.mygdx.Items.Potion;
import com.mygdx.Pokemon.Pokemon;
import com.mygdx.TileMap.Map;

import java.util.Random;

public class GameState extends State{

    private Player player;
    private Map map;
    SpriteBatch batch;
    /*Sound for the game*/
    Sound sound;

    private boolean grid;


    public GameState(GameStateManager gsm){
        super(gsm);
    }

    public void init(){
        batch = new SpriteBatch();
        grid = false;
        player = new Player();
        player.getBag().addItem(new Heart(5));
        player.getBag().addItem(new Coin(1));
        player.getBag().addItem(new Potion(5));
        player.addPokemon(new Pokemon(1,3, "charizard"));
        //menu.setPlayer(player);
        map = new Map(1088,768,16);
        map.loadTileSet();
        map.loadMapFromAFile();
        player.setMap(map);//temporary solution this would only work if I never change map since this only loads at the beginning of the game
        //load sound
        sound = Gdx.audio.newSound(Gdx.files.internal("./sounds/palletTown.mp3"));
        long id = sound.play(1.0f);
        sound.setLooping(id, true);
    }

    public void update(){

    }

    public void draw(){
        map.draw();
        player.draw();
        handleInput();
    }


    private void wrapPlayer(){
        if(player.isStandingOnWrap()){
            //map.loadMap2();
            map.loadMap();
            player.setMap(map);
            map.draw();
        }else if(player.isStandingOnGrass()){
            if(checkIfPlayerFoundPokemon()){
                //that means that player has found pokemon
                //gameState = MATCH_STATE;
                gsm.setState(GameStateManager.BATTLE);
            }
        }
    }

    private boolean checkIfPlayerFoundPokemon(){
        //generate number
        int pokemon = generateNumber(1000000);
        int shiny = generateNumber(3000000);
        if(checkPokemonNumberOnCurrentMap(pokemon).equals("")){
            System.out.println("No pokemon found");
            return false;
        }else{
            System.out.println(checkPokemonNumberOnCurrentMap(pokemon));
            return true;
        }

    }

    private int generateNumber(int range){
        Random rand = new Random();
        int number = rand.nextInt(range);
        return number;
    }

    private String checkPokemonNumberOnCurrentMap(int pokemonCode){
        String pokemon = "";
        switch (map.getMapName()){
            case "./maps/bigmap.map":
                if(pokemonCode > 9000 && pokemonCode < 10000){
                    pokemon = "Pidgy";
                }else if(pokemonCode == 67){
                    //something rare like scyther
                    pokemon = "Scyther";
                }else if(pokemonCode > 300000 && pokemonCode < 300100){
                    //something bit more rare then Pidgy
                    pokemon = "Nidoran";
                }else if(pokemonCode > 921240 && pokemonCode < 922000){
                    pokemon = "Pikachu";
                }
                break;
        }
        return pokemon;
    }

    public void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveUp();
            player.moveRight();
            wrapPlayer();
        }else if(Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.A)){
            player.moveUp();
            player.moveLeft();
            wrapPlayer();
        }else if(Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.A)){
            player.moveDown();
            player.moveLeft();
            wrapPlayer();
        }else if(Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D)){
            player.moveDown();
            player.moveRight();
            wrapPlayer();
        }else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveUp();
            wrapPlayer();
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveDown();
            wrapPlayer();
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveRight();
            wrapPlayer();
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveLeft();
            wrapPlayer();
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            //handle in game menu, bag system and such...
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.G)){
            grid = !grid;
        }else if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            //fire the lighting
            player.attack();
        }
    }
}
