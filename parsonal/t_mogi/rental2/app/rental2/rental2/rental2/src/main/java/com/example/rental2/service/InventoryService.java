package com.example.rental2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rental2.bean.InventorySelectBean;
import com.example.rental2.entity.BigGenreEntity;
import com.example.rental2.entity.InventoryControlEntity;
import com.example.rental2.entity.SmallGenreEntity;
import com.example.rental2.mapper.BiggenreMapper;
import com.example.rental2.mapper.InventoryMapper;
import com.example.rental2.mapper.SmallGenreMapper;

@Service
public class InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    private BiggenreMapper biggenreMapper;

    private SmallGenreMapper smallGenreMapper;

    // 在庫データの取り出し
    public List<InventoryControlEntity> selectAll() {

        return inventoryMapper.selectAll();
    }

    public List<InventorySelectBean> selectByInventoryInformation() {
        return inventoryMapper.selectByInventoryInformation();
    }

    public List<BigGenreEntity> selectBigGenreAll() {
        return biggenreMapper.selectAll();
    }

    public List<SmallGenreEntity> selectSmallGenreAll() {
        return smallGenreMapper.selectAll();
    }

}
