CREATE TABLE inventory
(
    id       bigint(20)      NOT NULL AUTO_INCREMENT,
    sku_code varchar(255) NOT NULL,
    quantity int(11) DEFAULT NULL,
    PRIMARY KEY (id)
)