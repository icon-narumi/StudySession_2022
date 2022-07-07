package com.example.shop.form;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.example.shop.entity.AnimeEntity;

public class UpdateForm {
    private List<AnimeEntity> list;

    @NotBlank(message = "上記のタイトルのみ指定してください")
    private String title;

    private String genre;

    @NotBlank(message = "空白以外を入力してください。")
    @Pattern(regexp = "[1-9]{1,1}[0-9]{1,4}", message = "1-99999の数値を入力して下さい")
    private String episodes;

    private Date broadcast;

    @NotBlank(message = "空白以外を入力してください。")
    @Pattern(regexp = "[1-9]{1,1}[0-9]{0,1}", message = "1-99の数値を入力してください")
    private String season;

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

    public List<AnimeEntity> getList() {
        return list;
    }

    public void setList(List<AnimeEntity> list) {
        this.list = list;
    }
}
