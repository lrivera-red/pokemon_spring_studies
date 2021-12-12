package usr.lrivera.pokemonestudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usr.lrivera.pokemonestudo.entities.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
