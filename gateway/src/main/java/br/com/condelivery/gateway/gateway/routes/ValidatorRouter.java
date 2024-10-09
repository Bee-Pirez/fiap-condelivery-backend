package br.com.condelivery.gateway.gateway.routes;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

@Component
public class ValidatorRouter {

    private static final List<String> OPEN_API_ENDPOINTS = List.of(
            "/auth/auth/login",
            "/auth/auth/search",
            "/auth/eureka",
            "/auth/products/partners"
    );

    public Predicate<ServerHttpRequest> isSecured = request ->
            OPEN_API_ENDPOINTS
                .stream()
                .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
