package br.com.condelivery.user.controller;

import br.com.condelivery.user.dto.ResidentDto;
import br.com.condelivery.user.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residents")
public class ResidentController {
    @Autowired
    private ResidentService residentService;

    @GetMapping("/condominium/{condominiumId}")
    public List<ResidentDto> getResidentsByCondominium(@PathVariable Long condominiumId) {
        return residentService.getResidentsByCondominiumId(condominiumId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidentDto> getResidentById(@PathVariable Long id) {
        return ResponseEntity.ok(residentService.getResidentById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<ResidentDto> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(residentService.getResidentByEmail(email));
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<ResidentDto> updateResident(@PathVariable Long id, @RequestBody ResidentDto residentDto) {
        ResidentDto updatedResident = residentService.updateResident(id, residentDto);
        return ResponseEntity.ok(updatedResident);
    }
     */
}
