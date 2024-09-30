package br.com.condelivery.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resident {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private boolean isDeliveryMan;
    private String imgUrl;
    private Condominium condominium;
    private String block;
    private String apartment;
}
