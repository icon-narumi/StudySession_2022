package com.example.shop.form;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.shop.entity.AnimeEntity;

public class DeleteForm {

    private String title;

    private Integer genre;

    private String episodes;

    private Date broadcast;

    private String season;

    @NotNull(message = "削除項目を選択してください")
    private Integer id;

    private List<AnimeEntity> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AnimeEntity> getList() {
        return list;
    }

    public void setList(List<AnimeEntity> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public Date getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(Date broadcast) {
        this.broadcast = broadcast;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

}
