package br.com.condelivery.order.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private Long residentId;
    private Long storeId;

    private StoreDto store;
    private List<ItemDto> items;

    public OrderDto(Long id, BigDecimal price, LocalDateTime createdAt, Long residentId, Long storeId, List<ItemDto> items) {
        this.id = id;
        this.price = price;
        this.createdAt = createdAt;
        this.residentId = residentId;
        this.storeId = storeId;
        this.items = items;
    }
}
