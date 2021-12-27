package usr.lrivera.pokemonestudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usr.lrivera.pokemonestudo.entities.Pokemon;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Optional<Pokemon> findByName(String name);
}
