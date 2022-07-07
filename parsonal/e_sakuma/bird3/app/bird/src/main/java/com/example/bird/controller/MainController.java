package com.example.bird.controller;

import com.example.bird.entity.TbirdEntity;
import com.example.bird.form.BirdForm;
import com.example.bird.form.DeleteForm;
import com.example.bird.form.InputForm;
import com.example.bird.form.UpdateForm;
import com.example.bird.mapper.BirdMapper;
import com.example.bird.service.CastService;
import com.example.bird.service.DeliveryService;

import java.sql.Timestamp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
public class MainController {
    
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private CastService castService;
    @Autowired
    private BirdMapper birdMapper;
    

    
    //最初の画面
    @RequestMapping(value = "/", method = RequestMethod.GET)
        public String init(Model model) {

            BirdForm birdForm = new BirdForm();
            birdForm.setList(null);

        model.addAttribute("birdForm",birdForm);
        return "bird";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String init2(Model model) {

        BirdForm birdForm = new BirdForm();
        birdForm.setList(null);

    model.addAttribute("birdForm",birdForm);
    return "bird";
}

    //検索画面
    @PostMapping("/search") 
        public String serchExecute(@ModelAttribute BirdForm birdForm, Model model) {
           
                //DBから直接SQLで抽出して変換して格納
                birdForm.setList(castService.StrList(deliveryService.getSearchList(birdForm.getSpecies())));
            
            
                 
        model.addAttribute("birdForm",birdForm);
        return "bird";
    }

    //登録画面
    @PostMapping("/input") 
        public String inputExecute(@ModelAttribute InputForm inputForm, Model model) {

            inputForm.setComment("新しい鳥を登録");       
            inputForm.setOrdoList(birdMapper.ordoAll());
            inputForm.setFamilyList(birdMapper.familyAll());
            inputForm.setFoodList(birdMapper.foodAll());
                 
        model.addAttribute("inputForm",inputForm);
        return "input";
    }

    //登録結果
    @PostMapping("/comment") 
        public String commentExecute(@Valid @ModelAttribute InputForm inputForm, BindingResult error,Model model) {


        try{

            if(inputForm.getVolume() != null){
            //inputFormから受取った値をMapperへいれる
                   
                if(error.hasErrors()){
                    
                inputForm.setOrdoList(birdMapper.ordoAll());
                inputForm.setFamilyList(birdMapper.familyAll());
                inputForm.setFoodList(birdMapper.foodAll());
                    return "input";
                }else{

                    //Timestamp nowTime = new Timestamp(System.currentTimeMillis());
                    birdMapper.inputBird(inputForm.getSpecies(),inputForm.getFamily(),inputForm.getOrdo(),Integer.parseInt(inputForm.getVolume()),inputForm.getFood());
                    inputForm.setComment("登録しました");
                }
            }else{

                return "input";
            }

        }catch(Exception e){
			    System.out.println(e);
				System.out.println("!!一意制約違反発生");
                inputForm.setComment("既に登録されている鳥です");

                return "ng";
               
        }
        
                
            model.addAttribute("inputForm",inputForm);
           
            return "comment";
        }


    //削除画面
    @PostMapping("/delete") 
        public String deleteExecute(@ModelAttribute DeleteForm deleteForm, Model model) {

            deleteForm.setList(castService.StrList(deliveryService.getAllList()));

            deleteForm.setComment("削除対象を選択してください");
           

        model.addAttribute("deleteForm",deleteForm);
        return "delete";
    }

    //削除結果
    @PostMapping("/result") 
        public String resultExecute(@Valid @ModelAttribute DeleteForm deleteForm,BindingResult error, Model model) {
 
            if(error.hasErrors()){
                
                deleteForm.setList(castService.StrList(deliveryService.getAllList()));

                return "delete";
            }else{
                
                deleteForm.setComment("削除しました");
                birdMapper.deleteBird(deleteForm.getSpecies());
                deleteForm.setList(castService.StrList(deliveryService.getAllList()));

            }

        model.addAttribute("deleteForm",deleteForm);
        return "result";
    }

    //更新前検索画面
    @PostMapping("/updateSearch")
        public String updateSearchExecute(@ModelAttribute UpdateForm updateForm, Model model) {
            //現在の鳥一覧
            updateForm.setList(castService.StrList(deliveryService.getAllList())); 
            
            model.addAttribute("updateForm",updateForm);    
        return "updateSearch";
    }

    //更新画面
    @PostMapping("/update") 
        public String updateExecute(@ModelAttribute UpdateForm updateForm, Model model) {

                //指定された鳥のEntityを出力
                updateForm.setComment("鳥情報の更新");
                updateForm.setList(castService.StrList(deliveryService.getSearchList(updateForm.getSpecies())));
                //選択できる項目
                updateForm.setOrdoList(birdMapper.ordoAll());
                updateForm.setFamilyList(birdMapper.familyAll());
                updateForm.setFoodList(birdMapper.foodAll());
                
                //Mapperへ種名を入れて抽出した一行をEntity型の変数へ代入(getSpecies()で更新対象を絞っている)
                TbirdEntity tbirdEntity = birdMapper.updateSearchOrdo(updateForm.getSpecies());
                //Entityの一行からOrdoの値だけを抽出し、FormのOrdoへセット
                updateForm.setOrdo(tbirdEntity.getOrdo());
                updateForm.setFamily(tbirdEntity.getFamily());
                updateForm.setVolume(tbirdEntity.getVolume());
                updateForm.setFood(tbirdEntity.getFood());

                
        model.addAttribute("updateForm",updateForm);
        return "update";
    }


    //更新結果
    @PostMapping("/correct") 
        public String correctExecute(@Valid @ModelAttribute UpdateForm updateForm,BindingResult error,Model model) {

        if(updateForm.getVolume() != ""){
              if(error.hasErrors()){    
                    
                    updateForm.setComment("更新してません");
                    return "update";

                }else{
                    
                    birdMapper.updateBird(updateForm.getSpecies(),updateForm.getOrdo(),updateForm.getFamily(),(Integer.parseInt(updateForm.getVolume())),updateForm.getFood());
                    
                    updateForm.setComment("更新しました");
                    updateForm.setList(castService.StrList(deliveryService.getAllList()));
                    
                }
        }else{
                updateForm.setComment("更新できませんでした");

                updateForm.setList(castService.StrList(deliveryService.getAllList()));
                
                //マスターテーブルをFormに格納し出力
                updateForm.setOrdoList(birdMapper.ordoAll());
                updateForm.setFamilyList(birdMapper.familyAll());
                updateForm.setFoodList(birdMapper.foodAll());

                return "update";
        }

        model.addAttribute("updateForm",updateForm);
        return "correct";
        }


}
