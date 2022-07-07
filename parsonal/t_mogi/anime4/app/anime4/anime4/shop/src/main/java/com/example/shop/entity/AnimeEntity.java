package com.example.shop.entity;

import java.sql.Date;

public class AnimeEntity {

    String title;
    Integer genre;
    String episodes;
    Date broadcast;
    String season;

    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
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

}
