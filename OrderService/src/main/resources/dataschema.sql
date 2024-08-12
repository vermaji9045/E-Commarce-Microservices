CREATE TABLE IF NOT EXISTS `order` (
    `Id` BIGINT PRIMARY KEY,
    `order_Number` VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS `orderlineItem` (
    `Id` BIGINT PRIMARY KEY,
    `skuCode` VARCHAR(255),
    `price` DECIMAL(10, 2),
    `quantity` INT
);