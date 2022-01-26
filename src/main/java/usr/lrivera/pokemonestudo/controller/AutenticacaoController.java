package usr.lrivera.pokemonestudo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usr.lrivera.pokemonestudo.dto.TokenDto;
import usr.lrivera.pokemonestudo.forms.LoginForm;
import usr.lrivera.pokemonestudo.security.TokenService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    private static final Logger LOG = LoggerFactory.getLogger(AutenticacaoController.class);
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity<TokenDto> autenticar (@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
       try {
           Authentication authentication = authManager.authenticate(dadosLogin);
           String token = tokenService.gerarToken(authentication);
           LOG.info("Solicitação de autenticação realizada por {}",dadosLogin.getName());
           return ResponseEntity.ok(new TokenDto(token,"Bearer"));
       }
       catch (AuthenticationException ex){
           return ResponseEntity.badRequest().build();
       }

    }
}
