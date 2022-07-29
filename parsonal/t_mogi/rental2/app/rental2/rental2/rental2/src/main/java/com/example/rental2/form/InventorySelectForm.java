package com.example.rental2.form;

import java.util.List;

import com.example.rental2.entity.BigGenreEntity;
import com.example.rental2.entity.InventoryControlEntity;

//在庫一覧用Form
public class InventorySelectForm {
    


    private String titleName;

    private Integer bigGenre;

    private Integer smallGenre;

    private Integer turns;

    private Integer status;

    private List<InventoryControlEntity> inventoryList;

    private List<BigGenreEntity> BigGenreList;
    
    

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Integer getBigGenre() {
        return bigGenre;
    }

    public void setBigGenre(Integer bigGenre) {
        this.bigGenre = bigGenre;
    }

    public Integer getSmallGenre() {
        return smallGenre;
    }

    public void setSmallGenre(Integer smallGenre) {
        this.smallGenre = smallGenre;
    }

    public Integer getTurns() {
        return turns;
    }

    public void setTurns(Integer turns) {
        this.turns = turns;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<InventoryControlEntity> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<InventoryControlEntity> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public List<BigGenreEntity> getBigGenreList() {
        return BigGenreList;
    }

    public void setBigGenreList(List<BigGenreEntity> bigGenreList) {
        BigGenreList = bigGenreList;
    }



    
}
