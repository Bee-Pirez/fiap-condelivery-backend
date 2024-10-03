package br.com.condelivery.auth.service;

import br.com.condelivery.auth.model.Resident;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class ResidentTokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String genarateToken(Resident usuario){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Condelivery")
                    .withSubject(usuario.getEmail())
                    .withClaim("name", usuario.getName())
                    .withClaim("id", usuario.getId())
                    .withClaim("profile", usuario.isDeliveryMan())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("JWT generation failed", exception);
        }
    }
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
