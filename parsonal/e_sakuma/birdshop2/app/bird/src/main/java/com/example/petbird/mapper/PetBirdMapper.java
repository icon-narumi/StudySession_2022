package com.example.petbird.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.petbird.bean.IdCountBean;
import com.example.petbird.entity.CastPetBirdEntity;
import com.example.petbird.entity.ColorEntity;
import com.example.petbird.entity.PetBirdEntity;
import com.example.petbird.entity.SexEntity;
import com.example.petbird.entity.SpeciesEntity;

@Mapper
public interface PetBirdMapper {

    @Select("select * from (select t1.id,m1.speciesname,m2.sexname,m3.colorname,t1.price,t1.life,t1.count from t_petbird t1"
            +" left join m_species m1"
            +" on t1.species = m1.id"
            +" left join m_sex m2"
            +" on t1.sex = m2.id"
            +" left join m_color m3"
            +" on t1.color=m3.id"
            +" where t1.species = #{species} or t1.sex = #{sex} or t1.color=#{color} or t1.price = #{price}"
            +" )mt1"
            +" where mt1.count > 0"  
            +" order by price asc")
    List<CastPetBirdEntity> selectBird(@Param("species") Integer species,@Param("sex") Integer sex,@Param("color") Integer color,@Param("price") Integer price);

    @Select("select * from (select t1.id,m1.speciesname,m2.sexname,m3.colorname,t1.price,t1.life,t1.count from t_petbird t1"
            +" left join m_species m1"
            +" on t1.species = m1.id"
            +" left join m_sex m2"
            +" on t1.sex = m2.id"
            +" left join m_color m3"
            +" on t1.color=m3.id"
            +" where t1.id = #{id}"
            +" )mt1"
            +" where mt1.count > 0")                   
     CastPetBirdEntity commitBird(@Param("id") Integer id);

     @Select("select * from (select t1.id,m1.speciesname,m2.sexname,m3.colorname,t1.price,t1.life,t1.count from t_petbird t1"
            +" left join m_species m1"
            +" on t1.species = m1.id"
            +" left join m_sex m2"
            +" on t1.sex = m2.id"
            +" left join m_color m3"
            +" on t1.color=m3.id"
            +" where t1.id = any(array[#{id}])"
            +" )mt1"
            +" where mt1.count > 0")                   
     List<CastPetBirdEntity> commitBirdList(@Param("id") Integer[] id);

     @Select("select tc1.id, mt1.speciesname,mt1.sexname,mt1.colorname,mt1.price,mt1.life,tc1.count from t_cartbird tc1"
                +" left join(select t1.id,m1.speciesname,m2.sexname,m3.colorname,t1.price,t1.life,t1.count from t_petbird t1"
                                +" left join m_species m1"
                                +" on t1.species = m1.id"
                                +" left join m_sex m2"
                                +" on t1.sex = m2.id"
                                +" left join m_color m3"
                                +" on t1.color=m3.id"
                                +" )mt1"
                +" on tc1.id = mt1.id"
                //+" where t1.id = any(array[#{id}])"選択した行のみとる時に使用
                +" order by tc1.id asc")                   
        List<CastPetBirdEntity> cartBirdList(@Param("id") Integer id);

        //削除ボタンを押した時に鳥テーブルの値が元に戻るようにする
        @Select("select id,count from t_petbird where id = #{id}")
        PetBirdEntity PetBird(@Param("id") Integer id);
        //カートに入っているかを確認
        @Select("select id,count from t_cartbird where id = #{id}")
        IdCountBean seachCartBird(@Param("id") Integer id);
        //カートへ入れる
        @Insert("insert into t_cartbird (id,count) values(#{id},#{count})")
        void inputCartBird(@Param("id") Integer id,@Param("count") Integer count);
        
        //カートへ入れた鳥の数を更新
        @Update("update t_cartbird set count = #{count} where id = #{id}")
        void updateCartBird(@Param("id") Integer id,@Param("count") Integer count);
        //鳥テーブルの値を更新
        @Update("update t_petbird set count = #{count} where id = #{id}")
        void updatePetBird(@Param("id") Integer id,@Param("count") Integer count);
        //購入と共にカートを空にする
        @Delete("delete from t_cartbird")
        void deleteAll();

        //カート画面で削除ボタンを押した際に実行
        @Delete("delete from t_cartbird where id = #{id}")
        void deleteOnly(@Param("id") Integer id);
        //削除ボタンを押した後にカートの中身を全表示する
        @Select("select tc1.id, mt1.speciesname,mt1.sexname,mt1.colorname,mt1.price,mt1.life,tc1.count from t_cartbird tc1"
        +" left join(select t1.id,m1.speciesname,m2.sexname,m3.colorname,t1.price,t1.life,t1.count from t_petbird t1"
                        +" left join m_species m1"
                        +" on t1.species = m1.id"
                        +" left join m_sex m2"
                        +" on t1.sex = m2.id"
                        +" left join m_color m3"
                        +" on t1.color=m3.id"
                        +" )mt1"
        +" on tc1.id = mt1.id"
        +" order by tc1.id asc")
        List<CastPetBirdEntity> seachCartAll();
   
    //MasterTable
    @Select("select * from m_species")                   
    List<SpeciesEntity> speciesAll();

    @Select("select * from m_sex")                
    List<SexEntity> sexAll();

    @Select("select * from m_color")                   
    List<ColorEntity> colorAll();


}
