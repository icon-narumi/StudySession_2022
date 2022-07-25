package com.example.pokemon.mapper;

import java.util.List;

import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.PokemonBean;
import com.example.pokemon.entity.PokemonEntity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PokemonMasterMapper {

    // ポケモンリストを持ってくる
    @Select("select * from m_pokemon")
    List<PokemonEntity> selectPokemon();

    // 手持ち表示用のポケモンリスト
    @Select("select"
                + " row_number() over(order by id) num, "
                + " get_pokemon.id,"
                + " m_pokemon.name,"
                + " m_pokemon.type1,"
                + " m_pokemon.type2,"
                + " get_pokemon.strength,"
                + " get_pokemon.attack_type as attackType"
                + " from m_pokemon"
                + " join get_pokemon "
                + " on m_pokemon.p_id = get_pokemon.p_id "
                + " where get_pokemon.t_id =#{tId}")
    List<PartnerBean> selectPartner(Integer tId);

    // 選べるポケモン
    @Select("select"
                + " row_number() over(order by p_id) num "
                + " ,p_id"
                + " ,name"
                + " ,type1"
                + " ,type2"
                + " from m_pokemon")
    List<PokemonBean> selectPokemonWithNum();

    @Insert("insert into get_pokemon values(nextval('get_pokemon_seq'), #{tId}, #{pId}, #{strength}, #{attackType})")
    void addPartner(@Param("tId") Integer tId, @Param("pId") Integer pId, @Param("strength") Integer strength, @Param("attackType") String attackType);

    @Update("update get_pokemon set strength = #{strength} where id = #{id}")
    void updateStrength(@Param("strength") Integer strength, @Param("id") Integer id);

    @Delete("delete from get_pokemon where id = #{id}")
    void deletePartner(Integer id);
}
