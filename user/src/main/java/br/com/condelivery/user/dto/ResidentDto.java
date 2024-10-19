package br.com.condelivery.user.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentDto {

    private Long id;
    private String name;
    @Email(message = "Email inválido")
    private String email;
    private String password;
    private String cpf;
    private Boolean isDeliveryMan;
    private String imgUrl;
}
