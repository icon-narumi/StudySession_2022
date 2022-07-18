package com.example.saibaikun.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.saibaikun.entity.CharacterEntity;



@Mapper
public interface SaibaikunMapper {

    // さいばいくんキャラクターリスト
    @Select("select \"CHARACTER_ID\" as characterId,\"CHARACTER_NAME\" as characterName,\"IMG_PATH\" as imgPath "
            +"from \"M_CHARACTER\" order by \"CHARACTER_ID\" asc")
    List<CharacterEntity> selectAll();



    // //Insert
    // @Insert(
    //       "insert into \"T_USER\" ("
    //     + "\"USER_ID\", \"USER_NAME\", \"SAIBAI_COUNT\""
    //     + ") values ("
    //     + "nextval('t_user_user_id_seq'), '${e.userName}', 1);")
    // void userAddEntity(
    //     @Param("e")      UserEntity e
    // );

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
