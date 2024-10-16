package br.com.condelivery.user.controller;

import br.com.condelivery.user.dto.ResidentDto;
import br.com.condelivery.user.dto.ResidentRegisterDto;
import br.com.condelivery.user.service.ResidentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/residents")
public class ResidentController {
    @Autowired
    private ResidentService residentService;

    /*@GetMapping("/condominium/{condominiumId}")
    public List<ResidentDto> getResidentsByCondominium(@PathVariable Long condominiumId) {
        return residentService.getResidentsByCondominiumId(condominiumId);
    }

     */

    @GetMapping("/{id}")
    public ResponseEntity<ResidentDto> getResidentById(@PathVariable Long id) {
        return ResponseEntity.ok(residentService.getResidentById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<ResidentDto> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(residentService.getResidentByEmail(email));
    }

    @PostMapping("/register")
    public ResponseEntity<ResidentDto> registerResident(@Valid @RequestBody ResidentRegisterDto residentRegisterDto) {
        ResidentDto newResident = residentService.registerResident(residentRegisterDto);
        return ResponseEntity.ok().build(); // 201 Created
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<ResidentDto> updatePassword(@PathVariable @NotNull Long id, @RequestBody @Valid ResidentDto dto) {
        ResidentDto updatedResident  = residentService.updatePassword(id, dto);
        return ResponseEntity.ok(updatedResident );
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<ResidentDto> updateResident(@PathVariable Long id, @RequestBody ResidentDto residentDto) {
        ResidentDto updatedResident = residentService.updateResident(id, residentDto);
        return ResponseEntity.ok(updatedResident);
    }
     */
}
