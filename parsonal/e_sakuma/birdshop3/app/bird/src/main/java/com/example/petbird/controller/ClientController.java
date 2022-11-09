package com.example.petbird.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    
    @PostMapping("/petbird/search")
    public String petbirdLoginExecute(@ModelAttribute PetBirdForm petBirdForm, BindingResult error, Model model) {
                
        SearchForm searchForm = new SearchForm();

        if(petBirdForm.getSpecies() == null && petBirdForm.getSex() == null && petBirdForm.getColor() == null ){

            searchForm.setComment("何かしら選択してください");

            model.addAttribute("searchForm", searchForm);
            return "c_searchError";

        }else{
        
            searchForm.setStrPetBirdBeanList(makeStrPetBirdBeanService.strChangeList(clientMapper.selectBird(petBirdForm.getSpecies(),petBirdForm.getSex(),petBirdForm.getColor())));

        }

        model.addAttribute("searchForm", searchForm);
        return "c_search";     
    }


    @PostMapping("/search/select")
    public String searchSelectExecute(@ModelAttribute SearchForm searchForm, BindingResult error, Model model) {
                
        SelectForm selectForm = new SelectForm();
    
        if (searchForm.getId() != null) {

            // カウントリストを持ったpetBirdBeanListを、出力用のselectForm.setBeanListへセット
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
                
        //なにもチェックが入ってなかったらエラー画面へ        
        if (selectForm.getId() == null) {

            SearchForm searchForm = new SearchForm();
            searchForm.setComment("カートへ入れるものに✓して下さい");
            model.addAttribute("searchForm", searchForm);
            return "c_searchError";

        }

        List<Integer> checkedIdList = new ArrayList<>();
        List<CartBean> cartBeanList1 = new ArrayList<>();

        //配列のIDをバラけさして、単体のIdに入れる
        for (Integer checked : selectForm.getId()) {
            checkedIdList.add(checked);//これをselectForm.setCartBeanList();に入れたい
        }
        
        //selectForm.getCartBeanList()を一度分解
            for(CartBean cartBeanLine:selectForm.getCartBeanList()){//最終的にselectForm.getCartBeanList()に✓したもののみを格納したい
                //✓入っているIDと照らし合わせる
                if(cartBeanLine.getId() == selectForm.getOnlyId()){
                    //入っているやつのみ再List化する
                    cartBeanLine.setId(selectForm.getOnlyId());
                }
                cartBeanList1.add(cartBeanLine);
            }
        

        selectForm.setCartBeanList(cartBeanList1);
        
        /*
        List<FieldError> errorCheckList = buyService.errorCheck(selectForm.getCartBeanList(), bindingResult);

        for (FieldError errorCheck : errorCheckList) {
                    
        // エラーを追加
        bindingResult.addError(errorCheck);
        }

        List<CartBean> cartBeanList = selectForm.getCartBeanList();
        
        // エラーメッセージを出す
        if (bindingResult.hasErrors()) {
            List<Integer> selectIdList = new ArrayList<>();
            /*IdのみをList化した変数を作り、List化したやつ
            for (CartBean cartBean : cartBeanList) {
                
                selectIdList.add(cartBean.getId());
            }
            selectIdList=selectForm.getSelectIdList();
            Integer[] selectIdArray = selectIdList.toArray(new Integer[selectIdList.size()]);

            //カートリストを再表示
            selectForm.setStrPetBirdBeanList(acquisition.strPetBirdBeanAddCountBeanList(selectIdArray));//ここに配列を入れなければいけない//取れてない
            
            model.addAttribute("selectForm", selectForm);
            return "c_select";
        }
        */
        // For文で一行一行確認
        //配列のIDをバラけさして、単体のIdに入れる
        /*for (Integer checkedId : checkedIdList) {
            
        }*/
        
            for (CartBean cartBean : cartBeanList1) {
                //selectで✓したIdに✓を付けていたら
                if(cartBean.getId() != null){
                    // t_cartに既にIdが登録されているかを確認(select 文で探す)
                     CartBean alreadyInCart = cartMapper.seachCart(cartBean.getId());

                    if (alreadyInCart == null) {//無い→selectForm.IdCountBeanList().getId()とselectForm.IdCountBeanList().getCount()をinputCartBirdで登録する
                        // カートに追加
                        cartMapper.inputCart(cartBean.getId(), cartBean.getCount());
                        
                    } else {//有る→t_cartに値がある場合→updateCartBirdで更新する
                    
                        Integer cartResult = cartBean.getCount();
                        cartMapper.updateCart(cartBean.getId(), cartResult);
                    }
                }         
                // t_cartと結合した一覧を出力し、格納
                selectForm.setCartAllList(cartMapper.checkedCartBird(selectForm.getId()));            
            }

        BuyForm buyForm = new BuyForm();
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
        //計算サービスへ
        List<UnitIdBean> cartAllList = cartMapper.searchCartAll();
        buyForm.setStrPetBirdBeanList((makeStrPetBirdBeanService.strChangeList(cartAllList)));
        buyForm.setTotalPrice(calculationService.totalPrice(cartAllList));
        
        model.addAttribute("buyForm",buyForm);
        return "c_buy";     
    }

    @PostMapping("/search/select/buy/buyResult")
    public String totalExecute(@ModelAttribute SelectForm selectForm, Model model) {
        
        BuyResultForm buyResultForm = new BuyResultForm();

        List<UnitIdBean> unitIdBeanList = cartMapper.checkedCartBird(selectForm.getId());

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
   
        model.addAttribute("buyResultForm",buyResultForm);
        return "c_buyResult";     
    }

    @PostMapping("/cart")
    public String cartExecute(@ModelAttribute SelectForm selectForm, Model model) {

        BuyForm buyForm = new BuyForm();

        // カートの中身を表示
        List<UnitIdBean> cartList = cartMapper.searchCartAll();
        buyForm.setStrPetBirdBeanList(makeStrPetBirdBeanService.strChangeList(cartList));

        // カートの中身を再計算
        // 掛けた結果を格納しておく場所を作る
        Integer result = 0;
        for (UnitIdBean cart : cartList) {
            Integer cartCount = cart.getCount();
            Integer cartPrice = cart.getPrice();
            // t_petbirdとt_cartを結合し、t_cartのcountとt_petbirdのpridceを掛けた結果を格納して、掛けたものを足す
            result = result + calculationService.cartTotal(cartCount, cartPrice);
        }

        // buyFormに格納して出力
        buyForm.setTotalPrice("¥" + (String.format("%,d", result)));

        model.addAttribute("buyForm", buyForm);
        return "c_buy";

    }

}
