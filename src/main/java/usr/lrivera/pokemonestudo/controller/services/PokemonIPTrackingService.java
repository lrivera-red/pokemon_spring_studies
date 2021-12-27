package usr.lrivera.pokemonestudo.controller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
public class PokemonIPTrackingService {

    public  String getIpRequestAndUser (HttpServletRequest httpServletRequest){
        return "IP REMOTO: " + httpServletRequest.getRemoteAddr() + "USER" +httpServletRequest.getRemoteUser();
    }
}
