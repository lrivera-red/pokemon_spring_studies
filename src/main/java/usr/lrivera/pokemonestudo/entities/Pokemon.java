package usr.lrivera.pokemonestudo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name_pk;
    private String type_1 ;
    private String type_2 ;
    private Double stats_total;
    private Integer stats_hp;
    private Integer stats_attack;
    private Integer stats_defense;
    private Integer stats_sp_attack;
    private Integer stats_sp_def;
    private Integer stats_speed;
    private Integer generation;
    private Boolean legendary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_pk() {
        return name_pk;
    }

    public void setName_pk(String name_pk) {
        this.name_pk = name_pk;
    }

    public String getType_1() {
        return type_1;
    }

    public void setType_1(String type_1) {
        this.type_1 = type_1;
    }

    public String getType_2() {
        return type_2;
    }

    public void setType_2(String type_2) {
        this.type_2 = type_2;
    }

    public Double getStats_total() {
        return stats_total;
    }

    public void setStats_total(Double stats_total) {
        this.stats_total = stats_total;
    }

    public Integer getStats_hp() {
        return stats_hp;
    }

    public void setStats_hp(Integer stats_hp) {
        this.stats_hp = stats_hp;
    }

    public Integer getStats_attack() {
        return stats_attack;
    }

    public void setStats_attack(Integer stats_attack) {
        this.stats_attack = stats_attack;
    }

    public Integer getStats_defense() {
        return stats_defense;
    }

    public void setStats_defense(Integer stats_defense) {
        this.stats_defense = stats_defense;
    }

    public Integer getStats_sp_attack() {
        return stats_sp_attack;
    }

    public void setStats_sp_attack(Integer stats_sp_attack) {
        this.stats_sp_attack = stats_sp_attack;
    }

    public Integer getStats_sp_def() {
        return stats_sp_def;
    }

    public void setStats_sp_def(Integer stats_sp_def) {
        this.stats_sp_def = stats_sp_def;
    }

    public Integer getStats_speed() {
        return stats_speed;
    }

    public void setStats_speed(Integer stats_speed) {
        this.stats_speed = stats_speed;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public Boolean getLegendary() {
        return legendary;
    }

    public void setLegendary(Boolean legendary) {
        this.legendary = legendary;
    }
}
