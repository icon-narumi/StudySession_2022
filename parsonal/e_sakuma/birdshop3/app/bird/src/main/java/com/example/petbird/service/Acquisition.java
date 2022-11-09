package com.example.petbird.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petbird.bean.CountBean;
import com.example.petbird.bean.StrPetBirdBean;
import com.example.petbird.bean.UnitIdBean;
import com.example.petbird.form_client.SelectForm;
import com.example.petbird.mapper.T_ClientMapper;

@Service
public class Acquisition {
    @Autowired
    private T_ClientMapper clientMapper;
    @Autowired
    private MakeStrPetBirdBeanService makeStrPetBirdBeanService;


    public List<StrPetBirdBean> strPetBirdBeanAddCountBeanList(Integer[] id){

    // 最終的にカウントリストを持ったBeanをList化するためのインスタンスを用意
    List<StrPetBirdBean> strPetBirdBeanList = new ArrayList<>();
    
    // 複数のidを配列としてMapperで抽出し、該当行数分だけ変数checkに格納してList型でセット 
    // 前画面で☑で選択されたIDでSQL検索し、抽出した一行をcheckとしてList化する
    List<UnitIdBean> checked = clientMapper.idCheckedPetBirdList(id);

        // ☑で選択してList化された値を一行ずつ分解し、checkの行数分繰り返す
        for (UnitIdBean unitIdBean : checked) {

            StrPetBirdBean strPetBirdBean = new StrPetBirdBean();
            // カウント部分のリストを作成
            List<CountBean> countBeanList = new ArrayList<>();
            // ☑された一行のカウント部分をmaxCountへ代入
            Integer maxCount = unitIdBean.getCount();
            //最大値の数だけ-1を繰り返す
            for (int i = maxCount; i >= 1; i--) { // 最大値から減っていく

                CountBean countBean = new CountBean();
                // countBeanの各変数へ最大値までカウントした値をセットする
                countBean.setCount(i);
                countBean.setCountName(String.valueOf(i)+"羽");
                // セットしたcountBeanをList化する
                countBeanList.add(countBean);
            }
            // ☑で選択して変換した一行をpetBirdBeanへ代入
            strPetBirdBean = makeStrPetBirdBeanService.strChange(unitIdBean);
            // for文で作成済みのcountBeanListをpetBirdBeanの変数CountBeanListへセット
            strPetBirdBean.setCountBeanList(countBeanList);
            // petBirdBeanに必要な項目が揃ったためpetBirdBeanListにセット
            strPetBirdBeanList.add(strPetBirdBean);
        }
        return strPetBirdBeanList;
    }

    public List<StrPetBirdBean> strPetBirdBeanAddCountBeanList1(List<Integer> selectIdList){//selectIdListをselectIdの単体に分解して抽出した一行を再List化する

        SelectForm selectForm = new SelectForm();
        // 最終的にカウントリストを持ったBeanをList化するためのインスタンスを用意
        List<StrPetBirdBean> strPetBirdBeanList = new ArrayList<>();
        List<UnitIdBean> checked = new ArrayList<>();
        //selectIdListを分解して、Mapperで一行ずつ検索してList<UnitIdBean>へList化する
        for(Integer selectId : selectIdList){
            
            selectForm.setCartAll(clientMapper.idCheckedPickpPetBird(selectId));
            selectIdList.add(selectForm.getCartAll().getId());

        }
        
//        checked.add(clientMapper.idCheckedPickpPetBirdList(selectForm.getOnlyId()));
        
    
            // ☑で選択してList化された値を一行ずつ分解し、checkの行数分繰り返す
            for (UnitIdBean unitIdBean : checked) {
    
                StrPetBirdBean strPetBirdBean = new StrPetBirdBean();
                // カウント部分のリストを作成
                List<CountBean> countBeanList = new ArrayList<>();
                // ☑された一行のカウント部分をmaxCountへ代入
                Integer maxCount = unitIdBean.getCount();
                //最大値の数だけ-1を繰り返す
                for (int i = maxCount; i >= 1; i--) { // 最大値から減っていく
    
                    CountBean countBean = new CountBean();
                    // countBeanの各変数へ最大値までカウントした値をセットする
                    countBean.setCount(i);
                    countBean.setCountName(String.valueOf(i)+"羽");
                    // セットしたcountBeanをList化する
                    countBeanList.add(countBean);
                }
                // ☑で選択して変換した一行をpetBirdBeanへ代入
                strPetBirdBean = makeStrPetBirdBeanService.strChange(unitIdBean);
                // for文で作成済みのcountBeanListをpetBirdBeanの変数CountBeanListへセット
                strPetBirdBean.setCountBeanList(countBeanList);
                // petBirdBeanに必要な項目が揃ったためpetBirdBeanListにセット
                strPetBirdBeanList.add(strPetBirdBean);
            }
            return strPetBirdBeanList;
        }
    /*  
    public List<StrPetBirdBean> strPetBirdBeanAddCountBeanList2(Integer Id){

        // 最終的にカウントリストを持ったBeanをList化するためのインスタンスを用意
        List<StrPetBirdBean> strPetBirdBeanList = new ArrayList<>();
        
        // 複数のidを配列としてMapperで抽出し、該当行数分だけ変数checkに格納してList型でセット 
        // 前画面で☑で選択されたIDでSQL検索し、抽出した一行をcheckとしてList化する
        List<UnitIdBean> check = clientMapper.idCheckedPickpPetBirdList(Id);
    
            // ☑で選択してList化された値を一行ずつ分解し、checkの行数分繰り返す
            for (UnitIdBean unitIdBean : check) {
    
                StrPetBirdBean strpetBirdBean = new StrPetBirdBean();
                // ☑されたIDをpetBirdBeanのIdへセット
                // petBirdBean.setId(String.valueOf(castEntity.getId()));
    
                // カウント部分のリストを作成
                List<CountBean> countBeanList = new ArrayList<>();
                // ☑された一行のカウント部分をmaxCountへ代入
                Integer maxCount = unitIdBean.getCount();
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
                strpetBirdBean = makeStrPetBirdBeanService.strChange(unitIdBean);
                // for文で作成済みのcountBeanListをpetBirdBeanの変数CountBeanListへセット
                strpetBirdBean.setCountBeanList(countBeanList);
                // petBirdBeanに必要な項目が揃ったためpetBirdBeanListにセット
                strPetBirdBeanList.add(strpetBirdBean);
            }
            return strPetBirdBeanList;
        }
    */
}
