package br.com.condelivery.products.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PartnerStoreId implements Serializable {

    @Column(name = "partner_id")
    private Long partnerId;

    @Column(name = "store_id")
    private Long storeId;

    public PartnerStoreId() {}

    public PartnerStoreId(Long partnerId, Long storeId) {
        this.partnerId = partnerId;
        this.storeId = storeId;
    }

    // equals() and hashCode() methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartnerStoreId that = (PartnerStoreId) o;
        return Objects.equals(partnerId, that.partnerId) && Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partnerId, storeId);
    }
}
