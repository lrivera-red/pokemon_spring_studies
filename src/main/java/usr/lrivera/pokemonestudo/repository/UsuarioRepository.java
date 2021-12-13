package usr.lrivera.pokemonestudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usr.lrivera.pokemonestudo.entities.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

     Optional<Usuario> findByEmail(String email);

}
