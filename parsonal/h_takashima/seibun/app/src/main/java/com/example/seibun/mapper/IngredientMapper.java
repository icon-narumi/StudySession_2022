package com.example.seibun.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import com.example.seibun.entity.IngredientEntity;

@Mapper
public interface IngredientMapper {

    //Select：検索条件未入力
    @Select("select * from ingredient order by ${sortList} ${sort};")
    List<IngredientEntity> orderList(
        @Param("sortList") String sortList,
        @Param("sort") String sort
    );

    //Select：検索条件あり
    @Select("select * from ingredient where name like '%${search}%' order by ${sortList} ${sort};")
    List<IngredientEntity> orderSearchList(
        @Param("search")    String search,
        @Param("sortList")  String sortList,
        @Param("sort")      String sort
    );

    //Select：更新対象データ
    @Select("select * from ingredient where name = '${selectName}';")
    IngredientEntity editData(
        @Param("selectName") String selectName
    );


    //Insert
    @Insert(
          "insert into ingredient ("
        + "category, name, color, calorie, protein"
        + ") values ("
        + "'${e.category}', '${e.name}', '${e.color}', '${e.calorie}', '${e.protein}');")
    void addEntity(
        @Param("e")      IngredientEntity e
    );

    // //Update
    @Update(
          "update ingredient set "
        + " name    = '${e.name}' "
        + ",color   = '${e.color}' "
        + ",calorie = '${e.calorie}' "
        + ",protein = '${e.protein}' "
        + "where category = '${e.category}' and name = '${Pname}'")
    void editEntity(
        @Param("e")      IngredientEntity e,
        @Param("Pcat")   String Pcat,
        @Param("Pname")  String Pname
    );


    //delete
    @Delete("delete from ingredient where name = '${selectName}';")
    void deleteData(
        @Param("selectName") String selectName
    );


}
