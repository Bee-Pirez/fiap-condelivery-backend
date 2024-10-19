package br.com.condelivery.user.repository;

import br.com.condelivery.user.model.ResidentCondominium;
import br.com.condelivery.user.model.ResidentCondominiumId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidentCondominiumRepository extends JpaRepository<ResidentCondominium, ResidentCondominiumId> {
    List<ResidentCondominium> findByCondominiumId(Long condominiumId);

    Optional<ResidentCondominium> findByResidentId(Long residentId); // Retorna um Optional
}
