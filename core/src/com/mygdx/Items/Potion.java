package com.mygdx.Items;

public class Potion extends Item{

    private int id;
    private int amount;

    public Potion(int amount){
        super("Potion", amount);
        this.amount = amount;
        this.id = 2;
    }

    public int getId(){
        return  this.id;
    }

    public void useOne(){
        if(this.amount > 0){
            this.amount--;
        }
    }

    public int getAmount(){
        return this.amount;
    }
}
