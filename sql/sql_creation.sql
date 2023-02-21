-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema conferencedb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `conferencedb` ;

-- -----------------------------------------------------
-- Schema conferencedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `conferencedb` ;
USE `conferencedb` ;

-- -----------------------------------------------------
-- Table `conferencedb`.`Categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conferencedb`.`Categories` ;

CREATE TABLE IF NOT EXISTS `conferencedb`.`Categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `conferencedb`.`Event_statuses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conferencedb`.`Event_statuses` ;

CREATE TABLE IF NOT EXISTS `conferencedb`.`Event_statuses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `description_UNIQUE` (`description` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `conferencedb`.`Events`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conferencedb`.`Events` ;

CREATE TABLE IF NOT EXISTS `conferencedb`.`Events` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `theme` VARCHAR(100) NOT NULL,
  `category_id` INT NOT NULL,
  `event_status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_events_category_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_events_event_status_idx` (`event_status_id` ASC) VISIBLE,
  FULLTEXT INDEX `event_theme` (`theme`) VISIBLE,
  CONSTRAINT `fk_Events_Categories`
    FOREIGN KEY (`category_id`)
    REFERENCES `conferencedb`.`Categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Events_Event_statuses1`
    FOREIGN KEY (`event_status_id`)
    REFERENCES `conferencedb`.`Event_statuses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conferencedb`.`Reports`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conferencedb`.`Reports` ;

CREATE TABLE IF NOT EXISTS `conferencedb`.`Reports` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `topic` VARCHAR(100) NOT NULL,
  `start_time` DATETIME NULL,
  `end_time` DATETIME NULL,
  `description` VARCHAR(255) NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_report_event_idx` (`event_id` ASC) VISIBLE,
  FULLTEXT INDEX `report_topic` (`topic`) VISIBLE,
  CONSTRAINT `fk_Reports_Events1`
    FOREIGN KEY (`event_id`)
    REFERENCES `conferencedb`.`Events` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conferencedb`.`Roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conferencedb`.`Roles` ;

CREATE TABLE IF NOT EXISTS `conferencedb`.`Roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `conferencedb`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conferencedb`.`Users` ;

CREATE TABLE IF NOT EXISTS `conferencedb`.`Users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Users_role_idx` (`role_id` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  FULLTEXT INDEX `users_first_name_last_name` (`first_name`, `last_name`) VISIBLE,
  CONSTRAINT `fk_Users_Roles1`
    FOREIGN KEY (`role_id`)
    REFERENCES `conferencedb`.`Roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conferencedb`.`Users_has_Reports`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conferencedb`.`Users_has_Reports` ;

CREATE TABLE IF NOT EXISTS `conferencedb`.`Users_has_Reports` (
  `user_id` INT NOT NULL,
  `report_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `report_id`),
  INDEX `fk_Users_has_Reports_report_idx` (`report_id` ASC) VISIBLE,
  INDEX `fk_Users_has_Reports_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Users_has_Reports_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `conferencedb`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Users_has_Reports_Reports1`
    FOREIGN KEY (`report_id`)
    REFERENCES `conferencedb`.`Reports` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conferencedb`.`Marks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conferencedb`.`Marks` ;

CREATE TABLE IF NOT EXISTS `conferencedb`.`Marks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sign` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `sign_UNIQUE` (`sign` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `conferencedb`.`Responces`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conferencedb`.`Responces` ;

CREATE TABLE IF NOT EXISTS `conferencedb`.`Responces` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `text` VARCHAR(255) NULL,
  `user_id` INT NOT NULL,
  `mark_id` INT NOT NULL,
  `report_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_responces_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_responces_mark_idx` (`mark_id` ASC) VISIBLE,
  INDEX `fk_responces_report_idx` (`report_id` ASC) VISIBLE,
  INDEX `idx_responces_date` (`date` ASC) VISIBLE,
  CONSTRAINT `fk_Responces_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `conferencedb`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Responces_Marks1`
    FOREIGN KEY (`mark_id`)
    REFERENCES `conferencedb`.`Marks` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Responces_Reports1`
    FOREIGN KEY (`report_id`)
    REFERENCES `conferencedb`.`Reports` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conferencedb`.`Event_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conferencedb`.`Event_details` ;

CREATE TABLE IF NOT EXISTS `conferencedb`.`Event_details` (
  `event_id` INT NOT NULL,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`event_id`),
  INDEX `fk_Event_details_Events1_idx` (`event_id` ASC) VISIBLE,
  CONSTRAINT `fk_Event_details_Events1`
    FOREIGN KEY (`event_id`)
    REFERENCES `conferencedb`.`Events` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
