package com.mygdx.Items;

public interface Consumable {
    default boolean consumeItem(){
        return true;
    }
}
