package com.example.pokemon.form;

import java.util.List;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.PokemonBean;
import com.example.pokemon.bean.ViewPartnerBean;
import com.example.pokemon.entity.PokemonEntity;
import com.example.pokemon.entity.TypeEntity;

public class AddPartnerForm {

    String resultMessage;
    String trainer;
    List<ViewPartnerBean> partnerList;
    List<PokemonBean> pokemonList;
    Integer tId;
    List<PokemonEntity> selectPokemonList;
    Integer pId;
    //String attackType;
    List<TypeEntity> typeList;
    Integer typeId;

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

    public List<ViewPartnerBean> getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(List<ViewPartnerBean> partnerList) {
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

    /*public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }*/

    public List<TypeEntity> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeEntity> typeList) {
        this.typeList = typeList;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    
    
    
}
