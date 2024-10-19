package br.com.condelivery.user.controller;

import br.com.condelivery.user.dto.ResidentCondominiumDto;
import br.com.condelivery.user.dto.ResidentDto;
import br.com.condelivery.user.dto.ResidentRegisterDto;
import br.com.condelivery.user.exception.ResourceNotFoundException;
import br.com.condelivery.user.model.Resident;
import br.com.condelivery.user.repository.ResidentRepository;
import br.com.condelivery.user.service.ResidentCondominiumService;
import br.com.condelivery.user.service.ResidentService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/residents")
public class ResidentController {
    @Autowired
    private ResidentService residentService;

    @Autowired
    private ResidentCondominiumService residentCondominiumService;

    @Autowired
    private ResidentRepository repository;

    @Autowired
    private ModelMapper modelMapper;


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
        ResidentDto residentDto = residentService.getResidentByEmail(email);
        if (residentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(residentDto);
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

    @PutMapping("/{id}/accountdata")
    public ResponseEntity<ResidentDto> updateResident(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid ResidentDto residentDto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {

        String token = authorizationHeader.substring(7);

        DecodedJWT decodedJWT = JWT.decode(token);
        String authenticatedEmail = decodedJWT.getSubject();

        Resident resident = residentService.findById(id);

        try {
            // Verificar se o email do residente é igual ao do usuário autenticado
            if (!resident.getEmail().equals(authenticatedEmail)) {
                throw new Exception("Você não tem permissão para alterar esses dados.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        ResidentDto updatedResident = residentService.updateResident(id, residentDto);
        return ResponseEntity.ok(updatedResident);
    }

    @PutMapping("/{id}/deliveryman")
    public ResponseEntity<ResidentDto> updateDeliveryManStatus(
            @PathVariable @NotNull Long id,
            @RequestBody Boolean isDeliveryMan) {

        ResidentDto updatedResident = residentService.updateDeliveryManStatus(id, isDeliveryMan);
        return ResponseEntity.ok(updatedResident);
    }

    @GetMapping("/condominiums/{condominiumId}/all")
    public ResponseEntity<List<ResidentDto>> getResidentsByCondominiumId(@PathVariable Long condominiumId) {
        List<ResidentDto> residents = residentService.getResidentsByCondominiumId(condominiumId);
        return ResponseEntity.ok(residents);
    }


    @PutMapping("/resident-condominium/{residentId}")
    public ResponseEntity<ResidentCondominiumDto> updateResidentCondominium(
            @PathVariable Long residentId,
            @RequestBody ResidentCondominiumDto residentCondominiumDto) {
        try {
            ResidentCondominiumDto updatedResidentCondominium = residentCondominiumService.updateResidentCondominium(residentId, residentCondominiumDto);
            return ResponseEntity.ok(updatedResidentCondominium);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/resident-condominium")
    public ResponseEntity<ResidentCondominiumDto> createResidentCondominium(@RequestBody ResidentCondominiumDto residentCondominiumDto) {
        try {
            ResidentCondominiumDto createdResidentCondominium = residentCondominiumService.createResidentCondominium(residentCondominiumDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdResidentCondominium);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    /*@PutMapping("/{id}/accountdata")
    public ResponseEntity<ResidentDto> updateResident(@PathVariable @NotNull Long id, @RequestBody @Valid ResidentDto residentDto) {
        ResidentDto updatedResident = residentService.updateResident(id, residentDto);
        return ResponseEntity.ok(updatedResident);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<ResidentDto> updateResident(@PathVariable Long id, @RequestBody ResidentDto residentDto) {
        ResidentDto updatedResident = residentService.updateResident(id, residentDto);
        return ResponseEntity.ok(updatedResident);
    }
     */
}
