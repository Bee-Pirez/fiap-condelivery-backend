CREATE TABLE residents (
    id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    cpf VARCHAR(45) NOT NULL,
    is_deliveryman BOOLEAN DEFAULT FALSE,
    img_url VARCHAR(255) NOT NULL,
    block VARCHAR(45) NOT NULL,
    apartment VARCHAR(45) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    condominium_id BIGINT,
    FOREIGN KEY (condominium_id) REFERENCES condominiums(id)
);