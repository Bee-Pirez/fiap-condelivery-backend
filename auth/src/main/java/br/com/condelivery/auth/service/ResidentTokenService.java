package br.com.condelivery.auth.service;

import br.com.condelivery.auth.model.Resident;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class ResidentTokenService {

    private static final String ISSUER = "API Condelivery";

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Resident resident){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(resident.getEmail())
                    .withClaim("name", resident.getName())
                    .withClaim("id", resident.getId())
                    //.withClaim("profile", resident.isDeliveryMan())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("JWT generation failed", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado: " +tokenJWT);
        }
    }

    // Método para validar o token
    /*public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("API Condelivery").build();
            verifier.verify(token);
            return true; // Token é válido
        } catch (JWTVerificationException exception) {
            return false; // Token é inválido ou expirou
        }
    } */

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
