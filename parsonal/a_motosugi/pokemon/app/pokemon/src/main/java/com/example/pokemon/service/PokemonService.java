package com.example.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.PokemonBean;
import com.example.pokemon.entity.PokemonEntity;
import com.example.pokemon.mapper.PokemonMasterMapper;

@Service
public class PokemonService {
    
    @Autowired
    PokemonMasterMapper pokemonMasterMapper;

    public List<PokemonEntity> selectPokemon() {
        return pokemonMasterMapper.selectPokemon();
    }

    public List<PartnerBean> selectPartner(Integer tId) {
        return pokemonMasterMapper.selectPartner(tId);
    }

    public List<PokemonBean> selectPokemonWithNum() {
        return pokemonMasterMapper.selectPokemonWithNum();
    }

    public void addPartner(Integer tId, Integer pId, Integer strength, String attackType) {
        pokemonMasterMapper.addPartner(tId, pId, strength, attackType);
    }

    public void updateStrength(Integer strength, Integer id) {
        pokemonMasterMapper.updateStrength(strength, id);
    }

    public boolean deletePartner(Integer id, List<PartnerBean> trainerPartnerList) {

        if(trainerPartnerList.size() <= 1) {
            return false;
        } else {
            try {
                pokemonMasterMapper.deletePartner(id);
            }catch (Exception e) {
                return false;
            }
            return true;
        }

        /*try {
            if(trainerPartnerList.size() == 1) {
                return false;
            } else {
                pokemonMasterMapper.deletePartner(id);
            }    
        } catch (Exception e) {
            return false;
        }
        return true;*/
    }
}
