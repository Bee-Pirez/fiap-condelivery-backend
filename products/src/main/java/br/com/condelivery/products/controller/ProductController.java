package br.com.condelivery.products.controller;

import br.com.condelivery.products.dto.ProductDto;
import br.com.condelivery.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //// Endpoint para buscar todos os produtos relacionados a uma loja
    @GetMapping("/store/{storeId}")
    public List<ProductDto> getProductsByStoreId(@PathVariable Long storeId) {
        return productService.getProductsByStore(storeId);
    }
}
