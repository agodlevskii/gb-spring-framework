CREATE TABLE product(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE,
    cost DECIMAL(10, 2) NOT NULL
);

GRANT ALL PRIVILEGES ON TABLE product TO geek;
GRANT ALL PRIVILEGES ON TABLE product_id_seq TO geek;

INSERT INTO product (name, cost) VALUES ('Product 1', 199.99), ('Product 2', 299.99), ('Product 3', 399.99), ('Product 4', 499.99),
                                        ('Product 5', 599.99), ('Product 6', 699.99), ('Product 7', 799.99), ('Product 8', 899.99),
                                        ('Product 9', 999.99), ('Product 10', 1099.99), ('Product 11', 1199.99), ('Product 12', 1299.99),
                                        ('Product 13', 1399.99), ('Product 14', 1499.99), ('Product 15', 1599.99), ('Product 16', 1699.99),
                                        ('Product 17', 1799.99), ('Product 18', 1899.99), ('Product 19', 1999.99), ('Product 20', 2099.99);
