package com.example.petbird.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.petbird.bean.CartBean;
import com.example.petbird.bean.UnitIdBean;
import com.example.petbird.entity_petbird.PetBirdEntity;
import com.example.petbird.form_client.BuyForm;
import com.example.petbird.form_client.BuyResultForm;
import com.example.petbird.form_client.SearchForm;
import com.example.petbird.form_client.SelectForm;
import com.example.petbird.form_login.PetBirdForm;
import com.example.petbird.mapper.T_CartMapper;
import com.example.petbird.mapper.T_ClientMapper;
import com.example.petbird.service.Acquisition;
import com.example.petbird.service.BuyService;
import com.example.petbird.service.CalculationService;
import com.example.petbird.service.MakeStrPetBirdBeanService;

@Controller
public class ClientController {
    @Autowired
    private T_ClientMapper clientMapper;
    @Autowired
    private MakeStrPetBirdBeanService makeStrPetBirdBeanService;
    @Autowired
    private CalculationService calculationService;    
    @Autowired
    private Acquisition acquisition;
    @Autowired
    private T_CartMapper cartMapper;
    @Autowired
    private BuyService buyService;

    @PostMapping("/petbird/search")
    public String petbirdLoginExecute(@ModelAttribute PetBirdForm petBirdForm, BindingResult error, Model model) {
                
        SearchForm searchForm = new SearchForm();

        if(petBirdForm.getSpecies() == null && petBirdForm.getSex() == null && petBirdForm.getColor() == null ){

            searchForm.setComment("何かしら選択してください");

            model.addAttribute("searchForm", searchForm);
            return "c_searchError";

        }else{
            //xmlファイルでＩｆ文を用いた書き方ができるらしい
        searchForm.setStrPetBirdBeanList(makeStrPetBirdBeanService.strChangeList(clientMapper.selectBird(petBirdForm.getSpecies(),petBirdForm.getSex(),petBirdForm.getColor())));

        }

        model.addAttribute("searchForm", searchForm);
        return "c_search";     
    }


    @PostMapping("/search/select")
    public String searchSelectExecute(@ModelAttribute SearchForm searchForm, BindingResult error, Model model) {
                
        SelectForm selectForm = new SelectForm();
        
        selectForm.setId(searchForm.getId());

        if (searchForm.getId() != null) {

            // カウントリストを持ったstrPetBirdBeanListを、出力用のselectForm.setBeanListへセット
            selectForm.setStrPetBirdBeanList(acquisition.strPetBirdBeanAddCountBeanList(searchForm.getId()));

        } else {

            searchForm.setComment("鳥が選択されていません。HOMEからやり直してください");
            return "c_searchError";
        }

        model.addAttribute("selectForm", selectForm);
        return "c_select";     
    }

    @PostMapping("/search/select/buy")
    public String searchSelectBuyExecute(@ModelAttribute SelectForm selectForm, BindingResult bindingResult, Model model) {
                
        BuyForm buyForm = new BuyForm();
        List<CartBean> cartBeanList = selectForm.getCartBeanList();

        List<FieldError> errorCheckList = buyService.errorCheck(selectForm.getCartBeanList(), bindingResult);
        for (FieldError errorCheck : errorCheckList) {
                    
        // エラーを追加
        bindingResult.addError(errorCheck);
        }
        // エラーメッセージを出す
        if (bindingResult.hasErrors()) {
            //カートリストを再表示
            buyForm.setStrPetBirdBeanList(acquisition.strPetBirdBeanAddCountBeanList(selectForm.getId()));//ここに配列を入れなければいけない//取れてない
            return "c_select";
        }
        
        // For文で一行一行確認
        // List化されたものを一行にバラして、
        for (CartBean cartBean : cartBeanList) {
            cartBean.getId();
            cartBean.getCount();

            // t_cartに既にIdが登録されているかを確認(select 文で探す)
            CartBean alreadyInCart = cartMapper.seachCart(cartBean.getId());

            if (alreadyInCart != null) {// 有る→t_cartに値がある場合→updateCartBirdで更新する

                Integer cartResult = calculationService.cartCountReplace(cartBean.getId(), alreadyInCart.getCount(),cartBean.getCount());
                cartMapper.updateCart(cartBean.getId(), cartResult);

            } else {// 無い→selectForm.IdCountBeanList().getId()とselectForm.IdCountBeanList().getCount()をinputCartBirdで登録する
                // カートに追加
                cartMapper.inputCart(cartBean.getId(), cartBean.getCount());
            }
            // t_cartと結合した一覧を出力し、格納
            selectForm.setCartAllList(cartMapper.cartBirdAll());
        }
        //計算サービスへ
        List<UnitIdBean> cartAllList = cartMapper.searchCartAll();
        buyForm.setStrPetBirdBeanList((makeStrPetBirdBeanService.strChangeList(cartAllList)));
        buyForm.setTotalPrice(calculationService.totalPrice(cartAllList));
    
        buyForm.setComment("ご購入金額");
    
        model.addAttribute("buyForm", buyForm);
        return "c_buy";     
    }

    @RequestMapping(value ="/search/select/buy/buyResult", params = "delete", method = RequestMethod.POST)
    public String deleteExecute(@RequestParam Integer delete, @ModelAttribute BuyForm buyForm, Model model) {
        
        Integer id = delete;
        cartMapper.cartDeleteOnly(id);
        
        model.addAttribute("buyForm",buyForm);
        return "c_buyResult";     
    }

    @PostMapping("/search/select/buy/buyResult")
    public String totalExecute(@ModelAttribute BuyForm buyForm, Model model) {
        
        BuyResultForm buyResultForm = new BuyResultForm();

        List<UnitIdBean> unitIdBeanList = cartMapper.cartBirdAll();

        // Listから一行取出す
        for (UnitIdBean unitIdBean : unitIdBeanList) {
            unitIdBean.getId();
            unitIdBean.getCount();

            // t_petbirdの対象の一行から個体数のみ抜出して代入
            PetBirdEntity petBirdId = clientMapper.petBird(unitIdBean.getId());
            Integer petBirdResult = calculationService.countReplace(unitIdBean.getId(), petBirdId.getCount(), unitIdBean.getCount());
            // t_petbirdもupdateBirdにて更新する(Countをt_petbird -
            // selectForm.IdCountBeanList().getCount() で出す)
            clientMapper.updatePetBird(unitIdBean.getId(), petBirdResult);

        }

        // 購入するボタンを押したらカートの中が消える
        cartMapper.cartDeleteAll();

        buyResultForm.setComment("可愛がってあげてください");


        
        model.addAttribute("buyForm",buyForm);
        return "c_buyResult";     
    }

}
