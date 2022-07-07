package com.example.bird.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.example.bird.entity.FamilyEntity;
import com.example.bird.entity.FoodEntity;
import com.example.bird.entity.OrdoEntity;

public class InputForm {
    
    @NotEmpty(message = "絶対入力してください")
    String species;
    Integer ordo;
    Integer family;
    //String→数字以外のものが入るとエラーを起こす
    @Pattern(regexp = "^\\d{1,4}$", message = "1~4桁の数字で入力してください")
    String volume;
    Integer food;
    
    String comment;

    //OrdoEntity用List(Output専用)
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
