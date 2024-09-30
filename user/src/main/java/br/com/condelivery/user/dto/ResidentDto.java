package br.com.condelivery.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private Boolean isDeliveryMan;
    private String imgUrl;
    private String block;
    private String apartment;
    private Long condominiumId;
}
