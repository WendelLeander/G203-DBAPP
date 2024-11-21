-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema default_schema
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`athlete`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`athlete` ;

CREATE TABLE IF NOT EXISTS `mydb`.`athlete` (
  `athleteID` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `middleInitial` CHAR(1) NULL DEFAULT NULL,
  `age` INT NOT NULL,
  `gender` ENUM('Male', 'Female') NULL DEFAULT NULL,
  PRIMARY KEY (`athleteID`),
  UNIQUE INDEX `athleteID_UNIQUE` (`athleteID` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`teams`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`teams` ;

CREATE TABLE IF NOT EXISTS `mydb`.`teams` (
  `teamID` VARCHAR(45) NOT NULL,
  `teamName` VARCHAR(45) NOT NULL,
  `sport` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`teamID`),
  UNIQUE INDEX `teamID_UNIQUE` (`teamID` ASC) VISIBLE,
  UNIQUE INDEX `teamName_UNIQUE` (`teamName` ASC) VISIBLE,
  UNIQUE INDEX `sport_UNIQUE` (`sport` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`athlete-team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`athlete-team` ;

CREATE TABLE IF NOT EXISTS `mydb`.`athlete-team` (
  `athleteID` VARCHAR(45) NOT NULL,
  `teamID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`athleteID`, `teamID`),
  INDEX `FK4_idx` (`teamID` ASC) VISIBLE,
  CONSTRAINT `FK1`
    FOREIGN KEY (`athleteID`)
    REFERENCES `mydb`.`athlete` (`athleteID`),
  CONSTRAINT `FK4`
    FOREIGN KEY (`teamID`)
    REFERENCES `mydb`.`teams` (`teamID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`resources`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`resources` ;

CREATE TABLE IF NOT EXISTS `mydb`.`resources` (
  `resourceID` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `status` ENUM('Damaged', 'Borrowed', 'Returned') NOT NULL,
  PRIMARY KEY (`resourceID`),
  UNIQUE INDEX `resourceID_UNIQUE` (`resourceID` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`coach`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`coach` ;

CREATE TABLE IF NOT EXISTS `mydb`.`coach` (
  `coachID` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `middleInitial` CHAR(1) NULL DEFAULT NULL,
  `age` INT NOT NULL,
  `gender` ENUM('Male', 'Female') NOT NULL,
  `hireDATE` DATE NOT NULL,
  PRIMARY KEY (`coachID`),
  UNIQUE INDEX `coachID_UNIQUE` (`coachID` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`borrowrecord`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`borrowrecord` ;

CREATE TABLE IF NOT EXISTS `mydb`.`borrowrecord` (
  `borrowID` VARCHAR(45) NOT NULL,
  `resourceID` VARCHAR(45) NOT NULL,
  `BorrowDate` DATE NOT NULL,
  `ReturnDate` DATE NOT NULL,
  `coachID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`borrowID`),
  UNIQUE INDEX `borrowID_UNIQUE` (`borrowID` ASC) VISIBLE,
  INDEX `FK21_idx` (`resourceID` ASC) VISIBLE,
  INDEX `FK22_idx` (`coachID` ASC) VISIBLE,
  CONSTRAINT `FK21`
    FOREIGN KEY (`resourceID`)
    REFERENCES `mydb`.`resources` (`resourceID`),
  CONSTRAINT `FK22`
    FOREIGN KEY (`coachID`)
    REFERENCES `mydb`.`coach` (`coachID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`coach_job_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`coach_job_history` ;

CREATE TABLE IF NOT EXISTS `mydb`.`coach_job_history` (
  `coachID` VARCHAR(45) NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  `teamID` VARCHAR(45) NOT NULL,
  `role` ENUM('Head Coach', 'Assistant Coach') NOT NULL,
  PRIMARY KEY (`coachID`, `startDate`),
  UNIQUE INDEX `coachID_UNIQUE` (`coachID` ASC) VISIBLE,
  INDEX `FK2_idx` (`teamID` ASC) VISIBLE,
  CONSTRAINT `FK2`
    FOREIGN KEY (`teamID`)
    REFERENCES `mydb`.`teams` (`teamID`),
  CONSTRAINT `FK3`
    FOREIGN KEY (`coachID`)
    REFERENCES `mydb`.`coach` (`coachID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`venues`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`venues` ;

CREATE TABLE IF NOT EXISTS `mydb`.`venues` (
  `venueID` VARCHAR(45) NOT NULL,
  `venueName` VARCHAR(45) NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`venueID`),
  UNIQUE INDEX `venueID_UNIQUE` (`venueID` ASC) VISIBLE,
  UNIQUE INDEX `venueName_UNIQUE` (`venueName` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`venuereservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`venuereservation` ;

CREATE TABLE IF NOT EXISTS `mydb`.`venuereservation` (
  `reserveID` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `venueID` VARCHAR(45) NOT NULL,
  `startTime` INT NOT NULL,
  `endTime` INT NOT NULL,
  `coachID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`reserveID`),
  UNIQUE INDEX `reserveID_UNIQUE` (`reserveID` ASC) VISIBLE,
  INDEX `FKV_idx` (`venueID` ASC) VISIBLE,
  INDEX `FKC_idx` (`coachID` ASC) VISIBLE,
  CONSTRAINT `FKC`
    FOREIGN KEY (`coachID`)
    REFERENCES `mydb`.`coach` (`coachID`),
  CONSTRAINT `FKV`
    FOREIGN KEY (`venueID`)
    REFERENCES `mydb`.`venues` (`venueID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

USE `mydb`;

DELIMITER $$

USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`venuereservation_BEFORE_UPDATE` $$
USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`venuereservation_BEFORE_UPDATE`
BEFORE UPDATE ON `mydb`.`venuereservation`
FOR EACH ROW
BEGIN
DECLARE overlap_count INT;

    -- Check for overlapping reservations
    SELECT COUNT(*) INTO overlap_count
    FROM VenueReservation
    WHERE VenueID = NEW.VenueID
      AND Date = NEW.Date
      AND (
          (NEW.startTime < endTime AND NEW.endTime > startTime)
      );

    -- If overlap_count > 0, raise an error
    IF overlap_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Overlapping reservation detected.';
    END IF;
END$$


USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`venuereservation_BEFORE_INSERT` $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`venuereservation_BEFORE_INSERT` BEFORE INSERT ON `venuereservation` FOR EACH ROW
BEGIN
DECLARE overlap_count INT;

    -- Check for overlapping reservations
    SELECT COUNT(*) INTO overlap_count
    FROM VenueReservation
    WHERE VenueID = NEW.VenueID
      AND Date = NEW.Date
      AND (
          (NEW.startTime < endTime AND NEW.endTime > startTime)
      );

    -- If overlap_count > 0, raise an error
    IF overlap_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Overlapping reservation detected.';
    END IF;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
