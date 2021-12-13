package usr.lrivera.pokemonestudo.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfil implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    String tipoPerfil;

    @Override
    public String getAuthority() {
        return this.tipoPerfil;
    }
}
