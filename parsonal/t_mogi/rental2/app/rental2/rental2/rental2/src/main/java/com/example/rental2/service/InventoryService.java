package com.example.rental2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rental2.bean.InventorySelectBean;
import com.example.rental2.entity.inventory.BigGenreEntity;
import com.example.rental2.entity.inventory.InventoryControlEntity;
import com.example.rental2.entity.inventory.SmallGenreEntity;
import com.example.rental2.entity.inventory.StatusEntity;
import com.example.rental2.mapper.inventory.BiggenreMapper;
import com.example.rental2.mapper.inventory.InventoryMapper;
import com.example.rental2.mapper.inventory.SmallGenreMapper;
import com.example.rental2.mapper.inventory.StatusMapper;

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

    /// *トランザクションテーブルの呼び出し*/
    public List<InventorySelectBean> selectByInventoryInformation(String titleName, Integer bigGenreId,
            Integer smallGenreId) {

        List<InventorySelectBean> selectList;
        // タイトルのみ入力した場合(ジャンルが両方空だった場合)
        // if (bigGenre == 0 && smallGenre == 0) {

        // selectList = inventoryMapper.selectByNotGenre(titleName);

        // // 大ジャンルのみ入力した場合(小ジャンルとタイトルが空だった場合)
        // } else if (smallGenre == 0) {
        // selectList = inventoryMapper.selectByBigGenre(titleName, bigGenre);
        // // 小ジャンルのみ入力した場合(タイトルと大ジャンルが空の場合)
        // } else if (bigGenre == 0) {
        // selectList = inventoryMapper.selectBySmallGenre(titleName, smallGenre);
        // // 全部入力した場合
        // } else {
        // selectList = inventoryMapper.selectField(titleName, bigGenre, smallGenre);
        // }
        selectList = inventoryMapper.selectField(titleName, bigGenreId, smallGenreId);
        return selectList;

        /// * return inventoryMapper.selectByInventoryInformation();*/
    }

    // 全てのデータを取得
    public List<InventorySelectBean> selectFieldAll() {
        return inventoryMapper.selectFieldAll();
    }

    // レンタルでの検索
    // 条件検索に貸出可を追加。
    public List<InventorySelectBean> selectRentalField(String titleName, Integer bigGenreId, Integer smallGenreId,
            Integer statusId) {
        List<InventorySelectBean> rentalSelectList;

        rentalSelectList = inventoryMapper.selectRentalField(titleName, bigGenreId, smallGenreId, statusId);
        return rentalSelectList;
    }

    /// *Biggenreマスターの呼び出し*/
    // 初期表示で---を表示
    public List<BigGenreEntity> selectBigGenreAll() {
        List<BigGenreEntity> BigGenreList = new ArrayList<BigGenreEntity>();

        BigGenreEntity bigGenreEntity = new BigGenreEntity();
        bigGenreEntity.setBigGenreId(0);
        bigGenreEntity.setBigGenre("---");
        BigGenreList.add(bigGenreEntity);
        BigGenreList.addAll(bigGenreMapper.selectAll());
        return BigGenreList;

    }

    /// *smallgenreマスターの呼び出し*/
    // 初期表示で---を表示
    public List<SmallGenreEntity> selectSmallGenreAll() {
        List<SmallGenreEntity> smallGenreList = new ArrayList<SmallGenreEntity>();

        SmallGenreEntity smallGenreEntity = new SmallGenreEntity();
        smallGenreEntity.setSmallGenreId(0);
        smallGenreEntity.setSmallGenre("---");
        smallGenreList.add(smallGenreEntity);
        smallGenreList.addAll(smallGenreMapper.selectAll());
        return smallGenreList;

    }

    /// *statusマスターの呼び出し*/
    public List<StatusEntity> selectStatusAll() {
        return statusMapper.selectAll();
    }

    // 在庫追加処理
    public void insertByInventory(String titleName, Integer bigGenreId,
            Integer smallGenreId, Integer turns, Integer statusId) {
        inventoryMapper.insertByInventory(titleName, bigGenreId, smallGenreId, turns, statusId);

    }

    // 在庫削除処理
    public void deleteByInventory(Integer id) {
        inventoryMapper.deleteByInventory(id);
    }

    // 更新対象データを一行だけ取り出し
    public InventoryControlEntity updateSelectByInventory(Integer id) {
        InventoryControlEntity listInventoryResult = inventoryMapper.selectByInventoryId(id);
        return listInventoryResult;
    }

    // 更新データ処理
    public void updateByInventory(String titleName, Integer bigGenreId,
            Integer smallGenreId, Integer turns, Integer statusId, Integer id) {
        inventoryMapper.updateByInventory(titleName, bigGenreId, smallGenreId, turns, statusId, id);

    }

    // レンタル処理
    // 貸出可⇒貸出中に変更
    public void updateByRentalInventory(Integer statusId, Integer id) {
        inventoryMapper.updateByRentalInventory(statusId, id);
    }

}
