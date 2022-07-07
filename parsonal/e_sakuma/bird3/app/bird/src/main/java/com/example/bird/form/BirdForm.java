package com.example.bird.form;



import java.util.List;
import com.example.bird.entity.StrBirdEntity;

public class BirdForm {

    //<htmlより値を受取り保持>
    String species;

    //Controllerより値を受取り出力
    private List<StrBirdEntity> list;
    //List<BirdEntity> list;

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }    
    public List<StrBirdEntity> getList() {
        return list;
    }
    public void setList(List<StrBirdEntity> list) {
        this.list = list;
    }    
     
    
}
