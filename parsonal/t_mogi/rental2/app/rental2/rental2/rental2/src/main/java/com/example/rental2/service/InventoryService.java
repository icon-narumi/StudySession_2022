package com.example.rental2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rental2.entity.InventoryControlEntity;
import com.example.rental2.mapper.InventoryMapper;

@Service
public class InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;
    // 在庫データの取り出し
public List<InventoryControlEntity> selectAll(){
    
    return inventoryMapper.selectAll();
}

}
