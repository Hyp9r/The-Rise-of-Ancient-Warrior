package com.mygdx.GameStateManager;

import com.mygdx.BattleSystem.BattleSystem;
import com.mygdx.Pokemon.Pokemon;

public class BattleState extends State{

    private BattleSystem battleSystem;

    private Pokemon[] player,opponent;

    public BattleState(GameStateManager gsm){
        super(gsm);
        this.player = player;
        this.opponent = opponent;
    }

    @Override
    public void init() {
        battleSystem = new BattleSystem();
    }

    @Override
    public void update() {

    }


    @Override
    public void draw() {
        battleSystem.draw();
        if(battleSystem.buttonPressed()){
            gsm.setState(GameStateManager.GAME);
        }
    }

    @Override
    public void handleInput() {

    }
}
