package com.example.petbird.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    
    @PostMapping("/select")
    public String selectExecute(@ModelAttribute SearchForm searchForm, Model model) {//Form→与えるForm
        
        SelectForm selectForm = new SelectForm();
 
        if(searchForm.getId() != null){
            
            //最終的にカウントリストを持ったBeanをList化するためのインスタンスを用意
            List<PetBirdBean> petBirdBeanList = new ArrayList<>();
            //複数のidを配列としてMapperで抽出し、該当行数分だけ変数checkに格納してList型でセット
             
                //前画面で☑で選択されたIDでSQL検索し、抽出した一行をcheckとしてList化する
                List<CastPetBirdEntity> check = petBirdMapper.commitBirdList(searchForm.getId());
            
                //☑で選択してList化された値を一行ずつ分解し、checkの行数分繰り返す
                for(CastPetBirdEntity castEntity : check){

                    PetBirdBean petBirdBean = new PetBirdBean();
                    //☑されたIDをpetBirdBeanのIdへセット
                    //petBirdBean.setId(String.valueOf(castEntity.getId()));

                    //カウント部分のリストを作成
                        List<CountBean> countBeanList =  new ArrayList<>();
                        //☑された一行のカウント部分をmaxCountへ代入
                        Integer maxCount = castEntity.getCount();
                        //for(int i = 1 ; i < maxCount ; i++){ 1から増えてく
                            for(int i =  maxCount ; i >=1 ; i--){ //最大値から減っていく
                                
                                CountBean countBean = new CountBean();

                                //countBeanの各変数へ最大値までカウントした値をセットする
                                countBean.setCount(i);
                                countBean.setCountName(String.valueOf(i));
                                //セットしたcountBeanをList化する
                                countBeanList.add(countBean);
                            }
                        //☑で選択して変換した一行をpetBirdBeanへ代入
                        petBirdBean = castService.strCast(castEntity);
                        //for文で作成済みのcountBeanListをpetBirdBeanの変数CountBeanListへセット
                        petBirdBean.setCountBeanList(countBeanList);
                        //petBirdBeanに必要な項目が揃ったためpetBirdBeanListにセット
                    petBirdBeanList.add(petBirdBean);
                    
                }
                //カウントリストを持ったpetBirdBeanListを、出力用のselectForm.setBeanListへセット
                selectForm.setBeanList(petBirdBeanList);

        }else{
            
            searchForm.setComment("鳥が選択されていません。HOMEからやり直してください");
            return"coution";
        }
        
        model.addAttribute("selectForm",selectForm);//→受取るForm
        return "select";
    }


    @PostMapping("/buy")//合計を出してカートへ入れる
    public String buyExecute(@ModelAttribute SelectForm selectForm, Model model) {
        
        BuyForm buyForm = new BuyForm();
        
        //selectForm.getIdCountBeanList()にはHTMLより受取った行数分格納されている
        List<IdCountBean> idCountBeanList =selectForm.getIdCountBeanList();          
        //For文で一行一行確認
            //List化されたものを一行にバラして、
            for(IdCountBean idcount : idCountBeanList){
                idcount.getId();
                idcount.getCount();

                //t_cartに既にIdが登録されているかを確認(select 文で探す)                
                IdCountBean idCountBean = petBirdMapper.seachCartBird(idcount.getId());

                //t_petbirdにある対象の一列を持ってくる
                CastPetBirdEntity castPetBirdEntity =petBirdMapper.commitBird(idcount.getId());

                if(idCountBean !=null){//有る→t_cartに値がある場合→updateCartBirdで更新する
                    
                    Integer cartResult = calculationService.cartCountReplace(idcount.getId(),idCountBean.getCount(),idcount.getCount());
                    petBirdMapper.updateCartBird(idcount.getId(),cartResult);

                     
                    //t_petbirdもupdateBirdにて更新する(Countをt_petbird - selectForm.IdCountBeanList().getCount() で出す)
                    Integer petBirdResult = calculationService.countReplace(idcount.getId(),castPetBirdEntity.getCount(),idcount.getCount());
                    petBirdMapper.updatePetBird(idcount.getId(),petBirdResult);
                    
                }else{//無い→selectForm.IdCountBeanList().getId()とselectForm.IdCountBeanList().getCount()をinputCartBirdで登録する
                    
                    petBirdMapper.inputCartBird(idcount.getId(),idcount.getCount());
                    
                    Integer petBirdResult = calculationService.countReplace(idcount.getId(),castPetBirdEntity.getCount(),idcount.getCount());
                    petBirdMapper.updatePetBird(idcount.getId(),petBirdResult);
                    
                }
                //t_cartと結合した一覧を出力し、格納
                selectForm.setCheck(petBirdMapper.cartBirdList(idcount.getId()));
                
             }

        //掛けた結果を格納しておく場所を作る
        Integer result = 0;
        //for文でt_cartの行数文繰り返す
        List<CastPetBirdEntity> cartList = selectForm.getCheck();

        buyForm.setChecks(castService.strList(cartList));

        for(CastPetBirdEntity cart : cartList){
            cart.getId();
            cart.getCount();
            cart.getPrice();
            //t_petbirdとt_cartを結合し、t_cartのcountとt_petbirdのpridceを掛けた結果を格納して、掛けたものを足す
            result = result+calculationService.cartTotal(cart.getCount(),cart.getPrice());
        }
        //buyFormに格納して出力
            buyForm.setTotal("¥"+(String.format("%,d",result)));

        buyForm.setComment("ご購入金額");

        model.addAttribute("buyForm",buyForm);
        return "buy";
    }

    //削除ボタン押下
    @RequestMapping(value = "/total", params = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam Integer delete, @ModelAttribute BuyForm buyForm, Model model) {
        
        //@RequestParamのdeleteにbuy.htmlのth:value="${checks.id}が入っている
        Integer id = delete;
        //指定されたデータで削除Mapperを実行
        petBirdMapper.deleteOnly(id);
        //カート一覧を変数へ代入
        List<CastPetBirdEntity> cartList = petBirdMapper.seachCartAll();
        //カートの中身を表示
        buyForm.setChecks(castService.strList(cartList));

        //カートの中身を再計算
        //掛けた結果を格納しておく場所を作る
        Integer result = 0;
        Integer cartCount = 0;

        for(CastPetBirdEntity cart : cartList){
            cartCount = cart.getCount();
            Integer cartPrice = cart.getPrice();

            //t_petbirdとt_cartを結合し、t_cartのcountとt_petbirdのpridceを掛けた結果を格納して、掛けたものを足す
            result = result+calculationService.cartTotal(cartCount,cartPrice);
        }

        //t_petbirdの対象の一行から個体数のみ抜出して代入
        PetBirdEntity petBirdId = petBirdMapper.PetBird(id);
        Integer petCount = petBirdId.getCount();

        //t_petbirdへ削除ボタンを押した鳥を戻す
           petBirdMapper.updatePetBird(id,calculationService.backShop(id,cartCount,petCount));
        
        //buyFormに格納して出力
            buyForm.setTotal("¥"+(String.format("%,d",result)));
       
        // VeiwにFormをセットする
        model.addAttribute("buyForm",buyForm);
        // buy.htmlを表示する
        return "buy";
    }


    @PostMapping("/total")
    public String totalExecute(@ModelAttribute BuyForm buyForm, Model model) {
        
        ThanksForm thanksForm = new ThanksForm();

        //個数を計算・変換
        Integer cal = calculationService.countReplace(buyForm.getId(),buyForm.getDbCount(),buyForm.getCount());
        petBirdMapper.updatePetBird(buyForm.getId(),cal);
        

        //購入するボタンを押したらカートの中が消える
        petBirdMapper.deleteAll();
        
        thanksForm.setComment("可愛がってあげてください");

        model.addAttribute("thanksForm",thanksForm);
        return "thank";

    }

    @PostMapping("/cart")
    public String cartExecute(@ModelAttribute SelectForm selectForm, Model model) {
        
        BuyForm buyForm = new BuyForm();
        
        //カートの中身を表示
        List<CastPetBirdEntity> cartList = petBirdMapper.seachCartAll();
        buyForm.setChecks(castService.strList(cartList));

        model.addAttribute("buyForm",buyForm);
        return "buy";

    }

    



}
