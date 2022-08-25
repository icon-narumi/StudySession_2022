package com.example.petbird.bean;

import java.util.List;

public class StrPetBirdBean {
    
    String id;
    String species;
    String sex;
    String color;
    String price;
    String life;
    String count;
    List<CountBean> countBeanList;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getLife() {
        return life;
    }
    public void setLife(String life) {
        this.life = life;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public List<CountBean> getCountBeanList() {
        return countBeanList;
    }
    public void setCountBeanList(List<CountBean> countBeanList) {
        this.countBeanList = countBeanList;
    }
   

}
