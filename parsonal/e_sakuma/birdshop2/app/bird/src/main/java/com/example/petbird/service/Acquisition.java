package com.example.petbird.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petbird.bean.CountBean;
import com.example.petbird.bean.PetBirdBean;
import com.example.petbird.entity.CastPetBirdEntity;
import com.example.petbird.mapper.PetBirdMapper;


@Service
public class Acquisition {

    @Autowired
    private PetBirdMapper petBirdMapper;
    @Autowired
    private CastService castService;


    public List<PetBirdBean> getData(Integer[] Id){

    // 最終的にカウントリストを持ったBeanをList化するためのインスタンスを用意
    List<PetBirdBean> petBirdBeanList = new ArrayList<>();
    
    // 複数のidを配列としてMapperで抽出し、該当行数分だけ変数checkに格納してList型でセット 
    // 前画面で☑で選択されたIDでSQL検索し、抽出した一行をcheckとしてList化する
    List<CastPetBirdEntity> check = petBirdMapper.commitBirdList(Id);

        // ☑で選択してList化された値を一行ずつ分解し、checkの行数分繰り返す
        for (CastPetBirdEntity castEntity : check) {

            PetBirdBean petBirdBean = new PetBirdBean();
            // ☑されたIDをpetBirdBeanのIdへセット
            // petBirdBean.setId(String.valueOf(castEntity.getId()));

            // カウント部分のリストを作成
            List<CountBean> countBeanList = new ArrayList<>();
            // ☑された一行のカウント部分をmaxCountへ代入
            Integer maxCount = castEntity.getCount();
            // for(int i = 1 ; i < maxCount ; i++){ 1から増えてく
            for (int i = maxCount; i >= 1; i--) { // 最大値から減っていく

                CountBean countBean = new CountBean();

                // countBeanの各変数へ最大値までカウントした値をセットする
                countBean.setCount(i);
                countBean.setCountName(String.valueOf(i));
                // セットしたcountBeanをList化する
                countBeanList.add(countBean);
            }
            // ☑で選択して変換した一行をpetBirdBeanへ代入
            petBirdBean = castService.strCast(castEntity);
            // for文で作成済みのcountBeanListをpetBirdBeanの変数CountBeanListへセット
            petBirdBean.setCountBeanList(countBeanList);
            // petBirdBeanに必要な項目が揃ったためpetBirdBeanListにセット
            petBirdBeanList.add(petBirdBean);
        }
        return petBirdBeanList;
    }

    public List<PetBirdBean> setData(Integer Id){

        // 最終的にカウントリストを持ったBeanをList化するためのインスタンスを用意
        List<PetBirdBean> petBirdBeanList = new ArrayList<>();
        
        // 複数のidを配列としてMapperで抽出し、該当行数分だけ変数checkに格納してList型でセット 
        // 前画面で☑で選択されたIDでSQL検索し、抽出した一行をcheckとしてList化する
        List<CastPetBirdEntity> check = petBirdMapper.returnBirdList(Id);
    
            // ☑で選択してList化された値を一行ずつ分解し、checkの行数分繰り返す
            for (CastPetBirdEntity castEntity : check) {
    
                PetBirdBean petBirdBean = new PetBirdBean();
                // ☑されたIDをpetBirdBeanのIdへセット
                // petBirdBean.setId(String.valueOf(castEntity.getId()));
    
                // カウント部分のリストを作成
                List<CountBean> countBeanList = new ArrayList<>();
                // ☑された一行のカウント部分をmaxCountへ代入
                Integer maxCount = castEntity.getCount();
                // for(int i = 1 ; i < maxCount ; i++){ 1から増えてく
                for (int i = maxCount; i >= 1; i--) { // 最大値から減っていく
    
                    CountBean countBean = new CountBean();
    
                    // countBeanの各変数へ最大値までカウントした値をセットする
                    countBean.setCount(i);
                    countBean.setCountName(String.valueOf(i));
                    // セットしたcountBeanをList化する
                    countBeanList.add(countBean);
                }
                // ☑で選択して変換した一行をpetBirdBeanへ代入
                petBirdBean = castService.strCast(castEntity);
                // for文で作成済みのcountBeanListをpetBirdBeanの変数CountBeanListへセット
                petBirdBean.setCountBeanList(countBeanList);
                // petBirdBeanに必要な項目が揃ったためpetBirdBeanListにセット
                petBirdBeanList.add(petBirdBean);
            }
            return petBirdBeanList;
        }
}

