package com.mygdx.Items;

public class Item {

    private String name;
    private int amount;
    private int id;

    public Item(String name, int amount){
        this.amount = amount;
        this.name = name;
    }

    public Item(){

    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public void useOne(){
        if(this.amount > 0){
            this.amount--;
        }
    }

    public int getAmount(){
        return this.amount;
    }

    public int getId(){
        return this.id;
    }

}
