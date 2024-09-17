package br.com.condelivery.products.repository;

import br.com.condelivery.products.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Partner, Long> {
}
