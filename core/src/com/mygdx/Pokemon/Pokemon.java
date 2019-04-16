package com.mygdx.Pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pokemon {
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

    public Pokemon(int level, int id){
        this.level = level;
        this.trainerPoints = level * 3;
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
