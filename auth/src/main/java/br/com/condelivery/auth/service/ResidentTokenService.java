package br.com.condelivery.auth.service;

import br.com.condelivery.auth.model.Resident;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import org.slf4j.Logger;

@Service
public class ResidentTokenService {

    private static final String ISSUER = "API Condelivery";
    private static final Logger logger = LoggerFactory.getLogger(ResidentTokenService.class);

    @Value("${api.security.token.secret}")
    private String secret;

    private static final long TOKEN_EXPIRATION_TIME = 2 * 60 * 60 * 1000; // 2 horas em milissegundos

    public String generateToken(Resident resident) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            long currentTimeMillis = System.currentTimeMillis();
            Date expirationDate = new Date(currentTimeMillis + TOKEN_EXPIRATION_TIME);
            String token = JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(resident.getEmail())
                    .withClaim("name", resident.getName())
                    .withClaim("residentId", resident.getId().toString())
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);

            logger.info("Token gerado: {}, expira em: {}", token, expirationDate);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("JWT generation failed", exception);
        }
    }

    public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado: " + token);
        }
    }

    public Instant dataExpiracao() {
        return LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
