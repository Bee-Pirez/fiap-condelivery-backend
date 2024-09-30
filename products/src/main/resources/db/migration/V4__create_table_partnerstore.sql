CREATE TABLE partner_store (
    partner_id BIGINT(20) NOT NULL,
    store_id BIGINT(20) NOT NULL,
    PRIMARY KEY (partner_id, store_id),
    FOREIGN KEY (partner_id) REFERENCES partners(id),
    FOREIGN KEY (store_id) REFERENCES stores(id)
);