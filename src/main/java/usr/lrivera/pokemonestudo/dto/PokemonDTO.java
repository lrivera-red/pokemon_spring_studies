package usr.lrivera.pokemonestudo.dto;

import usr.lrivera.pokemonestudo.entities.Pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class PokemonDTO {
    private Long id;
    private String name;
    private Integer geracao;

    public PokemonDTO(Pokemon pokemon) {
        this.id = pokemon.getId();
        this.name = pokemon.getName();
        this.geracao = pokemon.getGeneration();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getGeracao() {
        return geracao;
    }

    public static List<PokemonDTO> converterPokemon(List<Pokemon> pokemons){
        return pokemons.stream().map(PokemonDTO::new).collect(Collectors.toList());
    }
}