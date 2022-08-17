package com.example.rental2.mapper.inventory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.rental2.bean.InventorySelectBean;
import com.example.rental2.entity.inventory.InventoryControlEntity;

@Mapper
public interface InventoryMapper {

    // 在庫状況を何もデータベース上で手を加えずに全て取り出す。
    @Select("select * from inventorycontrol")
    List<InventoryControlEntity> selectAll();

    // 在庫状況をテーブル結合で表示
    // 作成途中
    @Select("select i.titlename,b.biggenre,s.smallgenre,i.turns,t.status,i.id "
            + "from inventorycontrol i "
            + "left join biggenre b on i.biggenre = b.biggenreid "
            + "left join smallgenre s on i.smallgenre = s.smallgenreid "
            + "left join status t on i.status = t.statusid")
    List<InventorySelectBean> selectByInventoryInformation();
}
