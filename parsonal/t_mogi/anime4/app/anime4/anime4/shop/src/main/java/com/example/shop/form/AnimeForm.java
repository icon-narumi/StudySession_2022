package com.example.shop.form;

import java.util.List;

import com.example.shop.bean.AnimeSelectBean;
import com.example.shop.entity.AnimeEntity;
import com.example.shop.entity.GenreMasterEntity;

public class AnimeForm {

    private String result;
    private List<AnimeSelectBean> list;
    private List<AnimeEntity> list2;
    private Integer genre;
    private String input;

    private List<GenreMasterEntity> genrelist;

    public List<GenreMasterEntity> getGenrelist() {
        return genrelist;
    }

    public void setGenrelist(List<GenreMasterEntity> genrelist) {
        this.genrelist = genrelist;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public List<AnimeEntity> getList2() {
        return list2;
    }

    public void setList2(List<AnimeEntity> list2) {
        this.list2 = list2;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<AnimeSelectBean> getList() {
        return list;
    }

    public void setList(List<AnimeSelectBean> list) {
        this.list = list;
    }

}