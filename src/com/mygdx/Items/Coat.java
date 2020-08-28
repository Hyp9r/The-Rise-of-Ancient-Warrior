package com.mygdx.Items;

public class Coat extends Item{

    private int amount;
    private int id;


    public Coat(int amount){
        super("Coat", amount);
        this.id = 2;
    }

    public int getId(){
        return this.id;
    }
}
