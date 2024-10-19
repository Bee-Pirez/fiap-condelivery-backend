package br.com.condelivery.user.service;

import br.com.condelivery.user.dto.*;
import br.com.condelivery.user.exception.ResourceNotFoundException;
import br.com.condelivery.user.model.Address;
import br.com.condelivery.user.model.Condominium;
import br.com.condelivery.user.model.Resident;
import br.com.condelivery.user.repository.CondominiumRepository;
import br.com.condelivery.user.repository.ResidentRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CondominiumService {
    @Autowired
    private CondominiumRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /*public List<ResidentDto> getResidentsByCondominiumId(Long condominiumId) {
        List<Resident> residents = repository.findByCondominiumId(condominiumId);
        return residents.stream().map(resident -> modelMapper.map(resident, ResidentDto.class)).collect(Collectors.toList());
    }

     */

    public Condominium findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));
    }

    public CondominiumDto getCondominiumById(Long id) {
        Condominium condo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));
        return modelMapper.map(condo, CondominiumDto.class);
    }

    public CondominiumDto getCondominiumByEmail(String email) {
        Condominium condo = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));
        return modelMapper.map(condo, CondominiumDto.class);
    }

    public CondominiumDto registerCondominium(@Valid CondominiumRegisterDto condoRegisterDto) {
        if (repository.findByEmail(condoRegisterDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Resident with this email already exists");
        }

        Condominium condo = modelMapper.map(condoRegisterDto, Condominium.class);
        Condominium savedCondominium = repository.save(condo);

        return modelMapper.map(savedCondominium, CondominiumDto.class);
    }


    public CondominiumDto updatePassword(Long id, @RequestBody @Valid CondominiumDto condominiumDtoDto) {
        // Buscar o residente pelo ID
        Condominium condo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));

        String encryptedPassword = passwordEncoder.encode(condominiumDtoDto.getPassword());

        condo.setPassword(encryptedPassword);

        condo = repository.save(condo);

        return modelMapper.map(condo, CondominiumDto.class);
    }

    public CondominiumDto updateResident(Long id, @RequestBody @Valid CondominiumDto condominiumDto) {
        Condominium condominium = findById(id);

        if (condominiumDto.getName() != null) {
            condominium.setName(condominiumDto.getName());
        }
        if (condominiumDto.getTelephone() != null) {
            condominium.setTelephone(condominiumDto.getTelephone());
        }
        if (condominiumDto.getEmail() != null) {
            if (repository.findByEmail(condominiumDto.getEmail()).isPresent() &&
                    !condominium.getEmail().equals(condominiumDto.getEmail())) {
                throw new IllegalArgumentException("Condominium with this email already exists");
            }
            condominium.setEmail(condominiumDto.getEmail());
        }

        condominium = repository.save(condominium);
        return modelMapper.map(condominium, CondominiumDto.class);
    }

    public CondominiumDto updateAddressId(Long id, CondominiumDto condominiumDto) {
        Condominium condominium = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condomínio não encontrado"));

        if (condominiumDto.getAddressId() != null) {
            if (repository.findByAddress_Id(condominiumDto.getAddressId()).isPresent()) {
                throw new IllegalArgumentException("O endereço já está associado a outro condomínio.");
            }

            Address address = new Address();
            address.setId(condominiumDto.getAddressId());
            condominium.setAddress(address);
        }

        condominium = repository.save(condominium);

        return modelMapper.map(condominium, CondominiumDto.class);
    }

    public CondominiumDto getCondominiumWithAddress(Long id) {
        // Busca o condomínio pelo ID
        Condominium condominium = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condomínio não encontrado"));

        // Mapeia o condomínio para o DTO, incluindo o endereço
        CondominiumDto condominiumDto = modelMapper.map(condominium, CondominiumDto.class);

        // Se o endereço não for nulo, mapeie-o também
        if (condominium.getAddress() != null) {
            AddressDto addressDto = modelMapper.map(condominium.getAddress(), AddressDto.class);
            condominiumDto.setAddress(addressDto); // Define o endereço no DTO do condomínio
        }

        return condominiumDto; // Retorna o DTO do condomínio com o endereço
    }

    /*public ResponseEntity<?> updateEnderecoId(Long id, CondominiumDto condominiumDto) {
        Condominium condominium = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));

        // Verificar se o endereço está nulo no banco de dados
        if (condominium.getAddress() == null) {
            // Atribuir o ID do endereço ao condomínio
            Address address = new Address();
            address.setId(condominiumDto.getAddressId());
            condominium.setAddress(address);

            // Salvar o condomínio atualizado
            condominium = repository.save(condominium);
            return ResponseEntity.ok(modelMapper.map(condominium, CondominiumDto.class));
        } else {
            // Retornar uma mensagem informando que o condomínio já possui um endereço
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O condomínio já tem um endereço criado.");
        }
    }


    //update imagem


    //update resident



    /*Atualizar informações do resident
    public ResidentDto updateResident(Long id, ResidentDto residentDto) {
        Resident resident = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));
        resident.setName(residentDto.getName());
        resident.setCpf(residentDto.getCpf());
        resident.setApto(residentDto.getApto());

        repository.save(resident);
        return modelMapper.map(resident, ResidentDto.class);
    }
     */
}


