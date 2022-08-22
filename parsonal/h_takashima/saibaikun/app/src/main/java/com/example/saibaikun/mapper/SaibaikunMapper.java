package com.example.saibaikun.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.saibaikun.bean.GetLoginInfoBean;
import com.example.saibaikun.entity.SaibaiDaichoEntity;



@Mapper
public interface SaibaikunMapper {

    // さいばいくんキャラクターリスト取得（新規）
    @Select( "select \"CHARACTER_ID\" as characterId,\"CHARACTER_NAME\" as characterName,\"IMG_PATH\" as imgPath, ' ' as saibaiName "
            +"from \"M_CHARACTER\" order by \"CHARACTER_ID\" asc")
    List<GetLoginInfoBean> selectAll();

    // さいばいくんキャラクターリスト取得（登録済み）
    @Select( "select m.\"CHARACTER_ID\" as characterId, t.\"SAIBAI_DAICHO_ID\" as saibaiDaichoId, t.\"SAIBAI_NAME\" as saibaiName, m.\"IMG_PATH\" as imgPath "
            +"from \"M_CHARACTER\" m "
            +"inner join (select * from \"T_SAIBAI_DAICHO\" where \"USER_ID\" = ${userId}) t "
            +"on m.\"CHARACTER_ID\" = t.\"CHARACTER_ID\" "
            +"order by m.\"CHARACTER_ID\" asc")
    List<GetLoginInfoBean> selectSaibaikun(
        @Param("userId") Integer userId
    );


    // saibai_daicho_id_seq取得
    @Select("select nextval('t_saibai_daicho_saibai_daicho_id_seq') as saibaiDaichoId")
    Integer getDaichoId();

    // さいばい台帳登録（新規登録）
    @Insert(
          "insert into \"T_SAIBAI_DAICHO\" ("
        + "\"SAIBAI_DAICHO_ID\", \"CHARACTER_ID\", \"SAIBAI_NAME\", \"USER_ID\", \"LEVEL\""
        + ") values ("
        + "${e.saibaiDaichoId}, '${e.characterId}', '${e.saibaiName}', '${e.userId}', 0);")
    void saibaiAddEntity(
        @Param("e")      SaibaiDaichoEntity e
    );

    // さいばいくんlevel確認
    @Select("select \"LEVEL\" as level from \"T_SAIBAI_DAICHO\" where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId}")
    Integer levelCheck( 
        @Param("saibaiDaichoId") Integer saibaiDaichoId
    );


    //Update
    //さいばいくんレベルアップ
    @Update(
          "update \"T_SAIBAI_DAICHO\" "
        + "set \"LEVEL\" = ${level} "
        + "where \"SAIBAI_DAICHO_ID\" = ${saibaiDaichoId}")
    void updLevel(
        @Param("saibaiDaichoId") Integer saibaiDaichoId,
        @Param("level") Integer level
    );
    
}
