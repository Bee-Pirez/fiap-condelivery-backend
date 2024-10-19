package br.com.condelivery.user.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondominiumDto {

    private Long id;
    private String name;
    private String telephone;
    @Email(message = "Email inválido")
    private String email;
    private String password;
    private String imgUrl;
    private Long addressId;
    private AddressDto address;
}
