CREATE TABLE IF NOT EXISTS user
(
    id             SERIAL PRIMARY KEY,
    first_name     VARCHAR(255),
    last_name      VARCHAR(255),
    email          VARCHAR(255),
    country        VARCHAR(255),
    street_address VARCHAR(255),
    city           VARCHAR(255),
    region         VARCHAR(255),
    postal_code    VARCHAR(255)
);


CREATE TABLE `eshop`.`categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` MEDIUMTEXT NULL,
  `parent` INT NULL,
  PRIMARY KEY (`id`));
