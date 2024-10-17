package br.com.condelivery.auth.security;

import br.com.condelivery.auth.feignClients.UserFeignClient;
import br.com.condelivery.auth.model.Resident;
import br.com.condelivery.auth.service.ResidentTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//@Component
public class SecurityFilter /*extends OncePerRequestFilter*/ {

    /*Autowired
    private ResidentTokenService tokenService;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = getToken(request);
        System.out.println("Token JWT: " + tokenJWT);

        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            System.out.println("Subject do Token: " + subject);

            var residentResponse = userFeignClient.findByEmail(subject);

            if (residentResponse.getBody() != null) {
                Resident resident = residentResponse.getBody();

                var authentication = new UsernamePasswordAuthenticationToken(resident, null, resident.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
            System.out.println("Resident não encontrado."); 
        }
        } else {
            System.out.println("Token não encontrado.");
        }

        System.out.println(tokenJWT);

        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }

        return null;
    }

     */
}
