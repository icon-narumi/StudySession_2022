package com.example.rental2.entity.inventory;
//在庫一覧用Entity

public class InventoryControlEntity {
    
    private String titleName;

    private Integer bigGenreId;

    private Integer smallGenreId;

    private Integer turns;

    private Integer statusId;

    private Integer id;


    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }



    public Integer getBigGenreId() {
        return bigGenreId;
    }

    public void setBigGenreId(Integer bigGenreId) {
        this.bigGenreId = bigGenreId;
    }

    public Integer getSmallGenreId() {
        return smallGenreId;
    }

    public void setSmallGenreId(Integer smallGenreId) {
        this.smallGenreId = smallGenreId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getTurns() {
        return turns;
    }

    public void setTurns(Integer turns) {
        this.turns = turns;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    
}
