package com.example.seibun.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
// import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
// import org.apache.ibatis.annotations.Delete;

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
    @Select("select * from ingredient where name ='${selectName}';")
    IngredientEntity editData(
        @Param("selectName") String selectName
    );

    // //Insert
    // @Insert(
    //       "insert into okaimono ("
    //     + "category, name, color, calorie, protein"
    //     + ") values ("
    //     + "'${category}', '${name}', '${color}', '${calorie}', '${protein}');")
    // void editEntity(
    //     @Param("category") String category,
    //     @Param("name")     String name,
    //     @Param("color")    String color,
    //     @Param("calorie")  BigDecimal calorie,
    //     @Param("protein")  BigDecimal protein

    // );

    // //Update
    @Update(
          "update set "
        + " name    = '${name}' "
        + ",color   = '${color}' "
        + ",calorie = '${calorie}' "
        + ",protein = '${protein}' "
        + "from ingredient where category = '${category} and name ='${name}';")
    IngredientEntity ingredientEntity(
        @Param("category") String category,
        @Param("name")     String name,
        @Param("color")    String color,
        @Param("calorie")  BigDecimal calorie,
        @Param("protein")  BigDecimal protein
    );


    // //delete
    // @Delete(
    //       "delete from ingredient"
    //     + "where category = '${category} and name ='${name}';")
    // void ingredientEntity(
    //     @Param("category") String category,
    //     @Param("name")     String name
    // );

}
