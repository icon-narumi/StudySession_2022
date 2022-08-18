package com.example.petbird.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.example.petbird.entity.CastPetBirdEntity;
import com.example.petbird.entity.ColorEntity;
import com.example.petbird.entity.SexEntity;
import com.example.petbird.entity.SpeciesEntity;

public class UpdateForm {

    Integer species;
    Integer sex;
    Integer color;

    @NotEmpty(message = "入力してください")
    Integer life;
    @NotEmpty(message = "入力してください")
    Integer count;
    @NotEmpty(message = "入力してください")
    Integer price;
    
    List<SpeciesEntity> speciesList;
    List<SexEntity> sexList;
    List<ColorEntity> colorList;
    List<CastPetBirdEntity> list;

    String comment;

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
    public List<CastPetBirdEntity> getList() {
        return list;
    }
    public void setList(List<CastPetBirdEntity> list) {
        this.list = list;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    
        
}
