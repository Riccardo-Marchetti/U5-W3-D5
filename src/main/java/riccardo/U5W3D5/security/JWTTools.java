package riccardo.U5W3D5.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import riccardo.U5W3D5.entities.Users;
import riccardo.U5W3D5.exceptions.UnauthorizedException;

import java.security.Key;
import java.util.Date;

@Component
public class JWTTools {
    @Value("${jwt_secret}")
    private String secret;

    // GENERO IL TOKEN
    public String createToken (Users users){
        return Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 + 60 + 60 * 24 * 7))
                .subject(String.valueOf(users.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    // VERIFICO IL TOKEN
    public void verifyToken (String token){
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi con il token effettua di nuovo il login");
        }
    }
}
