package com.example.pokemon.mapper;

import java.util.List;

import com.example.pokemon.entity.TrainerMasterEntity;
import com.example.pokemon.bean.NameLevelBean;

import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;

@Mapper
public interface TrainerMasterMapper {

    @Select("select * from m_trainer order by t_id ASC")
    List<TrainerMasterEntity> selectTrainerMaster();

    @Select("select m_trainer.name, trainer_level.level from m_trainer join trainer_level on m_trainer.t_id = trainer_level.t_id")
    List<NameLevelBean> selectTrainerAndLevel();

    @Select("select name from m_trainer where t_id = #{tId}")
    String selectTrainerName(Integer tId);

}