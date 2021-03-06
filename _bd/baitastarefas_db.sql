-- MySQL Script generated by MySQL Workbench
-- seg 03 set 2018 20:03:39 -03
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema baitastarefas_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema baitastarefas_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `baitastarefas_db` DEFAULT CHARACTER SET utf8 ;
USE `baitastarefas_db` ;

-- -----------------------------------------------------
-- Table `baitastarefas_db`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baitastarefas_db`.`Users` ;

CREATE TABLE IF NOT EXISTS `baitastarefas_db`.`Users` (
  `idUsers` INT ZEROFILL UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `user` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idUsers`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baitastarefas_db`.`Tasks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baitastarefas_db`.`Tasks` ;

CREATE TABLE IF NOT EXISTS `baitastarefas_db`.`Tasks` (
  `idTasks` INT ZEROFILL UNSIGNED NOT NULL AUTO_INCREMENT,
  `Users_idUsers` INT ZEROFILL UNSIGNED NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `previsionFinish` DATE NOT NULL,
  `finished` TINYINT(1) NOT NULL,
  `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idTasks`, `Users_idUsers`),
  INDEX `fk_Tasks_Users_idx` (`Users_idUsers` ASC),
  CONSTRAINT `fk_Tasks_Users`
    FOREIGN KEY (`Users_idUsers`)
    REFERENCES `baitastarefas_db`.`Users` (`idUsers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baitastarefas_db`.`Session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baitastarefas_db`.`Session` ;

CREATE TABLE IF NOT EXISTS `baitastarefas_db`.`Session` (
  `idSession` INT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `Users_idUsers` INT ZEROFILL UNSIGNED NOT NULL,
  `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idSession`, `Users_idUsers`),
  INDEX `fk_Session_Users1_idx` (`Users_idUsers` ASC),
  CONSTRAINT `fk_Session_Users1`
    FOREIGN KEY (`Users_idUsers`)
    REFERENCES `baitastarefas_db`.`Users` (`idUsers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
