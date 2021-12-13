package usr.lrivera.pokemonestudo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import usr.lrivera.pokemonestudo.repository.UsuarioRepository;

//principal configuração de securaça SpringSecurity
@EnableWebSecurity//Habilitar
@Configuration//Classe de configuração - Precisa herdar WebSecurityConfigurerAdapter
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired//Injetar UsuarioRepository
    private UsuarioRepository usuarioRepository;
    @Autowired//Injetar tokenService
    private TokenService tokenService;
    @Autowired//Injetar securityDetailsService
    private SecurityDetailsService securityDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Autorizacao
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.GET,"/pokemon").permitAll()//Permitir verbo Get nesse url
                .antMatchers(HttpMethod.GET,"/pokemon/*").permitAll()//Idem
                .antMatchers(HttpMethod.POST,"/auth").permitAll()//Permitir verbo Post nesse url
                .anyRequest().authenticated()//Qualquer outra request-> autenticado
                .and().csrf().disable()//Desabilitar A Config Padrão de segurança do SpringBoot-> Uso de classe customizada
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//Sessão Stateless por Token JWT
                .and().addFilterBefore(new TokenFilter(tokenService,usuarioRepository), UsernamePasswordAuthenticationFilter.class);
        //Instanciando o tokenFilter e já passando os objetos que ele vai precisar -> usuarioRepository(consulta de ID)
        //,tokenService ->tokenService(autenticação e criação)
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //Autenticacao
    auth.userDetailsService(securityDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    //recursosEstáticos(js,css,imagens,etc.)
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}


