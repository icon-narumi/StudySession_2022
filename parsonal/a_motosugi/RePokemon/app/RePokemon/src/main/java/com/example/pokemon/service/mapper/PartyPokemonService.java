package com.example.pokemon.service.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.bean.AllPartyViewBean;
import com.example.pokemon.mapper.PartyPokemonMapper;

@Service
public class PartyPokemonService {

    @Autowired
    private PartyPokemonMapper partyPokemonMapper;

    public List<AllPartyViewBean> selectAllPartyViewBean(Integer tId){
        return partyPokemonMapper.selectAllPartyViewBean(tId);
    }
    
    
}
