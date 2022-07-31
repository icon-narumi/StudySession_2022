package com.example.rental2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.rental2.bean.InventorySelectBean;
import com.example.rental2.entity.InventoryControlEntity;

public interface InventoryMapper {
    
    //在庫状況を何もデータベース上で手を加えずに全て取り出す。
    @Select("select * from inventorycontrol")
    List<InventoryControlEntity> selectAll() ;

    //在庫状況をテーブル結合で表示
    //作成途中
    @Select("SELECT id, name FROM users WHERE id = #{id}")
    List<InventorySelectBean> selectByInventoryInformation();
}
