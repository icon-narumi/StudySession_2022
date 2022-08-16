package com.example.petbird.form;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.petbird.bean.PetBirdBean;
import com.example.petbird.entity.ColorEntity;
import com.example.petbird.entity.PetBirdEntity;
import com.example.petbird.entity.SexEntity;
import com.example.petbird.entity.SpeciesEntity;

public class InputForm {
    
    Integer id;
    @NotEmpty(message = "絶対入力してください")
    String inputSpecies = "^[\\u30A0-\\u30FF]+$";
    Integer species;
    Integer sex;
    Integer color;
    @Digits(integer=4,fraction=0)
    Integer life;
    @Digits(integer=4,fraction=0)
    Integer count;
    @NotNull(message = "絶対入力してください")
    Integer price;

    String comment;

    
    List<PetBirdEntity> list;
    List<SpeciesEntity> speciesList;
    List<PetBirdBean> beanList;
    List<SexEntity> sexList;
    List<ColorEntity> colorList;
    

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
        public List<PetBirdEntity> getList() {
            return list;
        }
        public void setList(List<PetBirdEntity> list) {
            this.list = list;
        }
        public List<SpeciesEntity> getSpeciesList() {
            return speciesList;
        }
        public void setSpeciesList(List<SpeciesEntity> speciesList) {
            this.speciesList = speciesList;
        }
        public Integer getPrice() {
            return price;
        }
        public void setPrice(Integer price) {
            this.price = price;
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
        public String getComment() {
            return comment;
        }
        public void setComment(String comment) {
            this.comment = comment;
        }
        public String getInputSpecies() {
            return inputSpecies;
        }
        public void setInputSpecies(String inputSpecies) {
            this.inputSpecies = inputSpecies;
        }

    
}
