package br.com.condelivery.gateway.gateway.filter;

import br.com.condelivery.gateway.gateway.routes.ValidatorRouter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.http.HttpHeaders;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

import org.springframework.stereotype.Component;

import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;


import org.slf4j.Logger;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private ValidatorRouter router;

    @Value("${api.security.token.secret}")
    private String secret;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (router.isSecured.test(request)) {
                if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return onError(exchange, "Missing authorization header", HttpStatus.UNAUTHORIZED);
                }

                String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7); // Remove "Bearer "
                } else {
                    return onError(exchange, "Invalid authorization header format", HttpStatus.UNAUTHORIZED);
                }

                try {
                    // Verifica o token JWT
                    DecodedJWT decodedJWT = verifyToken(authHeader);
                    String username = decodedJWT.getClaim("name").asString();

                    return chain.filter(exchange);
                } catch (Exception e) {
                    return onError(exchange, "Invalid or expired token", HttpStatus.UNAUTHORIZED);
                }
            }

            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

    public DecodedJWT verifyToken(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Condelivery")
                    .build()
                    .verify(tokenJWT);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid JWT token", exception);
        }
    }

    public static class Config {

    }
}

