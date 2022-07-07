package com.example.pokemon.mapper;

import com.example.pokemon.entity.TrainerLevelEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TrainerLevelMapper {
    
    @Select("select * from trainer_level")
    List<TrainerLevelEntity> selectTrainerLevel();

    @Update("update trainer_level set level = #{trainerLevel} where t_id = #{tId}")
    void updateLevel(@Param("trainerLevel") Integer trainerLevel, @Param("tId") Integer tId);

}
