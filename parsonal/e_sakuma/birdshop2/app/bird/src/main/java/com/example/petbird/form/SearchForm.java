package com.example.petbird.form;

import java.util.ArrayList;
import java.util.List;

import com.example.petbird.bean.PetBirdBean;
import com.example.petbird.entity.CastPetBirdEntity;
import com.example.petbird.entity.ColorEntity;
import com.example.petbird.entity.SexEntity;
import com.example.petbird.entity.SpeciesEntity;

public class SearchForm {
    
    Integer[] id;
    Integer species;
    Integer sex;
    Integer color;
    Integer price;
    Integer count;
    String  total;

    String comment;

    List<SexEntity> sexList;
    List<ColorEntity> colorList;
    List<SpeciesEntity> speciesList;
    List<PetBirdBean> beanList;

   

    
   
    public Integer[] getId() {
        return id;
    }
    public void setId(Integer[] id) {
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
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public List<SpeciesEntity> getSpeciesList() {
        return speciesList;
    }
    public void setSpeciesList(List<SpeciesEntity> speciesList) {
        this.speciesList = speciesList;
    }
    public List<PetBirdBean> getBeanList() {
        return beanList;
    }
    public void setBeanList(List<PetBirdBean> beanList) {
        this.beanList = beanList;
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
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }
            

}
