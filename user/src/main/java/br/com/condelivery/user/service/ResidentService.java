package br.com.condelivery.user.service;

import br.com.condelivery.user.dto.ResidentDto;
import br.com.condelivery.user.dto.ResidentRegisterDto;
import br.com.condelivery.user.exception.ResourceNotFoundException;
import br.com.condelivery.user.model.Resident;
import br.com.condelivery.user.repository.ResidentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResidentService {
    @Autowired
    private ResidentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /*public List<ResidentDto> getResidentsByCondominiumId(Long condominiumId) {
        List<Resident> residents = repository.findByCondominiumId(condominiumId);
        return residents.stream().map(resident -> modelMapper.map(resident, ResidentDto.class)).collect(Collectors.toList());
    }

     */

    public ResidentDto getResidentById(Long id) {
        Resident resident = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));
        return modelMapper.map(resident, ResidentDto.class);
    }

    public ResidentDto getResidentByEmail(String email) {
        Resident resident = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));
        return modelMapper.map(resident, ResidentDto.class);
    }

    public ResidentDto registerResident(@Valid ResidentRegisterDto residentDto) {
        // Verificar se o residente já existe
        if (repository.findByEmail(residentDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Resident with this email already exists");
        }

        // Mapear o DTO para a entidade Resident
        Resident resident = modelMapper.map(residentDto, Resident.class);
        Resident savedResident = repository.save(resident);

        return modelMapper.map(savedResident, ResidentDto.class);
    }

    /*public ResidentDto updatePassword(Long id, ResidentDto dto) {
        Resident resident = modelMapper.map(dto, Resident.class);
        resident.setId(id);
        resident = repository.save(resident);
        return modelMapper.map(resident, ResidentDto.class);
    }

     */

    //update password
    public ResidentDto updatePassword(Long id, @RequestBody @Valid ResidentDto residentDto) {
        // Buscar o residente pelo ID
        Resident resident = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));

        String encryptedPassword = passwordEncoder.encode(residentDto.getPassword());

        resident.setPassword(encryptedPassword);

        resident = repository.save(resident);

        return modelMapper.map(resident, ResidentDto.class);
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
