package com.example.petbird.form_login;

import java.util.List;

import com.example.petbird.entity_master.ColorEntity;
import com.example.petbird.entity_master.SexEntity;
import com.example.petbird.entity_master.SpeciesEntity;

public class UpdateSpeciesForm {

    Integer species;
    Integer sex;
    Integer color;

    
    List<SpeciesEntity> speciesList;
    List<SexEntity> sexList;
    List<ColorEntity> colorList;
    
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
