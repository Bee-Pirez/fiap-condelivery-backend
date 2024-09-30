package br.com.condelivery.auth.controller;

import br.com.condelivery.auth.model.Resident;
import br.com.condelivery.auth.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class ResidentController {

    @Autowired
    private ResidentService service;

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
