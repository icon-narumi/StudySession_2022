package com.example.pokemon.form;

import java.util.List;

//import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.ViewPartnerBean;
import com.example.pokemon.bean.NameLevelBean;

public class ResultForm {
    
    String trainer1;
    String trainer2;
    String trainer1Msg;
    String trainer2Msg;
    String level1Msg;
    String level2Msg;
    List<ViewPartnerBean> pokemonList1;
    List<ViewPartnerBean> pokemonList2;
    List<ViewPartnerBean> resultList1;
    List<ViewPartnerBean> resultList2;
    List<NameLevelBean> trainerLevelList;
    
    public String getTrainer1() {
        return trainer1;
    }
    public void setTrainer1(String trainer1) {
        this.trainer1 = trainer1;
    }
    public String getTrainer2() {
        return trainer2;
    }
    public void setTrainer2(String trainer2) {
        this.trainer2 = trainer2;
    }
    public String getTrainer1Msg() {
        return trainer1Msg;
    }
    public void setTrainer1Msg(String trainer1Msg) {
        this.trainer1Msg = trainer1Msg;
    }
    public String getTrainer2Msg() {
        return trainer2Msg;
    }
    public void setTrainer2Msg(String trainer2Msg) {
        this.trainer2Msg = trainer2Msg;
    }
    
    public String getLevel1Msg() {
        return level1Msg;
    }
    public void setLevel1Msg(String level1Msg) {
        this.level1Msg = level1Msg;
    }
    public String getLevel2Msg() {
        return level2Msg;
    }
    public void setLevel2Msg(String level2Msg) {
        this.level2Msg = level2Msg;
    }
    public List<ViewPartnerBean> getPokemonList1() {
        return pokemonList1;
    }
    public void setPokemonList1(List<ViewPartnerBean> pokemonList1) {
        this.pokemonList1 = pokemonList1;
    }
    public List<ViewPartnerBean> getPokemonList2() {
        return pokemonList2;
    }
    public void setPokemonList2(List<ViewPartnerBean> pokemonList2) {
        this.pokemonList2 = pokemonList2;
    }
    public List<ViewPartnerBean> getResultList1() {
        return resultList1;
    }
    public void setResultList1(List<ViewPartnerBean> resultList1) {
        this.resultList1 = resultList1;
    }
    public List<ViewPartnerBean> getResultList2() {
        return resultList2;
    }
    public void setResultList2(List<ViewPartnerBean> resultList2) {
        this.resultList2 = resultList2;
    }
    public List<NameLevelBean> getTrainerLevelList() {
        return trainerLevelList;
    }
    public void setTrainerLevelList(List<NameLevelBean> trainerLevelList) {
        this.trainerLevelList = trainerLevelList;
    }
    

    
    
}
