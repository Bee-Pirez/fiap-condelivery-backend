package br.com.condelivery.auth.controller;

import br.com.condelivery.auth.dto.AuthenticationData;
import br.com.condelivery.auth.dto.JWTTokenData;
import br.com.condelivery.auth.model.Resident;
import br.com.condelivery.auth.service.AuthenticationResidentService;
import br.com.condelivery.auth.service.ResidentTokenService;
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

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthenticationData data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.genarateToken((Resident) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTTokenData(tokenJWT));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Resident> findByEmail(@RequestParam String email) {
        try {
            Resident resident = service.findByEmail(email);
            return ResponseEntity.ok(resident);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
