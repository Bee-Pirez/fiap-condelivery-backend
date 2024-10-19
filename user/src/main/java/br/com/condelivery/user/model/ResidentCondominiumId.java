package br.com.condelivery.user.model;

import java.io.Serializable;
import java.util.Objects;

public class ResidentCondominiumId implements Serializable {
    private Long resident;
    private Long condominium;

    public ResidentCondominiumId() {}

    public ResidentCondominiumId(Long resident, Long condominium) {
        this.resident = resident;
        this.condominium = condominium;
    }

    // Getters, setters, hashCode e equals
    @Override
    public int hashCode() {
        return Objects.hash(resident, condominium);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResidentCondominiumId that = (ResidentCondominiumId) o;
        return Objects.equals(resident, that.resident) &&
                Objects.equals(condominium, that.condominium);
    }
}
