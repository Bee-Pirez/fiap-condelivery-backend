package br.com.condelivery.products.service;

import br.com.condelivery.products.dto.PartnerDto;
import br.com.condelivery.products.model.Partner;
import br.com.condelivery.products.repository.PartnerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<PartnerDto> getAll() {
        List<Partner> partners = repository.findAll();
        return partners.stream()
                .map(partner -> modelMapper.map(partner, PartnerDto.class))
                .collect(Collectors.toList());
    }
}
