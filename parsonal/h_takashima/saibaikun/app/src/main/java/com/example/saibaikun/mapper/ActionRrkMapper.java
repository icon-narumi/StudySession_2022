package com.example.saibaikun.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.saibaikun.entity.ActionRrkEntity;


@Mapper
public interface ActionRrkMapper {

    @Select( "select count(1) from \"T_ACTION_RRK\" where exists ( "
           + "select * from \"T_ACTION_RRK\" "
           + "where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${date}' "
           + "group by \"SAIBAI_DAICHO_ID\", \"ACTION_YMD\" ) ;")
    Integer actionRrkCheck(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("date") String date
    );

    //Insert
    @Insert(
          "insert into \"T_ACTION_RRK\" ("
        + "\"SAIBAI_DAICHO_ID\",\"ACTION_YMD\", \"ACTION_COUNT_1\", \"ACTION_COUNT_2\", \"ACTION_COUNT_3\", \"CHECKED_FLG\""
        + ") values ("
        + "${e.saibaiDaichoId}, '${e.actionYmd}', 0, 0, 0, '0');")
    void actionRrkAddEntity(
        @Param("e") ActionRrkEntity e
    );


    @Select( "select \"ACTION_COUNT_2\" from \"T_ACTION_RRK\" where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${date}';")
    Integer numCheck(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("date") String date
    );

    //Update
    @Update(
          "update \"T_ACTION_RRK\" "
        + "set \"ACTION_COUNT_2\" = ${actionCount2} "
        + "where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${date}' ")
    void updateAction2(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("actionCount2") Integer actionCount2,
        @Param("date") String date
    );
}
