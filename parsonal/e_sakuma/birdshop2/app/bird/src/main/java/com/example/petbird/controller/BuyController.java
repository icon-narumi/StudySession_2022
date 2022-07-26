package com.example.petbird.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.example.petbird.bean.CountBean;
import com.example.petbird.bean.IdCountBean;
import com.example.petbird.bean.PetBirdBean;
import com.example.petbird.entity.CastPetBirdEntity;
import com.example.petbird.entity.PetBirdEntity;
import com.example.petbird.form.BuyForm;
import com.example.petbird.form.SearchForm;
import com.example.petbird.form.SelectForm;
import com.example.petbird.form.ThanksForm;
import com.example.petbird.mapper.PetBirdMapper;
import com.example.petbird.service.Acquisition;
import com.example.petbird.service.CalculationService;
import com.example.petbird.service.CastService;

@Controller
public class BuyController {

    @Autowired
    private PetBirdMapper petBirdMapper;
    @Autowired
    private CastService castService;
    @Autowired
    private CalculationService calculationService;
    @Autowired
    private Acquisition acquisition;

    @PostMapping("/select")
    public String selectExecute(@ModelAttribute SearchForm searchForm, Model model) {// Form→与えるForm

        SelectForm selectForm = new SelectForm();

        if (searchForm.getId() != null) {

            // 最終的にカウントリストを持ったBeanをList化するためのインスタンスを用意
            List<PetBirdBean> petBirdBeanList = new ArrayList<>();
            
            // カウントリストを持ったpetBirdBeanListを、出力用のselectForm.setBeanListへセット
            selectForm.setBeanList(acquisition.getData(petBirdBeanList));

        } else {

            searchForm.setComment("鳥が選択されていません。HOMEからやり直してください");
            return "coution";
        }

        model.addAttribute("selectForm", selectForm);// →受取るForm
        return "select";
    }

    @PostMapping("/buy") // 合計を出してカートへ入れる
    public String buyExecute(@ModelAttribute SelectForm selectForm, BindingResult bindingResult, Model model) {

        BuyForm buyForm = new BuyForm();

        // selectForm.getIdCountBeanList()にはHTMLより受取った行数分格納されている
        List<IdCountBean> idCountBeanList = selectForm.getIdCountBeanList();
        // チェックする
        for (IdCountBean idcount : idCountBeanList) {
            idcount.getId();
            idcount.getCount();

            // t_cartに既にIdが登録されているかを確認(select 文で探す)
            IdCountBean idCountBean = petBirdMapper.seachCartBird(idcount.getId());

            if (idCountBean != null) {// 有る→t_cartに値がある場合→updateCartBirdで更新する
                // 在庫から全てリストを持ってくる
                PetBirdEntity petBirdEntity = petBirdMapper.PetBird(selectForm.getId());
                // 対象のID,在庫の個数,選択した個数,カートの個数を引数とし、カートに入った数で在庫数を越えてないかチェック
                boolean a = calculationService.adjustment(idcount.getId(), petBirdEntity.getCount(),
                        idCountBean.getCount(), idcount.getCount());
                // もし越えてたら
                if (a == false) {
                    FieldError fieldError = new FieldError(bindingResult.getObjectName(), "count", "数越えてます");
                    // エラーを追加
                    bindingResult.addError(fieldError);
                }
            }
        }
        // エラーメッセージを出す
        if (bindingResult.hasErrors()) {
            return "select";
        }
        // For文で一行一行確認
        // List化されたものを一行にバラして、
        for (IdCountBean idcount : idCountBeanList) {
            idcount.getId();
            idcount.getCount();

            // t_cartに既にIdが登録されているかを確認(select 文で探す)
            IdCountBean idCountBean = petBirdMapper.seachCartBird(idcount.getId());

            if (idCountBean != null) {// 有る→t_cartに値がある場合→updateCartBirdで更新する

                Integer cartResult = calculationService.cartCountReplace(idcount.getId(), idCountBean.getCount(),
                        idcount.getCount());
                petBirdMapper.updateCartBird(idcount.getId(), cartResult);

            } else {// 無い→selectForm.IdCountBeanList().getId()とselectForm.IdCountBeanList().getCount()をinputCartBirdで登録する

                // カートに追加
                petBirdMapper.inputCartBird(idcount.getId(), idcount.getCount());

            }
            // t_cartと結合した一覧を出力し、格納
            selectForm.setCheck(petBirdMapper.cartBirdList(idcount.getId()));
        }

        // 計算結果を格納しておく場所を作る
        Integer result = 0;
        // for文でt_cartの行数分繰り返す
        List<CastPetBirdEntity> cartList = petBirdMapper.seachCartAll();
        buyForm.setChecks(castService.strList(cartList));
        // カートの個数
        for (CastPetBirdEntity cart : cartList) {
            cart.getId();
            cart.getPrice();
            cart.getCount();
            // t_petbirdとt_cartを結合し、t_cartのcountとt_petbirdのpridceを掛けた結果を格納して、掛けたものを足す
            result = result + calculationService.cartTotal(cart.getCount(), cart.getPrice());
        }
        // buyFormに格納して出力
        buyForm.setTotal("¥" + (String.format("%,d", result)));

        buyForm.setComment("ご購入金額");

        model.addAttribute("buyForm", buyForm);
        return "buy";
    }

