package com.example.saibaikun.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.saibaikun.entity.UserEntity;


@Mapper
public interface UserMapper {

    //Select：ユーザーの登録があればUSER_IDを取得する
    // うんち
    // @Select(  "select case when ( "
    //         + "select count(\"USER_NAME\") count from \"T_USER\" where \"USER_NAME\" = '${userName}' "
    //         + ") > 0 then \"USER_ID\" else 0 end userCheck "
    //         + "from \"T_USER\"")
    // Integer userCheck(
    //     @Param("userName") String userName
    // );

    @Select(  "select count(\"USER_NAME\") count from \"T_USER\" where \"USER_NAME\" = '${userName}'")
    Integer userCheck(
        @Param("userName") String userName
    );

    //Select：user_id_seq取得
    @Select("select nextval('t_user_user_id_seq') as userId")
    Integer getUserId();


    //Insert
    @Insert(
          "insert into \"T_USER\" ("
        + "\"USER_ID\", \"USER_NAME\", \"SAIBAI_COUNT\""
        + ") values ("
        + "${e.userId}, '${e.userName}', 1);")
    void userAddEntity(
        @Param("e")      UserEntity e
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
