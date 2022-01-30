package usr.lrivera.pokemonestudo.controller;


import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import usr.lrivera.pokemonestudo.controller.services.HeaderEnrichService;
import usr.lrivera.pokemonestudo.controller.services.PokemonIPTrackingService;
import usr.lrivera.pokemonestudo.controller.services.TraceService;
import usr.lrivera.pokemonestudo.controller.services.WebClientService;
import usr.lrivera.pokemonestudo.dto.PokemonDTO;
import usr.lrivera.pokemonestudo.dto.PokemonDetalhadoDTO;
import usr.lrivera.pokemonestudo.entities.Pokemon;
import usr.lrivera.pokemonestudo.forms.FormAtualizacao;
import usr.lrivera.pokemonestudo.forms.PokemonAddForm;
import usr.lrivera.pokemonestudo.repository.PokemonRepository;
//import usr.lrivera.pokemonestudo.repository.PokemonRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    @Autowired
    WebClientService webClientService;
    @Autowired
    private TraceService traceService;
    @Autowired
    private HeaderEnrichService headerEnrichService;
    @Autowired
    private PokemonIPTrackingService pokemonIPTrackingService;
    @Autowired
    private PokemonRepository pokemonRepository;
    private static final Logger log = LoggerFactory.getLogger("STDOUT");
    private static final Logger jsonlog = LoggerFactory.getLogger("jsonLogger");
    @GetMapping
    public List<PokemonDTO> listaCompleta(@RequestHeader HttpHeaders headers, HttpServletRequest httpServletRequest) {
        WebClient webClient = webClientService.webClientBuilder(traceService);
       String response = webClient.
               get().
               retrieve().
               bodyToMono(String.class).
               block();
       log.info(response);
       log.info("Request realizada pelo ip = {}",pokemonIPTrackingService.getIpRequestAndUser(httpServletRequest));
       headers.getOrigin();
        String idOptional = headers.getFirst("id");
        if (idOptional!=null) {
            return PokemonDTO.converterPokemonFromHeader(pokemonRepository.findById(Long.valueOf(idOptional)).get());
        }
        log.info("Request geral");
        jsonlog.info("Json request geral");
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
