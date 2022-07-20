package com.example.pokemon.form;

import java.util.List;

//import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.ViewPartnerBean;
import com.example.pokemon.entity.TrainerMasterEntity;

public class PartnerForm {

    List<TrainerMasterEntity> trainerList;
    Integer tId;
    String trainer;
    List<ViewPartnerBean> pokemonList;

    public List<TrainerMasterEntity> getTrainerList() {
        return trainerList;
    }
    public void setTrainerList(List<TrainerMasterEntity> trainerList) {
        this.trainerList = trainerList;
    }
    public Integer gettId() {
        return tId;
    }
    public void settId(Integer tId) {
        this.tId = tId;
    }
    public String getTrainer() {
        return trainer;
    }
    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
    public List<ViewPartnerBean> getPokemonList() {
        return pokemonList;
    }
    public void setPokemonList(List<ViewPartnerBean> pokemonList) {
        this.pokemonList = pokemonList;
    }
    
}
