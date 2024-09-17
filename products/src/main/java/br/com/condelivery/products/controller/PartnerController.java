package br.com.condelivery.products.controller;

import br.com.condelivery.products.dto.PartnerDto;
import br.com.condelivery.products.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping
    public List<PartnerDto> getPartners() {
        return partnerService.getAll();
    }
}