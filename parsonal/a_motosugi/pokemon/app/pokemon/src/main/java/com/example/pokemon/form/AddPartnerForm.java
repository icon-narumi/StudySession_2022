package com.example.pokemon.form;

import java.util.List;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.PokemonBean;
import com.example.pokemon.entity.PokemonEntity;

public class AddPartnerForm {

    String resultMessage;
    String trainer;
    List<PartnerBean> partnerList;
    List<PokemonBean> pokemonList;
    Integer tId;
    List<PokemonEntity> selectPokemonList;
    Integer pId;

    @Pattern(regexp = "[1-9 １-９]|[1-9 １-９][0-9 ０-９]|100|１００", message = "つよさは1-100の数値で入力してください")
    String strength;

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public List<PartnerBean> getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(List<PartnerBean> partnerList) {
        this.partnerList = partnerList;
    }

    public List<PokemonBean> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<PokemonBean> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public List<PokemonEntity> getSelectPokemonList() {
        return selectPokemonList;
    }

    public void setSelectPokemonList(List<PokemonEntity> selectPokemonList) {
        this.selectPokemonList = selectPokemonList;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }
    
    
    
}
