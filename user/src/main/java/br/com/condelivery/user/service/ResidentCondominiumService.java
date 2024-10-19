package br.com.condelivery.user.service;

import br.com.condelivery.user.dto.ResidentCondominiumDto;
import br.com.condelivery.user.exception.ResourceNotFoundException;
import br.com.condelivery.user.model.ResidentCondominium;
import br.com.condelivery.user.repository.ResidentCondominiumRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidentCondominiumService {

    private final ResidentCondominiumRepository residentCondominiumRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ResidentCondominiumService(ResidentCondominiumRepository residentCondominiumRepository) {
        this.residentCondominiumRepository = residentCondominiumRepository;
    }

    public ResidentCondominiumDto updateResidentCondominium(Long residentId, ResidentCondominiumDto residentCondominiumDto) {
        ResidentCondominium residentCondominium = residentCondominiumRepository.findByResidentId(residentId)
                .orElseThrow(() -> new ResourceNotFoundException("ResidentCondominium não encontrado"));

        residentCondominium.setBlock(residentCondominiumDto.getBlock());
        residentCondominium.setApartment(residentCondominiumDto.getApartment());

        ResidentCondominium updatedResidentCondominium = residentCondominiumRepository.save(residentCondominium);

        return modelMapper.map(updatedResidentCondominium, ResidentCondominiumDto.class);
    }

    public ResidentCondominiumDto createResidentCondominium(ResidentCondominiumDto residentCondominiumDto) {
        ResidentCondominium residentCondominium = modelMapper.map(residentCondominiumDto, ResidentCondominium.class);

        ResidentCondominium savedResidentCondominium = residentCondominiumRepository.save(residentCondominium);
        return modelMapper.map(savedResidentCondominium, ResidentCondominiumDto.class);
    }
}
