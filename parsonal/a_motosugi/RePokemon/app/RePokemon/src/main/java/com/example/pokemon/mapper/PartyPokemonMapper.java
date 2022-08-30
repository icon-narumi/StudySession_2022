package com.example.pokemon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.pokemon.bean.AllPartyViewBean;

@Mapper
public interface PartyPokemonMapper {

    // 手持ちポケモン一覧用
    List<AllPartyViewBean> selectAllPartyViewBean(Integer tId);
    
}
