package com.example.bird.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.example.bird.entity.FamilyEntity;
import com.example.bird.entity.FoodEntity;
import com.example.bird.entity.OrdoEntity;
import com.example.bird.entity.StrBirdEntity;

public class UpdateForm {

    @NotEmpty(message = "入力してください")
    String species;
    Integer ordo;
    Integer family;
    @NotEmpty(message = "入力してください")
    String volume;
    Integer food;

    
    String comment;
    
    List<StrBirdEntity> list;

    private List<OrdoEntity> ordoList;
    private List<FamilyEntity> familyList;
    private List<FoodEntity> foodList;

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
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public List<StrBirdEntity> getList() {
        return list;
    }
    public void setList(List<StrBirdEntity> list) {
        this.list = list;
    }
    public List<OrdoEntity> getOrdoList() {
        return ordoList;
    }
    public void setOrdoList(List<OrdoEntity> ordoList) {
        this.ordoList = ordoList;
    }
    public List<FamilyEntity> getFamilyList() {
        return familyList;
    }
    public void setFamilyList(List<FamilyEntity> familyList) {
        this.familyList = familyList;
    }
    public List<FoodEntity> getFoodList() {
        return foodList;
    }
    public void setFoodList(List<FoodEntity> foodList) {
        this.foodList = foodList;
    }
    
    

    

}
