package com.mygdx.Characters;

import com.mygdx.Items.Item;

import java.util.ArrayList;

public class Bag {

    private ArrayList<Item> items;
    private int MAX_ITEMS = 100;

    public Bag(){
        init();
    }

    private void init(){
        items = new ArrayList<Item>();
    }

    public void addItem(Item item){
        if(items.size() < MAX_ITEMS)
            this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public ArrayList<Item> getListOfItems(){
        return this.items;
    }

}
