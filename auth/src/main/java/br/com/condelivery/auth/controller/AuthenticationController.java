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
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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

    //@Autowired
    //private AuthenticationService authenticationService;


    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping(value = "/login/resident")
    public ResponseEntity<JWTTokenData> loginResident(@RequestBody AuthenticationData data) {
        try {
            var resident = authenticationResidentService.loadUserByUsername(data.email());
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = manager.authenticate(authenticationToken);

            if (authentication.getPrincipal() instanceof Resident) {
                var tokenJWT = residentTokenService.generateToken((Resident) authentication.getPrincipal());
                logger.info("Token JWT gerado para residente: " + tokenJWT);
                return ResponseEntity.ok(new JWTTokenData(tokenJWT));
            } else {
                return ResponseEntity.badRequest().body(new JWTTokenData("Autenticação falhou para residente."));
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(new JWTTokenData("Residente não encontrado."));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new JWTTokenData(e.getMessage()));
        }
    }

    @PostMapping(value = "/login/condo")
    public ResponseEntity<JWTTokenData> loginCondo(@RequestBody AuthenticationData data) {
        try {
            var condominium = authenticationCondominiumService.loadUserByUsername(data.email());
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = manager.authenticate(authenticationToken);

            if (authentication.getPrincipal() instanceof Condominium) {
                var tokenJWT = condoTokenService.generateToken((Condominium) authentication.getPrincipal());
                logger.info("Token JWT gerado para condomínio: " + tokenJWT);
                return ResponseEntity.ok(new JWTTokenData(tokenJWT));
            } else {
                return ResponseEntity.badRequest().body(new JWTTokenData("Autenticação falhou para condomínio."));
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(new JWTTokenData("Condomínio não encontrado."));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new JWTTokenData(e.getMessage()));
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

}
