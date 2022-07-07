package com.example.shop.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.shop.bean.AnimeSelectBean;
import com.example.shop.entity.AnimeEntity;

@Mapper
public interface AnimeMapper {

      // すべてのデータをとってくる。
      @Select("select title,genre,episodes,broadcast,season,id from anime ")
      List<AnimeEntity> selectAll();

      // left join genremaster anime.genre = genremaster.id
      // 選択したジャンルがあっているデータを取ってくる。
      @Select("select title,genre,episodes,broadcast,season from anime where genre =#{genre}")
      List<AnimeEntity> selectByGenre(String genre);
      // @Select("select * from anime where item = #{item}")s

      // @Select("select anime,genre,episodes,broadcast from anime where anime like
      // concat ('%',#{input},'%')")
      // List<AnimeEntity> selectByAnime(String anime);

      // タイトルとジャンルがあっているデータを取ってくる。
      @Select("select title,genre,episodes,broadcast,season from anime "
                  + "where title like concat ('%',#{titleName},'%') and genre =#{genre}")
      List<AnimeEntity> selectByAnime(
                  @Param("titleName") String titleName, @Param("genre") Integer genre);

      // タイトルの一部でもあっているデータを取ってくる。
      @Select("select title,genre,episodes,broadcast,season, from anime "
                  + "where title like concat ('%',#{titleName},'%')")
      List<AnimeEntity> selectByTitle(
                  @Param("titleName") String titleName);

      // 追加処理
      @Insert("insert into anime (id,title,genre,episodes,broadcast,season)"
                  + "values (nextval('anime_id_seq'),#{titleName},#{genre},#{episodes},#{broadcast},#{season})")
      void insertByAnime(
                  @Param("titleName") String titleName, @Param("genre") Integer genre,
                  @Param("episodes") String episodes, @Param("broadcast") Date broadcast,
                  @Param("season") String season);
      // void save(InsertForm insertForm);

      // 更新処理
      @Update("update anime set genre =#{genre},episodes =#{episodes},broadcast =#{broadcast}"
                  + "where title =#{title} and season =#{season}")
      void updateByAnime(
                  @Param("title") String title, @Param("genre") String genre,
                  @Param("episodes") String episodes, @Param("broadcast") Date broadcast,
                  @Param("season") String season);

      // 削除処理
      @Delete("delete from anime where id =#{id} ")
      void deleteByAnime(@Param("id") Integer id);// @Param("season") String season);

      // テーブル結合+select文
      @Select("select a.title,g.genre,a.episodes,a.broadcast,a.season,a.id from anime "
                  + "a left join genremaster g on a.genre =g.id where a.title like concat ('%',#{titleName},'%')")

      List<AnimeSelectBean> selectByTitle2(@Param("titleName") String titleName);

      // テーブル結合+select文
      @Select("select a.title,g.genre,a.episodes,a.broadcast,a.season from anime "
                  + "a left join genremaster g on a.genre =g.id where a.title like concat ('%',#{titleName},'%') and a.genre =#{genre}")
      List<AnimeSelectBean> selectByAnime2(
                  @Param("titleName") String titleName, @Param("genre") Integer genre);

}

// and season =#{season}"