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
        // Busca o ResidentCondominium pelo residentId
        ResidentCondominium residentCondominium = residentCondominiumRepository.findByResidentId(residentId)
                .orElseThrow(() -> new ResourceNotFoundException("ResidentCondominium não encontrado"));

        // Atualiza os campos block e apartment
        residentCondominium.setBlock(residentCondominiumDto.getBlock());
        residentCondominium.setApartment(residentCondominiumDto.getApartment());

        // Salva as alterações
        ResidentCondominium updatedResidentCondominium = residentCondominiumRepository.save(residentCondominium);

        // Mapeia o ResidentCondominium atualizado para ResidentCondominiumDto
        return modelMapper.map(updatedResidentCondominium, ResidentCondominiumDto.class);
    }

    public ResidentCondominiumDto createResidentCondominium(ResidentCondominiumDto residentCondominiumDto) {
        // Mapeia o DTO para a entidade ResidentCondominium
        ResidentCondominium residentCondominium = modelMapper.map(residentCondominiumDto, ResidentCondominium.class);

        // Salva a nova associação
        ResidentCondominium savedResidentCondominium = residentCondominiumRepository.save(residentCondominium);

        // Mapeia a entidade salva de volta para o DTO
        return modelMapper.map(savedResidentCondominium, ResidentCondominiumDto.class);
    }
}
