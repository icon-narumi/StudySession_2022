package com.example.saibaikun.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.example.saibaikun.entity.ActionRrkEntity;


@Mapper
public interface ActionRrkMapper {

    //Select：検索条件未入力
    // @Select("select saibaiDaichoId as  "
    // +"from T_ACTION_RRK where '${userId}'")
    // List<IngredientEntity> orderList(
    //     @Param("saibaiDaichoId") String sortList,
    //     @Param("actionYmd") String sort
    // );

    //Insert
    @Insert(
          "insert into \"T_ACTION_RRK\" ("
        + "\"SAIBAI_DAICHO_ID\",\"ACTION_YMD\", \"ACTION_COUNT_1\", \"ACTION_COUNT_2\", \"ACTION_COUNT_3\", \"CHECKED_FLG\""
        + ") values ("
        + "${e.saibaiDaichoId}, '${e.actionYmd}', 0, 0, 0, '0');")
    void actionRrkAddEntity(
        @Param("e") ActionRrkEntity e
    );
}
