package br.com.condelivery.auth.security;

import br.com.condelivery.auth.feignClients.UserFeignClient;
import br.com.condelivery.auth.model.Condominium;
import br.com.condelivery.auth.model.Resident;
import br.com.condelivery.auth.service.ResidentTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//@Component
public class SecurityFilter /*extends OncePerRequestFilter*/ {

    /*@Autowired
    private ResidentTokenService tokenService;

    @Autowired
    private UserFeignClient userFeignClient;

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = getToken(request);
        logger.info("Token JWT: {}", tokenJWT);

        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            logger.info("Subject do Token: {}", subject);

            // Supondo que a estrutura de seu Feign Client retorne um Resident ou Condominium baseado no email
            ResponseEntity<?> residentResponse = userFeignClient.findResidentByEmail(subject);
            ResponseEntity<?> condominiumResponse = userFeignClient.findCondominiumByEmail(subject);

            if (residentResponse.getBody() != null) {
                Resident resident = (Resident) residentResponse.getBody();
                var authentication = new UsernamePasswordAuthenticationToken(resident, null, resident.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("Resident autenticado: {}", resident.getEmail());
            } else if (condominiumResponse.getBody() != null) {
                Condominium condominium = (Condominium) condominiumResponse.getBody();
                var authentication = new UsernamePasswordAuthenticationToken(condominium, null, condominium.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("Condomínio autenticado: {}", condominium.getEmail());
            } else {
                logger.warn("Usuário não encontrado para o token: {}", subject);
            }
        } else {
            logger.warn("Token não encontrado.");
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }

        return null;
    }

     */
}
