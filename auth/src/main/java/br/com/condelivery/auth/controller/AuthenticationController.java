package br.com.condelivery.auth.controller;

import br.com.condelivery.auth.dto.AuthenticationData;
import br.com.condelivery.auth.dto.JWTTokenData;
import br.com.condelivery.auth.model.Condominium;
import br.com.condelivery.auth.model.Resident;
//import br.com.condelivery.auth.service.AuthenticationCondominiumService;
import br.com.condelivery.auth.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private CondoTokenService condoTokenService;

    @Autowired
    private ResidentTokenService residentTokenService;

    @Autowired
    private AuthenticationResidentService authenticationResidentService;

    @Autowired
    private AuthenticationCondoService authenticationCondominiumService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //@Autowired
    //private AuthenticationService authenticationService;


    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping(value = "/login/resident")
    public ResponseEntity<?> loginResident(@RequestBody AuthenticationData data) {
        try {
            // Aqui, você deve garantir que está obtendo um Resident
            Resident resident = (Resident) authenticationResidentService.loadUserByUsername(data.email());

            // Verificação da senha
            validatePassword(data.password(), resident.getPassword());

            var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = manager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = residentTokenService.generateToken(resident);
            return ResponseEntity.ok(new JWTTokenData(token));
        } catch (UsernameNotFoundException e) {
            logger.error("Resident not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Resident não encontrado"));
        } catch (BadCredentialsException e) {
            logger.error("Invalid password: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Email ou senha inválidos."));
        } catch (ClassCastException e) {
            logger.error("Failed to cast UserDetails to Resident: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Erro interno no servidor."));
        } catch (Exception e) {
            logger.error("Authentication error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Ocorreu um erro durante a autenticação."));
        }
    }

    @PostMapping(value = "/login/condo")
    public ResponseEntity<?> loginCondominium(@RequestBody AuthenticationData data) {
        try {
            // Aqui, você deve garantir que está obtendo um Resident
            Condominium condominium = (Condominium) authenticationCondominiumService.loadUserByUsername(data.email());

            // Verificação da senha
            validatePassword(data.password(), condominium.getPassword());

            var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = manager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = condoTokenService.generateToken(condominium);
            return ResponseEntity.ok(new JWTTokenData(token));
        } catch (UsernameNotFoundException e) {
            logger.error("Resident not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Condominio não encontrado"));
        } catch (BadCredentialsException e) {
            logger.error("Invalid password: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Email ou senha inválidos."));
        } catch (ClassCastException e) {
            logger.error("Failed to cast UserDetails to Resident: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Erro interno no servidor."));
        } catch (Exception e) {
            logger.error("Authentication error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Ocorreu um erro durante a autenticação."));
        }
    }


    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new BadCredentialsException("Invalid password");
        }
    }
}


    /*@GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        try {
            String subject = tokenService.getSubject(token);

            return ResponseEntity.ok("Token is valid for subject: " + subject + tokenService.dataExpiracao());
        } catch (RuntimeException e) {
            logger.error("Error validating token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

     */

    /*@GetMapping(value = "/search")
    public ResponseEntity<Resident> findByEmail(@RequestParam String email) {
        try {
            Resident resident = service.findByEmail(email);
            return ResponseEntity.ok(resident);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

     */

