package br.com.condelivery.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentRegisterDto {
    private Long id;
    private String name;
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;
    @NotBlank(message = "Senha é obrigatória")
    private String password;
    private String cpf;
    private Boolean isDeliveryMan;
    private String imgUrl;
    private String block;
    private String apartment;
    private Long condominiumId;
}