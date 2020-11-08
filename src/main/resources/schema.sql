DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    id INTEGER NOT NULL AUTO_INCREMENT,
    customer_name VARCHAR(128) NOT NULL,
    email VARCHAR(128),
    next_service DATE,
    notification_status VARCHAR(10),
    PRIMARY KEY (id)
);