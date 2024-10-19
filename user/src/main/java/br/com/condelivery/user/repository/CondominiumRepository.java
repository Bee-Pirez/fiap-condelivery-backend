package br.com.condelivery.user.repository;

import br.com.condelivery.user.model.Condominium;
import br.com.condelivery.user.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondominiumRepository extends JpaRepository<Condominium, Long> {
    //List<Resident> findByCondominiumId(Long condominiumId);
    Optional<Condominium> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Condominium> findByAddress_Id(Long addressId);
}
