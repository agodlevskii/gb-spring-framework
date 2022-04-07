CREATE TABLE product(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE,
    cost DECIMAL(10, 2) NOT NULL
);

GRANT ALL PRIVILEGES ON TABLE product TO geek;
GRANT ALL PRIVILEGES ON TABLE product_id_seq TO geek;

INSERT INTO product (name, cost) VALUES ('Product 1', 199.99);
INSERT INTO product (name, cost) VALUES ('Product 2', 299.99);
INSERT INTO product (name, cost) VALUES ('Product 3', 399.99);
INSERT INTO product (name, cost) VALUES ('Product 4', 499.99);
INSERT INTO product (name, cost) VALUES ('Product 5', 599.99);