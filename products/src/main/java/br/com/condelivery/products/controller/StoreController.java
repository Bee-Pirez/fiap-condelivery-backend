package br.com.condelivery.products.controller;

import br.com.condelivery.products.dto.StoreDto;
import br.com.condelivery.products.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/partner/{partnerId}")
    public List<StoreDto> getStoreByPartnerId(@PathVariable Long partnerId) {
        return storeService.getStoresByPartner(partnerId);
    }
}