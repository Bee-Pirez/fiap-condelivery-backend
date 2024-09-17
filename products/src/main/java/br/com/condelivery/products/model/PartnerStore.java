package br.com.condelivery.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "partner_store")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerStore implements Serializable {

    @EmbeddedId
    private PartnerStoreId id;

    @ManyToOne
    @MapsId("partnerId")
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

    @ManyToOne
    @MapsId("storeId")
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
