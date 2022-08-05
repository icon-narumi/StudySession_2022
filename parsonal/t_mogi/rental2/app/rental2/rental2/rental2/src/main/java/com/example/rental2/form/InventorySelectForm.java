package com.example.rental2.form;

import java.util.List;

import com.example.rental2.bean.InventorySelectBean;
import com.example.rental2.entity.BigGenreEntity;

//在庫一覧用Form
public class InventorySelectForm {
    


    private String titleName;

    private Integer bigGenre;

    private Integer smallGenre;

    private Integer turns;

    private Integer status;

    private List<InventorySelectBean> inventoryList;

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


    public List<BigGenreEntity> getBigGenreList() {
        return BigGenreList;
    }

    public void setBigGenreList(List<BigGenreEntity> bigGenreList) {
        BigGenreList = bigGenreList;
    }

    public List<InventorySelectBean> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<InventorySelectBean> inventoryList) {
        this.inventoryList = inventoryList;
    }



    
}
