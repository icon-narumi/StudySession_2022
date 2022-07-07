package com.example.shop.bean;

import java.sql.Date;

public class AnimeSelectBean {

    private String title;

    private String genre;

    private String episodes;

    private Date broadcast;

    private String season;

    private Integer id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

}
