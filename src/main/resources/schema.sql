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


  CREATE TABLE `eshop`.`products` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `description` MEDIUMTEXT NULL,
    `slug` VARCHAR(300) NULL,
    `category` INT NULL,
    `picture` VARCHAR(150) NULL,
    `price` VARCHAR(45) NULL,
    `status` VARCHAR(45) NULL,
    PRIMARY KEY (`id`));

ALTER TABLE `eshop`.`products`
ADD UNIQUE INDEX `slug_UNIQUE` (`slug` ASC) VISIBLE;
;

ALTER TABLE `eshop`.`categories`
ADD UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE;
;


