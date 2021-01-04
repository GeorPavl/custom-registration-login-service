-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema login_example
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema login_example
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `login_example` DEFAULT CHARACTER SET utf8 ;
USE `login_example` ;

-- -----------------------------------------------------
-- Table `login_example`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `login_example`.`users` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(125) NOT NULL,
  `password` VARCHAR(125) NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `login_example`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `login_example`.`authorities` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(125) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_authorities_user2` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_authorities_user2`
    FOREIGN KEY (`username`)
    REFERENCES `login_example`.`users` (`username`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `login_example`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `login_example`.`member` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(125) NOT NULL,
  `last_name` VARCHAR(125) NOT NULL,
  `email` VARCHAR(125) NOT NULL,
  `user_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_member_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_member_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `login_example`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
