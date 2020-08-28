package com.mygdx.Pokemon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pokemon {
    private Texture texture;
    private String name;
    private int attack;
    private int defence;
    private int speed;
    private int specialAttack;
    private int specialDefence;
    private int level;
    private int experience;
    private int id;
    private int trainerPoints;

    //drawing
    private int x, y;
    private SpriteBatch batch;

    public Pokemon(int level, int id, String name){
        this.level = level;
        this.trainerPoints = level * 3;
        this.name = name;
        init();
    }

    private void init(){
        batch = new SpriteBatch();
    }

    public void loadSprite(boolean playerOwns){
        //if the player owns that pokemon draw back sprite else draw front
        if(playerOwns){
            texture = new Texture(  "./pokemon-sprites/back/" + name + "_back.png");
            x = 45;
            y = 268;
        }else{
            texture = new Texture( "./pokemon-sprites/front/" + name + "_front.png");
            x = 700;
            y = 300;
        }
    }

    private void loadPokemonInfo(){
        BufferedReader br;
        String[] tokens;
        try{
            br = new BufferedReader(new FileReader("./pokemon/pokemon.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            everything = everything.substring(0, everything.length()-1);
            tokens = everything.split(";");
            }catch(IOException e){
                e.printStackTrace();
            }
    }

    public void draw(){
        batch.begin();
        batch.draw(texture, x, y, 165, 190);
        batch.end();
    }

    public void setAttack(){
        this.attack++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefence() {
        this.defence++;
    }

    public void setSpeed() {
        this.speed++;
    }

    public void setSpecialAttack() {
        this.specialAttack++;
    }

    public void setSpecialDefence() {
        this.specialDefence++;
    }

    public void setLevel() {
        this.level++;
    }

    public void setExperience(int experience) {
        this.experience += experience;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrainerPoints(int trainerPoints) {
        this.trainerPoints = trainerPoints;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefence() {
        return specialDefence;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getId() {
        return id;
    }

    public int getTrainerPoints() {
        return trainerPoints;
    }
}
