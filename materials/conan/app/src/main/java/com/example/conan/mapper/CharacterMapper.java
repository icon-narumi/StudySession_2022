package com.example.conan.mapper;

import java.util.List;

import com.example.conan.bean.DisplayBean;
import com.example.conan.entity.CharacterEntity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CharacterMapper {

  @Select("select "
      + "c.name as name "
      + ", ms.sex_name as sex "
      + ", js.job_name as job "
      + "from t_character c "
      + "left join m_sex ms "
      + "on c.sex_id = ms.sex_id "
      + "left join m_job js "
      + "on c.job_id = js.job_id "
      + "where c.name like '%' || #{name} || '%' ")
  List<DisplayBean> selectLikeName(String name);

  @Select("select name, sex_id as sexId, job_id as jobId from t_character where name = #{name}")
  CharacterEntity selectByName(String name);

  @Insert("Insert Into t_character values(#{ent.name} , #{ent.sexId} , #{ent.jobId})")
  void insertCharacter(@Param("ent") CharacterEntity ent);

  @Update("update t_character set sex_id = #{ent.sexId}, job_id = #{ent.jobId} "
      + "where name = #{ent.name}")
  void updateCharacter(@Param("ent") CharacterEntity ent);

  @Delete("delete from t_character t_character where name = #{name}")
  void deleteCharacter(String name);
}
