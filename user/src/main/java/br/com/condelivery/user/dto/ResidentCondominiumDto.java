package br.com.condelivery.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentCondominiumDto {
    private Long residentId;
    private Long condominiumId;
    private String block;
    private String apartment;
}
