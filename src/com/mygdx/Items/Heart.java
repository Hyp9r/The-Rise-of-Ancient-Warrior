package com.mygdx.Items;

public class Heart extends Item {

    private int amount;
    private int id;

    public Heart(int amount){
        super("Heart", amount);
        this.id = 0;
    }

    public int getId(){
        return this.id;
    }

}