    // 削除ボタン押下
    @RequestMapping(value = "/total", params = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam Integer delete, @ModelAttribute BuyForm buyForm, Model model) {

        // @RequestParamのdeleteにbuy.htmlのth:value="${checks.id}が入っている
        Integer id = delete;
        // 指定されたデータで削除Mapperを実行
        petBirdMapper.deleteOnly(id);
        // カート一覧を変数へ代入
        List<CastPetBirdEntity> cartList = petBirdMapper.seachCartAll();
        // カートの中身を表示
        buyForm.setChecks(castService.strList(cartList));

        // カートの中身を再計算
        // 掛けた結果を格納しておく場所を作る
        Integer result = 0;
        for (CastPetBirdEntity cart : cartList) {
            Integer cartCount = cart.getCount();
            Integer cartPrice = cart.getPrice();
            // t_petbirdとt_cartを結合し、t_cartのcountとt_petbirdのpridceを掛けた結果を格納して、掛けたものを足す
            result = result + calculationService.cartTotal(cartCount, cartPrice);
        }

        // buyFormに格納して出力
        buyForm.setTotal("¥" + (String.format("%,d", result)));

        // VeiwにFormをセットする
        model.addAttribute("buyForm", buyForm);
        // buy.htmlを表示する
        return "buy";
    }

    @PostMapping("/total")
    public String totalExecute(@ModelAttribute BuyForm buyForm, Model model) {

        ThanksForm thanksForm = new ThanksForm();

        List<CastPetBirdEntity> castPetBirdEntity = petBirdMapper.cartBirdList(buyForm.getId());

        // Listから一行取出す
        for (CastPetBirdEntity castPetBird : castPetBirdEntity) {
            Integer cartId = castPetBird.getId();
            Integer cartCount = castPetBird.getCount();

            // t_petbirdの対象の一行から個体数のみ抜出して代入
            PetBirdEntity petBirdId = petBirdMapper.PetBird(cartId);
            Integer petBirdResult = calculationService.countReplace(cartId, petBirdId.getCount(), cartCount);
            // t_petbirdもupdateBirdにて更新する(Countをt_petbird -
            // selectForm.IdCountBeanList().getCount() で出す)
            petBirdMapper.updatePetBird(cartId, petBirdResult);

        }

        // 購入するボタンを押したらカートの中が消える
        petBirdMapper.deleteAll();

        thanksForm.setComment("可愛がってあげてください");

        model.addAttribute("thanksForm", thanksForm);
        return "thank";

    }

    @PostMapping("/cart")
    public String cartExecute(@ModelAttribute SelectForm selectForm, Model model) {

        BuyForm buyForm = new BuyForm();

        // カートの中身を表示
        List<CastPetBirdEntity> cartList = petBirdMapper.seachCartAll();
        buyForm.setChecks(castService.strList(cartList));

        // カートの中身を再計算
        // 掛けた結果を格納しておく場所を作る
        Integer result = 0;
        for (CastPetBirdEntity cart : cartList) {
            Integer cartCount = cart.getCount();
            Integer cartPrice = cart.getPrice();
            // t_petbirdとt_cartを結合し、t_cartのcountとt_petbirdのpridceを掛けた結果を格納して、掛けたものを足す
            result = result + calculationService.cartTotal(cartCount, cartPrice);
        }

        // buyFormに格納して出力
        buyForm.setTotal("¥" + (String.format("%,d", result)));

        model.addAttribute("buyForm", buyForm);
        return "buy";

    }

}
