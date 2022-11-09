package com.example.petbird.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.petbird.bean.UnitIdBean;
import com.example.petbird.entity_petbird.PetBirdEntity;

@Mapper
public interface T_ClientMapper {
    //このメソッド名が呼ばれたらxmlファイルのMapperが実行される
    List<UnitIdBean> selectBird(@Param("species") Integer species,@Param("sex") Integer sex,@Param("color") Integer color);
    
    List<UnitIdBean> idCheckedPetBirdList(@Param("id") Integer[] id);

    List<UnitIdBean> idCheckedPickpPetBirdList(@Param("id") Integer id);

    UnitIdBean idCheckedPickpPetBird(@Param("id") Integer id);

    PetBirdEntity petBird(@Param("id") Integer id);

    void updatePetBird(@Param("id") Integer id,@Param("count") Integer count);

}
