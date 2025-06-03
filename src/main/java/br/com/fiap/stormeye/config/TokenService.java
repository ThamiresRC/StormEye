package br.com.fiap.stormeye.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.fiap.stormeye.model.Login;

@Service
public class TokenService {

    private static final String SECRET_KEY = "stormeye-secret";

    public String generateToken(Login login) {
        return JWT.create()
            .withSubject(login.getUsuario())
            .withClaim("tipoUsuario", login.getTipoUsuario())
            .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
            .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public TokenData validateToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
            .build()
            .verify(token);

        String usuario = decodedJWT.getSubject();
        String tipoUsuario = decodedJWT.getClaim("tipoUsuario").asString();

        return new TokenData(usuario, tipoUsuario);
    }
}
