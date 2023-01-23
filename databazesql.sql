-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema fs-library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fs-library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fs-library` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_czech_ci ;
USE `fs-library` ;

-- -----------------------------------------------------
-- Table `fs-library`.`authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fs-library`.`authors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `year_of_birth` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_czech_ci;


-- -----------------------------------------------------
-- Table `fs-library`.`book_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fs-library`.`book_details` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `description` MEDIUMTEXT NULL DEFAULT NULL,
  `year` INT NULL DEFAULT NULL,
  `isbn` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 42
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_czech_ci;


-- -----------------------------------------------------
-- Table `fs-library`.`book_author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fs-library`.`book_author` (
  `book_id` INT NOT NULL,
  `author_id` INT NOT NULL,
  CONSTRAINT `FK_AUTHOR_01`
    FOREIGN KEY (`author_id`)
    REFERENCES `fs-library`.`authors` (`id`),
  CONSTRAINT `FK_BOOK_03`
    FOREIGN KEY (`book_id`)
    REFERENCES `fs-library`.`book_details` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_czech_ci;

CREATE INDEX `FK_AUTHOR_01_idx` ON `fs-library`.`book_author` (`author_id` ASC) VISIBLE;

CREATE INDEX `FK_BOOK_03_idx` ON `fs-library`.`book_author` (`book_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `fs-library`.`physical_copies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fs-library`.`physical_copies` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `borrowed` TINYINT(1) NULL DEFAULT '0',
  `book_detail_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_BOOK_DETAILS_01`
    FOREIGN KEY (`book_detail_id`)
    REFERENCES `fs-library`.`book_details` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 66
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_czech_ci;

CREATE INDEX `FK_BOOK_DETAILS_01_idx` ON `fs-library`.`physical_copies` (`book_detail_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `fs-library`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fs-library`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `registration_date` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 38
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_czech_ci;


-- -----------------------------------------------------
-- Table `fs-library`.`book_customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fs-library`.`book_customer` (
  `book_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`book_id`, `customer_id`),
  CONSTRAINT `FK_BOOK_00`
    FOREIGN KEY (`book_id`)
    REFERENCES `fs-library`.`physical_copies` (`id`),
  CONSTRAINT `FK_CUSTOMER_00`
    FOREIGN KEY (`customer_id`)
    REFERENCES `fs-library`.`customers` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_czech_ci;

CREATE INDEX `FK_CUSTOMER_00_idx` ON `fs-library`.`book_customer` (`customer_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `fs-library`.`genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fs-library`.`genres` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_czech_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_czech_ci;


-- -----------------------------------------------------
-- Table `fs-library`.`book_genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fs-library`.`book_genre` (
  `book_id` INT NOT NULL,
  `genre_id` INT NOT NULL,
  CONSTRAINT `FK_BOOK`
    FOREIGN KEY (`book_id`)
    REFERENCES `fs-library`.`book_details` (`id`),
  CONSTRAINT `FK_GENRE`
    FOREIGN KEY (`genre_id`)
    REFERENCES `fs-library`.`genres` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_czech_ci;

CREATE INDEX `íd_idx` ON `fs-library`.`book_genre` (`genre_id` ASC) VISIBLE;

CREATE INDEX `FK_BOOK_idx` ON `fs-library`.`book_genre` (`book_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
