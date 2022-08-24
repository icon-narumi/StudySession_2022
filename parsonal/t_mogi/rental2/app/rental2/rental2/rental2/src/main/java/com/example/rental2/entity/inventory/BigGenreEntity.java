package com.example.rental2.entity.inventory;

//在庫の大ジャンルEntity
public class BigGenreEntity {

    private Integer bigGenreId;

    private String bigGenre;

    

    public String getBigGenre() {
        return bigGenre;
    }

    public void setBigGenre(String bigGenre) {
        this.bigGenre = bigGenre;
    }

    public Integer getBigGenreId() {
        return bigGenreId;
    }

    public void setBigGenreId(Integer bigGenreId) {
        this.bigGenreId = bigGenreId;
    }

}
