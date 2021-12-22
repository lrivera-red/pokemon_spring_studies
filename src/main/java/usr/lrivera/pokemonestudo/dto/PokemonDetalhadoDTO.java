package usr.lrivera.pokemonestudo.dto;

import usr.lrivera.pokemonestudo.entities.Pokemon;

public class PokemonDetalhadoDTO {
    private String nome;
    private Long id;
    private String tipo1;
    private String tipo2;
    private Double stats_total;
    private Integer stats_hp;
    private Integer stats_attack;
    private Integer stats_defense;
    private Integer stats_sp_attack;
    private Integer stats_sp_def;
    private Integer stats_speed;
    private Integer geracao;
    private Boolean lendario;

    public PokemonDetalhadoDTO(Pokemon pokemon) {
        this.nome = pokemon.getName();
        this.id = pokemon.getId();
        this.tipo1 = pokemon.getType_1();
        this.tipo2 = pokemon.getType_2();
        this.stats_total = pokemon.getStats_total();
        this.stats_hp = pokemon.getStats_hp();
        this.stats_attack = pokemon.getStats_attack();
        this.stats_defense = pokemon.getStats_defense();
        this.stats_sp_attack = pokemon.getStats_sp_attack();
        this.stats_sp_def = pokemon.getStats_sp_def();
        this.stats_speed = pokemon.getStats_speed();
        this.geracao = pokemon.getGeneration();
        this.lendario = pokemon.getLegendary();
    }

    public Double getStats_total() {
        return stats_total;
    }

    public void setStats_total(Double stats_total) {
        this.stats_total = stats_total;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
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

    public Integer getGeracao() {
        return geracao;
    }

    public void setGeracao(Integer geracao) {
        this.geracao = geracao;
    }

    public Boolean getLendario() {
        return lendario;
    }

    public void setLendario(Boolean lendario) {
        this.lendario = lendario;
    }
}
