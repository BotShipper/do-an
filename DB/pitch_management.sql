-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pitch_management
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pitch_management
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pitch_management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `pitch_management` ;

-- -----------------------------------------------------
-- Table `pitch_management`.`pitch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pitch_management`.`pitch` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `image` MEDIUMBLOB NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `create_at` DATETIME NULL DEFAULT NULL,
  `update_at` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pitch_management`.`time_slot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pitch_management`.`time_slot` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `start_time` TIME NULL DEFAULT NULL,
  `end_time` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pitch_management`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pitch_management`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `full_name` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `avatar` MEDIUMBLOB NULL DEFAULT NULL,
  `role` VARCHAR(45) NULL DEFAULT NULL,
  `create_at` DATETIME NULL DEFAULT NULL,
  `update_at` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pitch_management`.`book_pitch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pitch_management`.`book_pitch` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `note` VARCHAR(100) NULL DEFAULT NULL,
  `date_book` DATE NULL DEFAULT NULL,
  `used` TINYINT NULL DEFAULT NULL,
  `create_at` DATETIME NULL DEFAULT NULL,
  `update_at` DATETIME NULL DEFAULT NULL,
  `user_id` BIGINT NOT NULL,
  `pitch_id` BIGINT NOT NULL,
  `time_slot_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_book_pitch_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_book_pitch_pitch1_idx` (`pitch_id` ASC) VISIBLE,
  INDEX `fk_book_pitch_time_slot1_idx` (`time_slot_id` ASC) VISIBLE,
  CONSTRAINT `fk_book_pitch_pitch1`
    FOREIGN KEY (`pitch_id`)
    REFERENCES `pitch_management`.`pitch` (`id`),
  CONSTRAINT `fk_book_pitch_time_slot1`
    FOREIGN KEY (`time_slot_id`)
    REFERENCES `pitch_management`.`time_slot` (`id`),
  CONSTRAINT `fk_book_pitch_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `pitch_management`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pitch_management`.`bill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pitch_management`.`bill` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NULL DEFAULT NULL,
  `pay_method` VARCHAR(45) NULL DEFAULT NULL,
  `create_at` DATETIME NULL DEFAULT NULL,
  `book_pitch_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bill_book_pitch1_idx` (`book_pitch_id` ASC) VISIBLE,
  CONSTRAINT `fk_bill_book_pitch1`
    FOREIGN KEY (`book_pitch_id`)
    REFERENCES `pitch_management`.`book_pitch` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pitch_management`.`pitch_time_slot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pitch_management`.`pitch_time_slot` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NULL DEFAULT NULL,
  `closed` TINYINT NULL DEFAULT '0',
  `pitch_id` BIGINT NOT NULL,
  `time_slot_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unique_field_time_slot` (`pitch_id` ASC, `time_slot_id` ASC) VISIBLE,
  INDEX `fk_pitch_has_time_slot_time_slot1_idx` (`time_slot_id` ASC) VISIBLE,
  INDEX `fk_pitch_has_time_slot_pitch1_idx` (`pitch_id` ASC) VISIBLE,
  CONSTRAINT `fk_pitch_has_time_slot_pitch1`
    FOREIGN KEY (`pitch_id`)
    REFERENCES `pitch_management`.`pitch` (`id`),
  CONSTRAINT `fk_pitch_has_time_slot_time_slot1`
    FOREIGN KEY (`time_slot_id`)
    REFERENCES `pitch_management`.`time_slot` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
