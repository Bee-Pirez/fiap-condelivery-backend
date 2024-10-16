CREATE TABLE resident_condominium (
    resident_id BIGINT(20) NOT NULL,
    condominium_id BIGINT(20) NOT NULL,
    block VARCHAR(45) NOT NULL,
    apartment VARCHAR(45) NOT NULL,

    PRIMARY KEY (resident_id, condominium_id),
    FOREIGN KEY (resident_id) REFERENCES residents(id),
    FOREIGN KEY (condominium_id) REFERENCES condominiums(id)
);