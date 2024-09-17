package br.com.condelivery.products.repository;

import br.com.condelivery.products.model.PartnerStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerStoreRepository extends JpaRepository<PartnerStore, Long> {
    List<PartnerStore> findByPartnerId(Long partnerId);
}