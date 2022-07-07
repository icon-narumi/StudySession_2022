package com.example.bird.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.bird.entity.BirdEntity;
import com.example.bird.entity.FamilyEntity;
import com.example.bird.entity.FoodEntity;
import com.example.bird.entity.OrdoEntity;
import com.example.bird.entity.TbirdEntity;

@Mapper
public interface BirdMapper {
    
    @Select("select"
            +" t1.species,m1.ordoname,m2.familyname,t1.volume,m3.foodname "
            +"from  t_bird t1 left join m_ordo m1 on t1.ordo = m1.id left join m_family m2 on t1.family = m2.id left join m_food m3 on t1.food = m3.id order by species asc")
    List<BirdEntity> selectAll();

    @Select("select * "
            +"from(select t1.species,m1.ordoname,m2.familyname,t1.volume,m3.foodname "
                    +"from  t_bird t1 left join m_ordo m1 on t1.ordo = m1.id left join m_family m2 on t1.family = m2.id left join m_food m3 on t1.food = m3.id) mt1"
                    +"  where  mt1.species like  '%' || #{species} || '%' order by species asc" )
    List<BirdEntity> searchBird(@Param("species") String species);

    @Insert("insert into t_bird (species,ordo,family,volume,food) values(#{species},#{ordo},#{family},#{volume},#{food})")
    void inputBird(@Param("species") String species,@Param("ordo") Integer ordo,@Param("family") Integer family,@Param("volume") Integer volume,@Param("food") Integer food);

    @Update("update t_bird set ordo=#{ordo} , family=#{family} , volume=#{volume} , food=#{food} where species = #{species}")
    void updateBird(@Param("species") String species,@Param("ordo") Integer ordo,@Param("family") Integer family,@Param("volume") Integer volume,@Param("food") Integer food);

    @Delete("delete from t_bird where species=#{species}")
    void deleteBird(@Param("species") String species);
   
    /*論理削除で使いたかった
    @Update("update bird set species = #{species} , food=#{food} , delete_flg = 1 where species = #{species} and delete_flg = 0")
    void deleteBird(@Param("species") String species,@Param("food") String food);
    */

    @Select("select *  from  m_ordo order by id asc")
    List<OrdoEntity> ordoAll();

    @Select("select *  from  m_family order by id asc")
    List<FamilyEntity> familyAll();

    @Select("select *  from  m_food order by id asc")
    List<FoodEntity> foodAll();

    @Select("select * from t_bird where  species = #{species} ")
    TbirdEntity updateSearchOrdo(@Param("species") String species);

    @Update("update t_bird set ordo=#{tbird.ordo} , family=#{tbird.family} , volume=#{tbird.volume} , food=#{tbird.food} where species = #{tbird.species}")
    void updateTbird(@Param("tbird") TbirdEntity tbird);

 


}
