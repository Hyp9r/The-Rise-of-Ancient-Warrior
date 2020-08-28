package com.mygdx.TileMap;

public class Tile {

    private TileType type;
    private int id;
    private int x;
    private int y;

    public Tile(TileType type, int id){
        this.type = type;
        this.id = id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setType(TileType type){
        this.type = type;
    }

    public TileType getType(){
        return this.type;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getX(){
        return this.x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return this.y;
    }


}
