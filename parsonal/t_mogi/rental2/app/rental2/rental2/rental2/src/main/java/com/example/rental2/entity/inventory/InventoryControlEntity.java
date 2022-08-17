package com.example.rental2.entity.inventory;
//在庫一覧用Entity

public class InventoryControlEntity {
    
    private String titleName;

    private Integer bigGenre;

    private Integer smallGenre;

    private Integer turns;

    private Integer status;

    private Integer id;


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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    
}
