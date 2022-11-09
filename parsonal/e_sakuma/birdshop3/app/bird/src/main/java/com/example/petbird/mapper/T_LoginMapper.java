package com.example.petbird.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.petbird.bean.UnitIdBean;
import com.example.petbird.entity_master.SpeciesEntity;
import com.example.petbird.entity_petbird.PetBirdEntity;

@Mapper
public interface T_LoginMapper {

    List<SpeciesEntity> speciesList(@Param("species") String species);

    //新しい鳥を種類テーブルへ登録
    void inputSpecies(@Param("species") String inputSpecies);

    //名前で絞って種類IDを抽出
    SpeciesEntity speciesId(@Param("species") String species);

    //在庫テーブルへ鳥を登録
    void inputPetBird(@Param("species") Integer species,@Param("sex") Integer sex,@Param("color") Integer color,@Param("life") Integer life,@Param("count") Integer count,@Param("price") Integer price);

    //種類IDから更新対象を抽出
    PetBirdEntity updateSelectPetBird(@Param("species") Integer species,@Param("sex") Integer sex,@Param("color") Integer color);

    void updatePetBird(@Param("species") Integer species,@Param("sex") Integer sex,@Param("color") Integer color,@Param("life") Integer life,@Param("count") Integer count,@Param("price") Integer price);

    void deletePetBird(@Param("species") Integer species,@Param("sex") Integer sex,@Param("color") Integer color);

    List<UnitIdBean> petBirdAllList();
}
