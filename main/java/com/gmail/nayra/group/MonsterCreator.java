package com.gmail.nayra.group;


public abstract class MonsterCreator {

    private int health;

    public MonsterCreator(int healthmonster){
        this.health=healthmonster;
    }
    abstract double dmg();
    abstract double kbr();

}
