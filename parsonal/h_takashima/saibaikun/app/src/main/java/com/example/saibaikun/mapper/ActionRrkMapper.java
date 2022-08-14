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

    //前日のデータ有無
    @Select( "select * from \"T_ACTION_RRK\" where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${date}';")
    void yestDateCheck(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("date") String yestdate
    );
    //前日のデータ取得
    @Select( "select * from \"T_ACTION_RRK\" where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${date}';")
    ActionRrkEntity getYestDate(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("date") String yestdate
    );

    //前々日のデータ有無
    @Select( "select * from \"T_ACTION_RRK\" where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${date}';")
    void dbyestDateCheck(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("date") String dbyestdate
    );
    //前々日のデータ取得
    @Select( "select * from \"T_ACTION_RRK\" where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${date}';")
    ActionRrkEntity getDbYestDate(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("date") String dbyestdate
    );

    //前々々日のデータ有無
    @Select( "select * from \"T_ACTION_RRK\" where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${date}';")
    void tdbyestDateCheck(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("date") String tdbyestdate
    );
    //前々日のデータ取得
    @Select( "select * from \"T_ACTION_RRK\" where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${date}';")
    ActionRrkEntity getTDbYestDate(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("date") String tdbyestdate
    );

    //Update
    //前日データのチェックフラグON
    @Update(
          "update \"T_ACTION_RRK\" "
        + "set \"CHECKED_FLG\" = '1' "
        + "where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${yestdate}' ")
    void updYestDate(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("yestdate") String yestdate
    );

    //actioncount1ごはん
    @Update(
          "update \"T_ACTION_RRK\" "
        + "set \"ACTION_COUNT_1\" = ${actionCount1} "
        + "where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${date}' ")
    void updateAction1(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("actionCount1") Integer actionCount1,
        @Param("date") String date
    );
    //actioncount2そうじ
    @Update(
          "update \"T_ACTION_RRK\" "
        + "set \"ACTION_COUNT_2\" = ${actionCount2} "
        + "where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${date}' ")
    void updateAction2(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("actionCount2") Integer actionCount2,
        @Param("date") String date
    );
    //actioncount3あそび
    @Update(
          "update \"T_ACTION_RRK\" "
        + "set \"ACTION_COUNT_3\" = ${actionCount3} "
        + "where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId} and \"ACTION_YMD\" = '${date}' ")
    void updateAction3(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("actionCount3") Integer actionCount3,
        @Param("date") String date
    );
}
