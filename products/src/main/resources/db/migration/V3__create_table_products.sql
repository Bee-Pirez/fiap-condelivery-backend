CREATE TABLE products (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    description varchar(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    img_url varchar(255) NOT NULL,
    store_id BIGINT,
    FOREIGN KEY (store_id) REFERENCES stores(id)
);