package com.example.petbird.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.petbird.entity.PetBirdEntity;
import com.example.petbird.entity.SpeciesEntity;

@Mapper
public interface LoginMapper {

    //新しい鳥を種類テーブルへ登録
    @Insert("insert into m_species (speciesname) values(#{species})")
    void inputSpecies(@Param("species") String inputSpecies);
    
    //鳥の名前の種類で絞る
    @Select("select *  from  m_species where (speciesname) = #{species}")
    List<SpeciesEntity> newSpecies(@Param("species") String inputSpecies);

    //種類・性別・色で検索
    @Select("select * from t_petbird where species = #{species} and sex = #{sex} and color = #{color}")
    List<SpeciesEntity> registered(@Param("species") Integer species,@Param("sex") Integer sex,@Param("color") Integer color);
    
    //在庫テーブルへ鳥を登録
    @Insert("insert into t_petbird (species,sex,color,life,count,price) values(#{species},#{sex},#{color},#{life},#{count},#{price})")
    void inputPetBird(@Param("species") Integer species,@Param("sex") Integer sex,@Param("color") Integer color,@Param("life") Integer life,@Param("count") Integer count,@Param("price") Integer price);

    //名前で絞って種類IDを抽出
    @Select("select *  from  m_species where (speciesname) = #{species}")
    SpeciesEntity speciesId(@Param("species") String species);

    //種類IDから更新対象を抽出
    @Select("select *  from  t_petBird where (species) = #{species}")
    PetBirdEntity updatePetBird(@Param("species") Integer species);


}