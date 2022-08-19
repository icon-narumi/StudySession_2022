package com.example.petbird.form_login;

import java.util.List;

import com.example.petbird.entity_master.ColorEntity;
import com.example.petbird.entity_master.SexEntity;
import com.example.petbird.entity_master.SpeciesEntity;


public class PetBirdForm {

    //<htmlより値を受取り保持>
    Integer id;
    Integer species;
    String sex;
    String color;
    Integer life;
    Integer count;
    Integer price;

    List<SpeciesEntity> speciesList;
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


       
}
