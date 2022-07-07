package com.example.bird.entity;

import java.sql.Timestamp;

public class TbirdEntity {

    String species;
    Integer ordo;
    Integer family;
    String volume;
    Integer food;
    //Timestamp nowTime;

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public Integer getOrdo() {
        return ordo;
    }
    public void setOrdo(Integer ordo) {
        this.ordo = ordo;
    }
    public Integer getFamily() {
        return family;
    }
    public void setFamily(Integer family) {
        this.family = family;
    }
    public String getVolume() {
        return volume;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }
    public Integer getFood() {
        return food;
    }
    public void setFood(Integer food) {
        this.food = food;
    }
    /* 
    public Timestamp getNowTime() {
        return nowTime;
    }
    public void setNowTime(Timestamp nowTime) {
        this.nowTime = nowTime;
    }
    */
    
    
}
