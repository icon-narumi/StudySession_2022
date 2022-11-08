package com.example.rental2.mapper.inventory;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.rental2.bean.InventorySelectBean;
import com.example.rental2.entity.inventory.InventoryControlEntity;

@Mapper
public interface InventoryMapper {

        // 在庫状況を何もデータベース上で手を加えずに全て取り出す。
        /// * @Select("select * from inventorycontrol")*/
        List<InventoryControlEntity> selectAll();

        // 在庫状況をテーブル結合で表示
        // 作成途中
        /// * @Select("select i.titlename,b.biggenre,s.smallgenre,i.turns,t.status,i.id
        // "
        // + "from inventorycontrol i "
        // + "left join biggenre b on i.biggenre = b.biggenreid "
        // + "left join smallgenre s on i.smallgenre = s.smallgenreid "
        // + "left join status t on i.status = t.statusid")*/
        List<InventorySelectBean> selectByInventoryInformation();

        // タイトルのみ入力した場合
        @Select("select i.title,b.biggenre,s.smallgenre,i.turns,t.status,i.id "
                        + "from inventorycontrol i "
                        + "left join biggenre b on i.biggenre = b.biggenreid "
                        + "left join smallgenre s on i.smallgenre = s.smallgenreid "
                        + "left join status t on i.status = t.statusid "
                        + "where a.title like concat ('%',#{titleName},'%')")
        List<InventorySelectBean> selectByNotGenre(
                        @Param("titleName") String titleName);

        // タイトルと大ジャンルのみを入力した場合
        @Select("select i.title,b.biggenre,s.smallgenre,i.turns,t.status,i.id "
                        + "from inventorycontrol i "
                        + "left join biggenre b on i.biggenre = b.biggenreid "
                        + "left join smallgenre s on i.smallgenre = s.smallgenreid "
                        + "left join status t on i.status = t.statusid "
                        + "where a.title like concat ('%',#{titleName},'%') "
                        + "and  b.biggenre = #{bigGenre}")
        List<InventorySelectBean> selectByBigGenre(
                        @Param("titleName") String titleName,
                        @Param("bigGenre") Integer bigGenre);

        // タイトルと小ジャンルのみ入力した場合
        @Select("select i.title,b.biggenre,s.smallgenre,i.turns,t.status,i.id "
                        + "from inventorycontrol i "
                        + "left join biggenre b on i.biggenre = b.biggenreid "
                        + "left join smallgenre s on i.smallgenre = s.smallgenreid "
                        + "left join status t on i.status = t.statusid "
                        + "where a.title like concat ('%',#{titleName},'%') "
                        + "and s.smallgenre = #{smallGenre}")
        List<InventorySelectBean> selectBySmallGenre(
                        @Param("titleName") String titleName,
                        @Param("smallGenre") Integer smallGenre);

        // タイトル、大ジャンル、小ジャンル、ステータス（貸出可のみ）で検索
        List<InventorySelectBean> selectRentalField(
                        @Param("titleName") String titleName,
                        @Param("bigGenreId") Integer bigGenreId,
                        @Param("smallGenreId") Integer smallGenreId,
                        @Param("statusId") Integer statusId);

        //タイトル、大ジャンル、小ジャンル、ステータス(貸出可のみ)で検索
        List<InventorySelectBean> selectReturnField(
                @Param("titleName") String titleName,
                @Param("bigGenreId") Integer bigGenreId,
                @Param("smallGenreId") Integer smallGenreId,
                @Param("statusId") Integer statusId);

        // 全項目入力した場合
        // @Select("select i.title,b.biggenre,s.smallgenre,i.turns,t.status,i.id "
        // + "from inventorycontrol i "
        // + "left join biggenre b on i.biggenre = b.biggenreid "
        // + "left join smallgenre s on i.smallgenre = s.smallgenreid "
        // + "left join status t on i.status = t.statusid "
        // + "where a.title like concat ('%',#{titleName},'%') "
        // + "and b.biggenre = #{bigGenre}"
        // + "and s.smallgenre = #{smallGenre]")
        List<InventorySelectBean> selectField(
                        @Param("titleName") String titleName,
                        @Param("bigGenreId") Integer bigGenreId,
                        @Param("smallGenreId") Integer smallGenreId);

        // 全てのデータ取得
        List<InventorySelectBean> selectFieldAll();

        // 追加処理
        void insertByInventory(
                        @Param("titleName") String titleName,
                        @Param("bigGenreId") Integer bigGenreId,
                        @Param("smallGenreId") Integer smallGenreId,
                        @Param("turns") Integer turns,
                        @Param("statusId") Integer statusId);

        // 削除処理
        void deleteByInventory(
                        @Param("id") Integer id);

        // 更新処理するデータを一行のみ選択肢取り出す
        InventoryControlEntity selectByInventoryId(
                        @Param("id") Integer id);

        void updateByInventory(
                        @Param("titleName") String titleName,
                        @Param("bigGenreId") Integer bigGenreId,
                        @Param("smallGenreId") Integer smallGenreId,
                        @Param("turns") Integer turns,
                        @Param("statusId") Integer statusId,
                        @Param("id") Integer id);

        // レンタル（貸出可⇒貸出中に更新）
        void updateByRentalInventory(
                        @Param("statusId") Integer statusId,
                        @Param("id") Integer id);

        //返却（貸出中⇒貸出可に更新
        void updateByReturnInventory(
                        @Param("statusId") Integer statusId,
                        @Param("id") Integer id);
}
