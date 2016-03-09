-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema gsi16gn
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `gsi16gn` ;

-- -----------------------------------------------------
-- Schema gsi16gn
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gsi16gn` DEFAULT CHARACTER SET utf8 ;
USE `gsi16gn` ;

-- -----------------------------------------------------
-- Table `gsi16gn`.`utilisateur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gsi16gn`.`utilisateur` ;

CREATE TABLE IF NOT EXISTS `gsi16gn`.`utilisateur` (
  `id` VARCHAR(50) NOT NULL,
  `nom` VARCHAR(45) NULL DEFAULT NULL,
  `prenom` VARCHAR(45) NULL DEFAULT NULL,
  `mail` VARCHAR(45) NULL DEFAULT NULL,
  `telephone` VARCHAR(45) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `admin` INT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gsi16gn`.`lieu_albi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gsi16gn`.`lieu_albi` ;

CREATE TABLE IF NOT EXISTS `gsi16gn`.`lieu_albi` (
  `id` INT(3) NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gsi16gn`.`type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gsi16gn`.`type` ;

CREATE TABLE IF NOT EXISTS `gsi16gn`.`type` (
  `id` INT(11) NOT NULL,
  `code` VARCHAR(20) NULL DEFAULT NULL,
  `libelle` VARCHAR(25) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gsi16gn`.`covoiturage_albi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gsi16gn`.`covoiturage_albi` ;

CREATE TABLE IF NOT EXISTS `gsi16gn`.`covoiturage_albi` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date_publication` DATETIME NULL DEFAULT NULL,
  `date_depart` DATETIME NULL DEFAULT NULL,
  `nombre_place` INT(2) NULL DEFAULT NULL,
  `description` LONGTEXT NULL DEFAULT NULL,
  `id_lieu_depart` INT(3) NULL DEFAULT NULL,
  `id_lieu_destination` INT(3) NULL DEFAULT NULL,
  `id_utilisateur` VARCHAR(50) NULL DEFAULT NULL,
  `id_type_covoiturage` INT(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_id_utilisateur_idx` (`id_utilisateur` ASC),
  INDEX `fk_id_lieu_depart_idx` (`id_lieu_depart` ASC),
  INDEX `fk_id_lieu_destination_idx` (`id_lieu_destination` ASC),
  INDEX `fk_id_type_idx` (`id_type_covoiturage` ASC),
  CONSTRAINT `fk_id_utilisateur`
    FOREIGN KEY (`id_utilisateur`)
    REFERENCES `gsi16gn`.`utilisateur` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_lieu_depart`
    FOREIGN KEY (`id_lieu_depart`)
    REFERENCES `gsi16gn`.`lieu_albi` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_lieu_destination`
    FOREIGN KEY (`id_lieu_destination`)
    REFERENCES `gsi16gn`.`lieu_albi` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_type`
    FOREIGN KEY (`id_type_covoiturage`)
    REFERENCES `gsi16gn`.`type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 39
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gsi16gn`.`lieu_france`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gsi16gn`.`lieu_france` ;

CREATE TABLE IF NOT EXISTS `gsi16gn`.`lieu_france` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(50) NULL DEFAULT NULL,
  `latitude` FLOAT NULL DEFAULT NULL,
  `longitude` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 275
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gsi16gn`.`taille_bagage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gsi16gn`.`taille_bagage` ;

CREATE TABLE IF NOT EXISTS `gsi16gn`.`taille_bagage` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `libelle` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gsi16gn`.`covoiturage_france`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gsi16gn`.`covoiturage_france` ;

CREATE TABLE IF NOT EXISTS `gsi16gn`.`covoiturage_france` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date_publication` DATETIME NULL DEFAULT NULL,
  `date_depart` DATETIME NULL DEFAULT NULL,
  `nombre_place` INT(2) NULL DEFAULT NULL,
  `prix` INT(2) NULL DEFAULT NULL,
  `description` LONGTEXT NULL DEFAULT NULL,
  `id_lieu_depart` INT(11) NULL DEFAULT NULL,
  `id_lieu_destination` INT(11) NULL DEFAULT NULL,
  `id_utilisateur` VARCHAR(50) NULL DEFAULT NULL,
  `id_taille_baguage` INT(11) NULL DEFAULT NULL,
  `id_type_covoiturage` INT(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_id_utilisateur_idx` (`id_utilisateur` ASC),
  INDEX `fkey_id_lieu_depart_idx` (`id_lieu_depart` ASC),
  INDEX `fkey_id_lieu_destination_idx` (`id_lieu_destination` ASC),
  INDEX `fkey_id__idx` (`id_taille_baguage` ASC),
  INDEX `fkey_id_type_covoiturage_idx` (`id_type_covoiturage` ASC),
  CONSTRAINT `fkey_id_lieu_depart`
    FOREIGN KEY (`id_lieu_depart`)
    REFERENCES `gsi16gn`.`lieu_france` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkey_id_lieu_destination`
    FOREIGN KEY (`id_lieu_destination`)
    REFERENCES `gsi16gn`.`lieu_france` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkey_id_taille_baguage`
    FOREIGN KEY (`id_taille_baguage`)
    REFERENCES `gsi16gn`.`taille_bagage` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkey_id_type_covoiturage`
    FOREIGN KEY (`id_type_covoiturage`)
    REFERENCES `gsi16gn`.`type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkey_id_utilisateur`
    FOREIGN KEY (`id_utilisateur`)
    REFERENCES `gsi16gn`.`utilisateur` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 129
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gsi16gn`.`passe_par`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gsi16gn`.`passe_par` ;

CREATE TABLE IF NOT EXISTS `gsi16gn`.`passe_par` (
  `id_covoiturage` INT(11) NOT NULL,
  `id_lieu` INT(11) NOT NULL,
  PRIMARY KEY (`id_covoiturage`, `id_lieu`),
  INDEX `fk_id_lieu_france_idx` (`id_lieu` ASC),
  CONSTRAINT `FK_id_covoiturage`
    FOREIGN KEY (`id_covoiturage`)
    REFERENCES `gsi16gn`.`covoiturage_france` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_lieu_france`
    FOREIGN KEY (`id_lieu`)
    REFERENCES `gsi16gn`.`lieu_france` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gsi16gn`.`sauvegarde`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gsi16gn`.`sauvegarde` ;

CREATE TABLE IF NOT EXISTS `gsi16gn`.`sauvegarde` (
  `id_covoiturage` INT(11) NOT NULL,
  `id_utilisateur` VARCHAR(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_covoiturage`, `id_utilisateur`),
  INDEX `fk_utilisateur_idx` (`id_utilisateur` ASC),
  CONSTRAINT `fk_utilisateur`
    FOREIGN KEY (`id_utilisateur`)
    REFERENCES `gsi16gn`.`utilisateur` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk`
    FOREIGN KEY (`id_covoiturage`)
    REFERENCES `gsi16gn`.`covoiturage_france` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
