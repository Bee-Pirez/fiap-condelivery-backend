package br.com.condelivery.order.repository;

import br.com.condelivery.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Consulta padrão para buscar pedidos de um residente
    List<Order> findByResidentId(Long residentId);

    // Consulta que traz todos os pedidos com itens associados para um residente específico
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items WHERE o.residentId = :residentId")
    List<Order> findAllByResidentIdWithItems(@Param("residentId") Long residentId);
}
