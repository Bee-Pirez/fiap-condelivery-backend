package br.com.condelivery.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "resident_condominium")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentCondominium implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "resident_id", nullable = false)
    private Resident resident;

    @Id
    @ManyToOne
    @JoinColumn(name = "condominium_id", nullable = false)
    private Condominium condominium;

    private String block;
    private String apartmentNumber;
}
