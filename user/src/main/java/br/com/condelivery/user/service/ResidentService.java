package br.com.condelivery.user.service;

import br.com.condelivery.user.dto.ResidentDto;
import br.com.condelivery.user.exception.ResourceNotFoundException;
import br.com.condelivery.user.model.Resident;
import br.com.condelivery.user.repository.ResidentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResidentService {
    @Autowired
    private ResidentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ResidentDto> getResidentsByCondominiumId(Long condominiumId) {
        List<Resident> residents = repository.findByCondominiumId(condominiumId);
        return residents.stream().map(resident -> modelMapper.map(resident, ResidentDto.class)).collect(Collectors.toList());
    }

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
