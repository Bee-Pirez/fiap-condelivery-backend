package br.com.condelivery.products.service;

import br.com.condelivery.products.dto.StoreDto;
import br.com.condelivery.products.model.PartnerStore;
import br.com.condelivery.products.model.Store;
import br.com.condelivery.products.repository.PartnerStoreRepository;
import br.com.condelivery.products.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {

    @Autowired
    private PartnerStoreRepository partnerStoreRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<StoreDto> getStoresByPartner(Long partnerId) {
        List<PartnerStore> partnerStores = partnerStoreRepository.findByPartnerId(partnerId);

        if (partnerStores.isEmpty()) {
            throw new EntityNotFoundException("Parceiro não encontrado ou não tem lojas associadas");
        }

        return partnerStores.stream()
                .map(partnerStore -> modelMapper.map(partnerStore.getStore(), StoreDto.class))
                .collect(Collectors.toList());
    }

    public StoreDto getStoreById(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com o ID: " + storeId));

        return modelMapper.map(store, StoreDto.class);
    }
}