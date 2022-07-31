package com.example.saibaikun.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.saibaikun.entity.LoginLogEntity;


@Mapper
public interface LoginLogMapper {

    //Select：前回ログイン時のログがあるか確認、あれば履歴番号とログイン日時を取得
    @Select( "select main.\"RRK_NO\" rrkNo,main.\"LOGIN_TM\" zenkaiLoginTm "
           + "from \"T_LOGIN_LOG\" main "
           + "inner join ( "
           + "  select \"USER_ID\",MAX(\"RRK_NO\") RRKNO "
           + "  from \"T_LOGIN_LOG\" "
           + "  where \"USER_ID\" = ${userId} "
           + "  group by \"USER_ID\" ) sub "
           + "on main.\"USER_ID\" = sub.\"USER_ID\" and main.\"RRK_NO\" = sub.RRKNO ")
    LoginLogEntity loginLogCheck(
    @Param("userId") Integer userId
    );

    //Insert
    @Insert(
          "insert into \"T_LOGIN_LOG\" ( "
        + "\"USER_ID\",\"RRK_NO\", \"LOGIN_TM\", \"ZENKAI_LOGIN_TM\" "
        + ") values ("
        + "${e.userId}, ${e.rrkNo}, '${e.loginTm}', '${e.zenkaiLoginTm}');")
    void loginLogAddEntity(
        @Param("e") LoginLogEntity e
    );
}
