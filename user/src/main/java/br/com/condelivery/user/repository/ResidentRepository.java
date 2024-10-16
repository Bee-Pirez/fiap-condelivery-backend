package br.com.condelivery.user.repository;

import br.com.condelivery.user.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {
    //List<Resident> findByCondominiumId(Long condominiumId);
    Optional<Resident> findByEmail(String email);
    boolean existsByEmail(String email);
}
