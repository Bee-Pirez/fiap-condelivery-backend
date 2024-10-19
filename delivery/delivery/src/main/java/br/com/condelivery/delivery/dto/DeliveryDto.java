package br.com.condelivery.delivery.dto;

import br.com.condelivery.delivery.model.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {

    private Long id;
    private Status status;
    private Long orderId;
    private Long residentId;

}
