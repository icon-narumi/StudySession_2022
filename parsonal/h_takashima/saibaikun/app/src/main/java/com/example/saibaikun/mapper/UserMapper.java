package com.example.saibaikun.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.saibaikun.entity.UserEntity;


@Mapper
public interface UserMapper {

    //Select：更新対象データ
    @Select("select \"USER_ID\",\"USER_NAME\" as userName from \"T_USER\" where \"USER_NAME\" = '${userName}';")
    void userCheck(
        @Param("userName") String userName
    );


    //Insert
    @Insert(
          "insert into \"T_USER\" ("
        + "\"USER_ID\", \"USER_NAME\", \"SAIBAI_COUNT\""
        + ") values ("
        + "nextval('t_user_user_id_seq'), '${e.userName}', 1);")
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
