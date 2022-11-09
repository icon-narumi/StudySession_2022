package com.example.petbird.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.petbird.bean.CartBean;
import com.example.petbird.bean.StrPetBirdBean;
import com.example.petbird.bean.UnitIdBean;

@Mapper
public interface T_CartMapper {
    
    //カートに入っているかを確認
    CartBean seachCart(@Param("id") Integer id);
    //CartBeanの中身(IDとCountのみ)を全て検索
    List<CartBean> seachCartBeanAll();
    //カートを更新
    void updateCart(@Param("id") Integer id,@Param("count") Integer count);
    //カートへ追加
    void inputCart(@Param("id") Integer id,@Param("count") Integer count);
    //カートに入っている鳥を在庫テーブルと繋げて表示
    List<UnitIdBean> checkedCartBird(@Param("id") Integer[] id);
    //カート画面で削除ボタンを押した際に実行
    void cartDeleteOnly(@Param("id") Integer id);
    //購入と共にカートを空にする
    void cartDeleteAll();
    //引数無しで全てのカートの中身を在庫表と繋げて抽出する
    List<UnitIdBean> searchCartAll();
    //選択されたカートのIDと個数のみを複数抽出する
    List<StrPetBirdBean> cartBeanAlreadyInCart(@Param("id") Integer[] id);

}
