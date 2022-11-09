package com.example.petbird.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.petbird.bean.UnitIdBean;

@Service
public class CalculationService {
    
 //petBirdEntityの一行からgetCountで数を取り出してIntegerに変換する
 public Integer countReplace(Integer id,Integer db,Integer in){

    //引き算した結果を代入する
    Integer result = db-in;
    //もし結果が-だったらif文で0にする
    if(result < 0 ){
        //0以下だったら0に置き換える
        result = 0;
    }else{

        return result;    
    }
    //計算結果、又は0を返す
    return result;    
}

/*
//petBirdEntityの一行からgetCountで数を取り出してIntegerに変換する
public Integer cartCountReplace(Integer id,Integer db,Integer in){

    //足し算した結果を代入する
    Integer result = db+in;
    
    return result;    
}
 */

//金額を計算するメソッド。残羽数よりも大きな値が入力された場合は自動的に合わせる
public Integer total(Integer price,Integer count,Integer db){

    Integer result = 0;

    if(db<count){
        count = db;
        result = price * db;
    }else{
        result = price * count;
    }

    return result;
}

//カート内の金額を計算
public Integer cartTotal(Integer price,Integer count){

    Integer result = 0;

    result = price * count;
    
    return result;
}

public Integer countFix(Integer id,Integer db,Integer in){

    //引き算した結果を代入する
    Integer result = db-in;
    //もし結果が-だったらif文で0にする
    if(result < 0 ){
        //0以下だったらdbに置き換える
        result = db;
    }else{

        result = in;
        return result;    
    }
    //計算結果、又はdbを返す
    return result;    
}

//cartと新たに選択される個体数の合計が在庫数より多かったらエラー
public Boolean adjustment(Integer id,Integer pCount,Integer cCount,Integer nCount){//pCiunt →元の数 cCount→カートの数 nCount→新しく入力される個数

        //false
        if(pCount<cCount+nCount){
           
            return false;
           
        //true
        }
   
    return true;
}

public String totalPrice(List<UnitIdBean> cartAllList){

    String totalPrice= "";
    // 計算結果を格納しておく場所を作る
    Integer result = 0;
    // for文でt_cartの行数分繰り返す
    // カートの個数
    for (UnitIdBean cart : cartAllList) {
        cart.getId();
        cart.getPrice();
        cart.getCount();
        // t_petbirdとt_cartを結合し、t_cartのcountとt_petbirdのpridceを掛けた結果を格納して、掛けたものを足す
        result = result + cartTotal(cart.getCount(), cart.getPrice());
    }
    // buyFormに格納して出力
    totalPrice = ("¥" + (String.format("%,d", result)));

    return totalPrice;
}

}