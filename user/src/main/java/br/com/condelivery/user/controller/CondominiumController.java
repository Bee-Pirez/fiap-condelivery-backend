package br.com.condelivery.user.controller;

import br.com.condelivery.user.dto.*;
import br.com.condelivery.user.exception.ResourceNotFoundException;
import br.com.condelivery.user.model.Address;
import br.com.condelivery.user.model.Condominium;
import br.com.condelivery.user.model.Resident;
import br.com.condelivery.user.repository.AddressRepository;
import br.com.condelivery.user.repository.CondominiumRepository;
import br.com.condelivery.user.service.AddressService;
import br.com.condelivery.user.service.CondominiumService;
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

@RestController
@RequestMapping("/condominiums")
public class CondominiumController {
    @Autowired
    private CondominiumService service;
    @Autowired
    private CondominiumService condominiumService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CondominiumRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CondominiumDto> getCondominiumById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCondominiumById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<CondominiumDto> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.getCondominiumByEmail(email));
    }

    @PostMapping("/register")
    public ResponseEntity<CondominiumDto> registerResident(@Valid @RequestBody CondominiumRegisterDto condoRegisterDto) {
        CondominiumDto newCondo = service.registerCondominium(condoRegisterDto);
        return ResponseEntity.ok().build(); // 201 Created
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<CondominiumDto> updatePassword(@PathVariable @NotNull Long id, @RequestBody @Valid CondominiumDto dto) {
        CondominiumDto updatedCondo  = service.updatePassword(id, dto);
        return ResponseEntity.ok(updatedCondo );
    }

    @PutMapping("/{id}/accountdata")
    public ResponseEntity<CondominiumDto> updateResident(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid CondominiumDto condominiumDto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {

        String token = authorizationHeader.substring(7);

        DecodedJWT decodedJWT = JWT.decode(token);
        String authenticatedEmail = decodedJWT.getSubject();

        Condominium condominium = condominiumService.findById(id);

        try {
            if (!condominium.getEmail().equals(authenticatedEmail)) {
                throw new Exception("Você não tem permissão para alterar esses dados.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        CondominiumDto updatedResident = condominiumService.updateResident(id, condominiumDto);
        return ResponseEntity.ok(updatedResident);
    }


    // Endpoint para criar um novo endereço
    @PostMapping("/address/register")
    public ResponseEntity<?> createAddress(@RequestBody AddressDto addressDto) {
        try {
            AddressDto createdAddress = addressService.createAddress(addressDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/addressId")
    public ResponseEntity<CondominiumDto> updateEnderecoId(@PathVariable Long id, @RequestBody CondominiumDto condominiumDto) {
        try {
            // Chama o serviço para atualizar o ID do endereço
            CondominiumDto updatedCondominium = condominiumService.updateAddressId(id, condominiumDto);
            return ResponseEntity.ok(updatedCondominium);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /*@PutMapping("/{id}/addressId")
    public ResponseEntity<?> updateEnderecoId(@PathVariable Long id, @RequestBody CondominiumDto condominiumDto) {
        try {
            return condominiumService.updateEnderecoId(id, condominiumDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
     */

    @PutMapping("/{id}/address")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id, @RequestBody AddressDto addressDto) {
        try {
            AddressDto updatedAddress = addressService.updateAddress(id, addressDto);
            return ResponseEntity.ok(updatedAddress);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<CondominiumDto> getCondominium(@PathVariable Long id) {
        try {
            CondominiumDto condominiumDto = condominiumService.getCondominiumWithAddress(id);
            return ResponseEntity.ok(condominiumDto); 
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}


