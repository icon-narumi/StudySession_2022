package com.example.petbird.form;

import java.util.List;

import com.example.petbird.entity.ColorEntity;
import com.example.petbird.entity.PetBirdEntity;
import com.example.petbird.entity.SexEntity;
import com.example.petbird.entity.SpeciesEntity;

import lombok.Getter;
import lombok.Setter;

import com.example.petbird.bean.PetBirdBean;

@Getter
@Setter
public class PetBirdForm {

    //<htmlより値を受取り保持>
    Integer id;
    Integer species;
    String sex;
    String color;
    Integer life;
    Integer count;
    Integer price;

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
    
     
    
}
