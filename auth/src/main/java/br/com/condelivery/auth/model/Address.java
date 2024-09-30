package br.com.condelivery.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String number;
}
