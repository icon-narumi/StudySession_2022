package com.example.petbird.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.example.petbird.entity.CastPetBirdEntity;
import com.example.petbird.entity.ColorEntity;
import com.example.petbird.entity.SexEntity;
import com.example.petbird.entity.SpeciesEntity;

public class UpdateForm {

    String species;
    String sex;
    String color;

    @NotEmpty(message = "入力してください")
    String life;
    @NotEmpty(message = "入力してください")
    String count;
    @NotEmpty(message = "入力してください")
    String price;
    
    List<SpeciesEntity> speciesList;
    List<SexEntity> sexList;
    List<ColorEntity> colorList;
    List<CastPetBirdEntity> list;

    
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
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public List<CastPetBirdEntity> getList() {
        return list;
    }
    public void setList(List<CastPetBirdEntity> list) {
        this.list = list;
    }
    public List<SpeciesEntity> getSpeciesList() {
        return speciesList;
    }
    public void setSpeciesList(List<SpeciesEntity> speciesList) {
        this.speciesList = speciesList;
    }
    public List<SexEntity> getSexList() {
        return sexList;
    }
    public void setSexList(List<SexEntity> sexList) {
        this.sexList = sexList;
    }
    public List<ColorEntity> getColorList() {
        return colorList;
    }
    public void setColorList(List<ColorEntity> colorList) {
        this.colorList = colorList;
    }

    
}
