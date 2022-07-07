package com.example.petbird.entity;

//無加工のDBからの値を保持
public class PetBirdEntity {
    
    Integer id;
    Integer species;
    Integer sex;
    Integer color;
    Integer life;
    Integer count;
    Integer price;

    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getSpecies() {
        return species;
    }
    public void setSpecies(Integer species) {
        this.species = species;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getColor() {
        return color;
    }
    public void setColor(Integer color) {
        this.color = color;
    }
    public Integer getLife() {
        return life;
    }
    public void setLife(Integer life) {
        this.life = life;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    
}
