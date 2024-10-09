package br.com.condelivery.gateway.gateway.filter;

import br.com.condelivery.gateway.gateway.routes.ValidatorRouter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private ValidatorRouter router;

    @Value("${api.security.token.secret}")
    private String secret;

    public AuthenticationFilter() {
        super(AuthenticationFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (exchange.getRequest().getURI().getPath().startsWith("/auth/")) {
                return chain.filter(exchange); // Permite acesso sem validação
            }

            if (router.isSecured.test(exchange.getRequest())) {
                // Verifica se o header Authorization está presente
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization header not present"));
                }

                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                try {
                    DecodedJWT decodedJWT = validateToken(authHeader);
                    String username = decodedJWT.getClaim("name").asString();
                    boolean isDeliveryMan = decodedJWT.getClaim("profile").asBoolean();

                    ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                            .header("name", username)
                            //.header("isDeliveryMan", Boolean.toString(isDeliveryMan))
                            //.header("role", isDeliveryMan ? "ROLE_DELIVERYMAN" : "ROLE_RESIDENT")
                            .build();

                    return chain.filter(exchange.mutate().request(modifiedRequest).build());
                } catch (Exception e) {
                    return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Acesso negado", e));
                }
            }

            return chain.filter(exchange);
        };
    }

    private DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("API Condelivery").build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido ou expirado");
        }
    }

    public static class Config {

    }
}
