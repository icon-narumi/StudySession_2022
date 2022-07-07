package com.example.rental2.service;

import java.util.List;

import com.example.rental2.entity.CustmerEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegiService {

    @Autowired
    CustomerRegistService kakakuService;

    public List<CustmerEntity> getItemList() {
        return kakakuService.selectAll();
    }

    public String getResultMessage(String item, Integer num) {

        return "result";
    }
}
