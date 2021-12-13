package usr.lrivera.pokemonestudo.forms;

import usr.lrivera.pokemonestudo.entities.Pokemon;
import usr.lrivera.pokemonestudo.repository.PokemonRepository;

public class FormAtualizacao {
    private Double stats_total;
    private Integer stats_hp;
    private Integer stats_attack;
    private Integer stats_defense;
    private Integer stats_sp_attack;
    private Integer stats_sp_def;
    private Integer stats_speed;


    public Pokemon atualizacao(PokemonRepository pokemonRepository, Long id){
        Pokemon pokemon = pokemonRepository.getById(id);
        pokemon.setStats_total(this.stats_total);
        pokemon.setStats_hp(this.stats_hp);
        pokemon.setStats_attack(this.stats_attack);
        pokemon.setStats_defense(this.stats_defense);
        pokemon.setStats_sp_attack(this.stats_sp_attack);
        pokemon.setStats_sp_def(this.stats_sp_def);
        pokemon.setStats_speed(this.stats_speed);
        return pokemon;
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
}
