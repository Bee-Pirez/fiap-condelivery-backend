CREATE TABLE orders (
    id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    price DECIMAL(19, 2) NOT NULL,
    store_id BIGINT(20),
    resident_id BIGINT(20),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);