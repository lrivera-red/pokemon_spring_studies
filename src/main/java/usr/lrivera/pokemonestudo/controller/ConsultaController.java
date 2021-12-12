package usr.lrivera.pokemonestudo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usr.lrivera.pokemonestudo.dto.PokemonDTO;
import usr.lrivera.pokemonestudo.dto.PokemonDetalhadoDTO;
import usr.lrivera.pokemonestudo.entities.Pokemon;
import usr.lrivera.pokemonestudo.repository.PokemonRepository;
//import usr.lrivera.pokemonestudo.repository.PokemonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class ConsultaController {

    @Autowired
    private PokemonRepository pokemonRepository;
    @GetMapping
    public List<PokemonDTO> listaCompleta(){
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return PokemonDTO.converterPokemon(pokemons) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PokemonDetalhadoDTO> pokemonDTOResponseEntity(@PathVariable Long id){
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        return pokemon.map(value -> ResponseEntity.ok(new PokemonDetalhadoDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
