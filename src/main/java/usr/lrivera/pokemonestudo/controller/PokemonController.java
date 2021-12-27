package usr.lrivera.pokemonestudo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;
import usr.lrivera.pokemonestudo.controller.services.PokemonIPTrackingService;
import usr.lrivera.pokemonestudo.dto.PokemonDTO;
import usr.lrivera.pokemonestudo.dto.PokemonDetalhadoDTO;
import usr.lrivera.pokemonestudo.entities.Pokemon;
import usr.lrivera.pokemonestudo.forms.FormAtualizacao;
import usr.lrivera.pokemonestudo.forms.PokemonAddForm;
import usr.lrivera.pokemonestudo.repository.PokemonRepository;
//import usr.lrivera.pokemonestudo.repository.PokemonRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    @Autowired
    private PokemonIPTrackingService pokemonIPTrackingService;
    @Autowired
    private PokemonRepository pokemonRepository;

    @GetMapping
    public List<PokemonDTO> listaCompleta(@RequestHeader HttpHeaders headers) {
        String idOptional = headers.getFirst("id");
        if (idOptional!=null) {
            return PokemonDTO.converterPokemonFromHeader(pokemonRepository.findById(Long.valueOf(idOptional)).get());
        }
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return PokemonDTO.converterPokemon(pokemons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDetalhadoDTO> pokemonDTOResponseEntity(@PathVariable Long id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        return pokemon.map(value -> ResponseEntity.ok(new PokemonDetalhadoDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public RedirectView pokemonDetalhadoDTOResponseEntity(@RequestBody @Valid PokemonAddForm form, UriComponentsBuilder uriComponentsBuilder) {
        Pokemon pokemon = form.converter();
        pokemonRepository.save(pokemon);
        URI uri = uriComponentsBuilder.path("/pokemon/{id}").buildAndExpand(pokemon.getId()).toUri();
        return new RedirectView(uri.getPath());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonDetalhadoDTO> update(@PathVariable Long id, @RequestBody FormAtualizacao att) {
        if (pokemonRepository.findById(id).isPresent()) {
            Pokemon pokemon = att.atualizacao(pokemonRepository, id);
            return ResponseEntity.ok(new PokemonDetalhadoDTO(pokemon));
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        if (pokemonRepository.findById(id).isPresent()) {
            pokemonRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        System.out.println(pokemonIPTrackingService.
                getIpRequestAndUser(httpServletRequest));
        return ResponseEntity.notFound().build();
    }
}
