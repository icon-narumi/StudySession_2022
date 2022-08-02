package com.example.petbird.form;

import java.util.List;

import com.example.petbird.entity.ColorEntity;
import com.example.petbird.entity.SexEntity;
import com.example.petbird.entity.SpeciesEntity;

public class UpdateForm {
    
    List<SpeciesEntity> speciesList;
    List<SexEntity> sexList;
    List<ColorEntity> colorList;

    
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
