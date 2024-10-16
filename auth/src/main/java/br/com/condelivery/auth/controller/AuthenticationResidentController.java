package br.com.condelivery.auth.controller;

import br.com.condelivery.auth.dto.AuthenticationData;
import br.com.condelivery.auth.dto.JWTTokenData;
import br.com.condelivery.auth.model.Resident;
import br.com.condelivery.auth.service.AuthenticationResidentService;
import br.com.condelivery.auth.service.ResidentTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResidentController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AuthenticationResidentService service;

    @Autowired
    private ResidentTokenService tokenService;

    private static Logger logger = LoggerFactory.getLogger(AuthenticationResidentService.class);

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthenticationData data) {
        try{
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.generateToken((Resident) authentication.getPrincipal());

            logger.info("Token JWT gerado: " + tokenJWT);
            return ResponseEntity.ok(new JWTTokenData(tokenJWT));
        } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(e.getMessage());
        }


        /*try{
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.genarateToken((Resident) authentication.getPrincipal());

            return ResponseEntity.ok(new JWTTokenData(tokenJWT));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

         */

    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        try {
            String subject = tokenService.getSubject(token);

            return ResponseEntity.ok("Token is valid for subject: " + subject + tokenService.dataExpiracao());
        } catch (RuntimeException e) {
            logger.error("Error validating token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

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
