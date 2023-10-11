package com.mygdx.game.actors;


public abstract class Actors {

    private String name;
    private int life;
    private int maxLife;
    private int stamina;
    private int maxStamina;

    public Actors(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }
    public void setLife(int life){
        this.life=life;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public boolean  isLive() {
        return this.life > 0 ;
    }

    public float attackCost(int cost, float distance, float distMin, float coeffDist) {
        return cost + ((distance - distMin) * coeffDist);
    }


}

