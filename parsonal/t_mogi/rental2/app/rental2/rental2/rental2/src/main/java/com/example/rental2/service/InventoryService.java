package com.example.rental2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rental2.bean.InventorySelectBean;
import com.example.rental2.entity.BigGenreEntity;
import com.example.rental2.entity.InventoryControlEntity;
import com.example.rental2.entity.SmallGenreEntity;
import com.example.rental2.entity.StatusEntity;
import com.example.rental2.mapper.BiggenreMapper;
import com.example.rental2.mapper.InventoryMapper;
import com.example.rental2.mapper.SmallGenreMapper;
import com.example.rental2.mapper.StatusMapper;


@Service
public class InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private BiggenreMapper bigGenreMapper;
    @Autowired
    private SmallGenreMapper smallGenreMapper;
    @Autowired
    private StatusMapper statusMapper;

    // 在庫データの取り出し
    public List<InventoryControlEntity> selectAll() {

        return inventoryMapper.selectAll();
    }

    ///*トランザクションテーブルの呼び出し*/
    public List<InventorySelectBean> selectByInventoryInformation() {
        return inventoryMapper.selectByInventoryInformation();
    }
    ///*Biggenreマスターの呼び出し*/
    public List<BigGenreEntity> selectBigGenreAll() {
        return bigGenreMapper.selectAll();
    }
    ///*smallgenreマスターの呼び出し*/
    public List<SmallGenreEntity> selectSmallGenreAll() {
        return smallGenreMapper.selectAll();
    }
    ///*statusマスターの呼び出し*/
    public List<StatusEntity> selectStatusAll(){
        return statusMapper.selectAll();
    }

}
