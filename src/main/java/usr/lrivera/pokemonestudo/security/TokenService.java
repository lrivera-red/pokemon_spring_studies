package usr.lrivera.pokemonestudo.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import usr.lrivera.pokemonestudo.entities.Usuario;

import java.util.Date;

@Service
public class TokenService {
    @Value("6400000")
    private String expiration;
    @Value("rm'!@N=Ke!~p8VTA2ZRK~nMDQX5Uvm!m'D&]{@Vr?G;2?XhbC:Qa#9#eMLN\\}x3?JR3.2zr~v)gYF^8\\:8>:XfB:Ww75N/emt9Yj[bQMNCWwW\\J?N,nvH.<2\\.r~w]*e~vgak)X\"v8H`MH/7\"2E`,^k@n<vE-wD3g9JWPy;CrY*.Kd2_D])=><D?YhBaSua5hW%{2]_FVXzb9`8FH^b[X3jzVER&:jw2<=c38=>L/zBq`}C6tT*cCSVC^c]-L}&/")
    private String secret;
    public String gerarToken(Authentication authentication){
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje =new Date();
        Date exp = new Date(hoje.getTime()+Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("API")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }
    public boolean validacaoToken(String token){
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
        return true;}
        catch (Exception ex){
            return false;}
    }
    public Long recuperarId(String token){
        Claims claims = Jwts.parser().setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());

    }
}

