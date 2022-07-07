package com.example.shop.form;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.example.shop.entity.GenreMasterEntity;

public class InsertForm {

    @NotBlank(message = "空白以外を入力してください。")
    private String title;

    private Integer genre;

    @NotBlank(message = "空白以外を入力してください。")
    @Pattern(regexp = "[1-9]{1,1}[0-9]{1,4}", message = "1-99999の数値を入力して下さい")
    private String episodes;

    private Date broadcast;

    // private String id;
    @NotBlank(message = "空白以外を入力してください。")
    @Pattern(regexp = "[1-9]{1,1}[0-9]{0,1}", message = "1-99の数値を入力してください")
    private String season;

    private Integer id;

    private List<GenreMasterEntity> genrelist;

    public List<GenreMasterEntity> getGenrelist() {
        return genrelist;
    }

    public void setGenrelist(List<GenreMasterEntity> genrelist) {
        this.genrelist = genrelist;
    }

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

    // public String getId() {
    // return id;
    // }

    // public void setId(String id) {
    // this.id = id;
    // }

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



    
}
