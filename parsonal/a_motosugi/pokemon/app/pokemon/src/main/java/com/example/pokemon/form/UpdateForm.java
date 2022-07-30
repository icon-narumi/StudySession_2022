package com.example.pokemon.form;

import java.util.List;

//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

//import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.ViewPartnerBean;

public class UpdateForm {

    String resultMessage;
    String trainer;
    List<ViewPartnerBean> partnerList;
    Integer tId;
    List<ViewPartnerBean> selectPokemonList;
    Integer id;

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

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public List<ViewPartnerBean> getSelectPokemonList() {
        return selectPokemonList;
    }

    public void setSelectPokemonList(List<ViewPartnerBean> selectPokemonList) {
        this.selectPokemonList = selectPokemonList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }
    
    
}








    
    

