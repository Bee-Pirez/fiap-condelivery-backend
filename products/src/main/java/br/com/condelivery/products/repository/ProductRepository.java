package br.com.condelivery.products.repository;

import br.com.condelivery.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Buscar todos os produtos relacionados a uma loja
    List<Product> findByStoreId(Long storeId);
}