package br.com.condelivery.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "residents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "is_deliveryman", nullable = false)
    private boolean isDeliveryMan;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "condominium_id")
    private Condominium condominium;

    @Column(name = "block")
    private String block;

    @Column(name = "apartment")
    private String apartment;
}
