package br.com.condelivery.user.dto;

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
    private String email;
    private String password;
    private String imgUrl;
    private Long addressId;
}
