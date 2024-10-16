package br.com.condelivery.gateway.gateway.routes;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

@Component
public class ValidatorRouter {

    private static final List<String> OPEN_API_ENDPOINTS = List.of(
            "/auth/login",
            "/auth/register",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured = request -> {
        boolean secured = OPEN_API_ENDPOINTS
                .stream()
                .noneMatch(uri -> request.getURI().getPath().startsWith(uri));

        System.out.println("Rota: " + request.getURI().getPath() + " Protegida: " + secured);
        return secured;
    };

}

    /*private static final List<String> OPEN_API_ENDPOINTS = List.of("/auth/login", "/auth/register", "/Eureka");

    public Predicate<ServerHttpRequest> isSecured = request -> {
        boolean isOpenApiEndpoint = OPEN_API_ENDPOINTS.stream()
                .anyMatch(uri -> request.getURI().getPath().contains(uri));

        // Se for um endpoint aberto, não é protegido
        boolean secured = !isOpenApiEndpoint;

        System.out.println("Rota: " + request.getURI().getPath() + " Protegida: " + secured);
        return secured;
    };


}

     */
