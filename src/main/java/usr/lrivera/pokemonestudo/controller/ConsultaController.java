package usr.lrivera.pokemonestudo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import usr.lrivera.pokemonestudo.dto.PokemonDTO;
import usr.lrivera.pokemonestudo.dto.PokemonDetalhadoDTO;
import usr.lrivera.pokemonestudo.entities.Pokemon;
import usr.lrivera.pokemonestudo.forms.PokemonAddForm;
import usr.lrivera.pokemonestudo.repository.PokemonRepository;
//import usr.lrivera.pokemonestudo.repository.PokemonRepository;

import javax.validation.Valid;
import java.net.URI;
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
    @PostMapping("/add")
    public ResponseEntity<PokemonDetalhadoDTO> pokemonDetalhadoDTOResponseEntity(@RequestBody @Valid PokemonAddForm form, UriComponentsBuilder uriComponentsBuilder){
        Pokemon pokemon = form.converter();
        pokemonRepository.save(pokemon);
        URI uri = uriComponentsBuilder.path("/pokemon/{id}").buildAndExpand(pokemon.getId()).toUri();
        return ResponseEntity.created(uri).body(new PokemonDetalhadoDTO(pokemon));
    }
}
