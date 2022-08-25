package com.example.petbird.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.petbird.bean.CartBean;
import com.example.petbird.entity_petbird.PetBirdEntity;
import com.example.petbird.mapper.T_CartMapper;
import com.example.petbird.mapper.T_ClientMapper;

@Service
public class BuyService {
    @Autowired
    private T_CartMapper cartMapper;
    @Autowired
    private T_ClientMapper clientMapper;
    @Autowired
    private CalculationService calculationService;
    
 
    public List<FieldError> errorCheck(List<CartBean> cartBeanList,BindingResult bindingResult ){

        List<FieldError> keepError = new ArrayList<>();

        for (CartBean cartBean : cartBeanList) {

            // t_cartに既にIdが登録されているかを確認(select 文で探す)
            CartBean alreadyInCart = cartMapper.seachCart(cartBean.getId());

            if (alreadyInCart != null) {// 有る→t_cartに値がある場合→updateCartBirdで更新する
                // 在庫から全てリストを持ってくる
                PetBirdEntity petBirdEntity = clientMapper.petBird(alreadyInCart.getId());
                // 対象のID,在庫の個数,選択した個数,カートの個数を引数とし、カートに入った数で在庫数を越えてないかチェック
                boolean overCount = calculationService.adjustment(cartBean.getId(), petBirdEntity.getCount(),alreadyInCart.getCount(),cartBean.getCount());
                // もし越えてたら
                if (overCount == false) {
                    FieldError fieldError = new FieldError(bindingResult.getObjectName(), "count", "数越えてます");
                    keepError.add(fieldError);
                }
            }
        }

        return keepError;
    }
        
      /*  public List<CartBean> inputOrUpdateCartBeanList(List<CartBean> cartBeanList){
        // For文で一行一行確認
        // List化されたものを一行にバラして、
        for (CartBean cartBean : cartBeanList) {
            cartBean.getId();
            cartBean.getCount();

            // t_cartに既にIdが登録されているかを確認(select 文で探す)
            CartBean alreadyInCart2 = cartMapper.seachCart(cartBean.getId());

            if (alreadyInCart2 != null) {// 有る→t_cartに値がある場合→updateCartBirdで更新する

                Integer cartResult = calculationService.cartCountReplace(cartBean.getId(), alreadyInCart2.getCount(),cartBean.getCount());
                cartMapper.updateCart(cartBean.getId(), cartResult);

            } else {// 無い→selectForm.IdCountBeanList().getId()とselectForm.IdCountBeanList().getCount()をinputCartBirdで登録する

                // カートに追加
                cartMapper.inputCart(cartBean.getId(), cartBean.getCount());

            }
            // t_cartと結合した一覧を出力し、格納
            selectForm.setCartAll(cartMapper.cartBirdAll(cartBean.getId()));
        }
        
        return alreadyInCart2;

    }*/ 
}

