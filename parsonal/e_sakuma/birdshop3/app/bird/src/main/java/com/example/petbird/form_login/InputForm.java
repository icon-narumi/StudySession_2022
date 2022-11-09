package com.example.petbird.form_login;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.example.petbird.entity_master.ColorEntity;
import com.example.petbird.entity_master.SexEntity;
import com.example.petbird.entity_master.SpeciesEntity;

public class InputForm {

    Integer id;

    @Pattern(regexp = "^[\\u30A0-\\u30FF]+$", message = "全角カタカナで入力して下さい")
    String inputSpecies;

    Integer species;
    Integer sex;
    Integer color;
    @NotNull(message = "絶対入力してください")
    @Digits(integer=4,fraction=0)
    Integer life;
    @NotNull(message = "絶対入力してください")
    @Digits(integer=4,fraction=0)
    Integer count;
    @NotNull(message = "絶対入力してください")
    Integer price;

    String comment;

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
