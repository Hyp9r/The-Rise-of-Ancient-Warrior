package com.mygdx.BattleSystem;


import com.mygdx.Pokemon.Pokemon;

public class BattleSystem {
    private Background background;
    private BattleGUI gui;
    private Pokemon[] playerPokemon, opponentPokemon;
    private int currentPokemon, currentOpponentPokemon;

    public BattleSystem(){
        background = new Background();
        gui = new BattleGUI();
        currentOpponentPokemon = 0;
        currentPokemon = 0;
        this.playerPokemon = playerPokemon;
        this.opponentPokemon = opponentPokemon;
        //this is for testing purposes

    }

    private void init(){
        for(int i=0; i<6; i++){
            Pokemon playersPokemon = new Pokemon(33, 6, "charizard");
            playerPokemon[i] = playersPokemon;
            playerPokemon[i].loadSprite(true);
            Pokemon opponent = new Pokemon(3, 18, "nidoran-m");
            opponentPokemon[i] = opponent;
            opponentPokemon[i].loadSprite(false);
        }
    }

    public void draw(){
        background.draw();
        gui.draw();
        playerPokemon[currentPokemon].draw();
        opponentPokemon[currentOpponentPokemon].draw();
    }

    public boolean buttonPressed(){
        return gui.isButtonClicked();
    }
}
