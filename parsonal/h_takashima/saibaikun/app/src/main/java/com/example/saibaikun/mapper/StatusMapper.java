package com.example.saibaikun.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.saibaikun.bean.SaibaikunStatusBean;

@Mapper
public interface StatusMapper {

    // ステータス表示
    @Select( "select m.\"CHARACTER_NAME\" as characterName, t.\"SAIBAI_NAME\" as saibaiName, t.\"LEVEL\" as level, t2.\"ACTION_COUNT_1\" as actionCount1, t2.\"ACTION_COUNT_2\" as actionCount2, t2.\"ACTION_COUNT_3\" as actionCount3 "
            +"from \"M_CHARACTER\" m "
            +"inner join ( select * from \"T_SAIBAI_DAICHO\" where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} ) t "
            +"on m.\"CHARACTER_ID\" = t.\"CHARACTER_ID\" "
            +"inner join ( select * from \"T_ACTION_RRK\" where \"ACTION_YMD\" = '${date}') t2 "
            +"on t.\"SAIBAI_DAICHO_ID\" = t2.\"SAIBAI_DAICHO_ID\" ;")
    SaibaikunStatusBean getStatus(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("date") String date
    );

    // //Update
    // @Update(
    //       "update ingredient set "
    //     + " name    = '${e.name}' "
    //     + ",color   = '${e.color}' "
    //     + ",calorie = '${e.calorie}' "
    //     + ",protein = '${e.protein}' "
    //     + "where category = '${e.category}' and name = '${Pname}'")
    // void editEntity(
    //     @Param("e")      IngredientEntity e,
    //     @Param("Pcat")   String Pcat,
    //     @Param("Pname")  String Pname
    // );


    //delete
    // @Delete("delete from ingredient where name = '${selectName}';")
    // void deleteData(
    //     @Param("selectName") String selectName
    // );


}
