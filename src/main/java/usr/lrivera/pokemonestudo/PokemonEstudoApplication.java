package usr.lrivera.pokemonestudo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PokemonEstudoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonEstudoApplication.class, args);
	}

}
