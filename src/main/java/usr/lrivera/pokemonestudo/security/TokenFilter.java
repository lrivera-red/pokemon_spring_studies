package usr.lrivera.pokemonestudo.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import usr.lrivera.pokemonestudo.entities.Usuario;
import usr.lrivera.pokemonestudo.repository.UsuarioRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TokenFilter extends OncePerRequestFilter {
    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;


    public TokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Usar a própria request pra recuperar o token
        String token = recuperarToken(request);
        boolean valido = tokenService.validacaoToken(token);
        if(valido){
            autenticar(token);
        }

        filterChain.doFilter(request,response);


    }
    public String recuperarToken(HttpServletRequest request) {
        //Condições para realizar verificação
        String token = request.getHeader("Authorization");//Header que carrega o token
        if (token == null ) {
            return null;
        }
        return token.substring(7, token.length());//Depois do "Bearer "
    }
    private void autenticar(String token){
        Usuario usuario = usuarioRepository.findById(tokenService.recuperarId(token)).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                usuario,null,usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


    }
}
