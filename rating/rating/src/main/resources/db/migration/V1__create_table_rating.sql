CREATE TABLE rating (
    id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    number INT,
    description VARCHAR(255),
    resident_id BIGINT(20),
    order_id BIGINT(20),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
