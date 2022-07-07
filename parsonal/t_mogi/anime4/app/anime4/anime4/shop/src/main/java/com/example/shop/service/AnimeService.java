package com.example.shop.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.bean.AnimeSelectBean;
import com.example.shop.entity.AnimeEntity;
import com.example.shop.entity.GenreMasterEntity;
import com.example.shop.mapper.AnimeMapper;
import com.example.shop.mapper.GenreMasterMapper;

@Service
public class AnimeService {

    @Autowired
    private AnimeMapper animeMapper;
    @Autowired
    private GenreMasterMapper genreMasterMapper;

    public List<AnimeEntity> selectAll() {
        return animeMapper.selectAll();
    }

    public List<AnimeEntity> selectByGenre(String genre) {
        return animeMapper.selectByGenre(genre);
    }
    // public AnimeEntity selectByItem(String item) {
    // return animeMapper.selectByItem(item);
    // }

    public List<AnimeSelectBean> selectByAnime(String titleName, Integer genre) {
        return animeMapper.selectByAnime2(titleName, genre);
    }

    public List<AnimeSelectBean> selectByTitle(String titleName) {
        return animeMapper.selectByTitle2(titleName);
    }

    public AnimeMapper getAnimeMapper() {
        return animeMapper;
    }

    public void setAnimeMapper(AnimeMapper animeMapper) {
        this.animeMapper = animeMapper;
    }

    public void insertByAnime(String titleName, Integer genre, String episodes, Date broadcast, String season) {
        animeMapper.insertByAnime(titleName, genre, episodes, broadcast, season);
    }

    public void updateByAnime(String titleName, String genre, String episodes, Date broadcast, String season) {
        animeMapper.updateByAnime(titleName, genre, episodes, broadcast, season);
    }

    public void deleteByAnime(Integer id) {// , String season) {
        animeMapper.deleteByAnime(id);// season);
    }

    public List<GenreMasterEntity> selectGenreAll() {

        List<GenreMasterEntity> genrelist;

        genrelist = genreMasterMapper.selectAll();
        GenreMasterEntity genreMasterEntity = new GenreMasterEntity();
        genreMasterEntity.setId(0);
        genreMasterEntity.setGenre("すべてのジャンル");
        genrelist.add(genreMasterEntity);
        return genrelist;
    }


    public List<GenreMasterEntity> selectGenreInsert(){

        return genreMasterMapper.selectAll();
    }
}
