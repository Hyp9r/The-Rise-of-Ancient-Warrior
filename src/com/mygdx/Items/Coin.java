package com.mygdx.Items;

public class Coin extends Item {


    private int id;
    private int amount;

    public Coin(int amount){
        super("Coin", amount);
        this.id = 1;

    }


    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
}
