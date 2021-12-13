package usr.lrivera.pokemonestudo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import usr.lrivera.pokemonestudo.entities.Usuario;
import usr.lrivera.pokemonestudo.repository.UsuarioRepository;

import java.util.Optional;

//Classe de serviço para login com Username e Password
//Utilizada
@Service
public class SecurityDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> byEmail = usuarioRepository.findByEmail(username);
        if(byEmail.isPresent()){
            return byEmail.get();
        }
        throw new UsernameNotFoundException("Dados inválidos");
    }
}
