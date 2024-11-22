-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `athlete-team`
--

DROP TABLE IF EXISTS `athlete-team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `athlete-team` (
  `athleteID` varchar(45) NOT NULL,
  `teamID` varchar(45) NOT NULL,
  `role` enum('Player', 'Captain', 'Assistant Captain') NOT NULL,
  PRIMARY KEY (`athleteID`,`teamID`),
  KEY `FK4_idx` (`teamID`),
  CONSTRAINT `FK1` FOREIGN KEY (`athleteID`) REFERENCES `athletes` (`athleteID`),
  CONSTRAINT `FK4` FOREIGN KEY (`teamID`) REFERENCES `teams` (`teamID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `athletes`
--

DROP TABLE IF EXISTS `athletes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `athletes` (
  `athleteID` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `middleInitial` char(1) DEFAULT NULL,
  `birthday` date NOT NULL,
  `gender` enum('M','F') DEFAULT NULL,
  PRIMARY KEY (`athleteID`),
  UNIQUE KEY `athleteID_UNIQUE` (`athleteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `performance`
--

DROP TABLE IF EXISTS `performance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performance` (
  `performanceID` varchar(45) NOT NULL,
  `athleteID` varchar(45) NOT NULL,
  `role` enum('Player', 'Captain', 'Assistant Captain') NOT NULL,
  `performanceDate` date NOT NULL,
  `performanceScore` int NOT NULL,
  PRIMARY KEY (`performanceID`),
  KEY `FK_athleteID_idx` (`athleteID`),
  CONSTRAINT `FK_athleteID` FOREIGN KEY (`athleteID`) REFERENCES `athletes` (`athleteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `borrowrecords`
--

DROP TABLE IF EXISTS `borrowrecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowrecords` (
  `borrowID` varchar(45) NOT NULL,
  `resourceID` varchar(45) NOT NULL,
  `BorrowDate` date NOT NULL,
  `ReturnDate` date NOT NULL,
  `coachID` varchar(45) NOT NULL,
  PRIMARY KEY (`borrowID`),
  UNIQUE KEY `borrowID_UNIQUE` (`borrowID`),
  KEY `FK21_idx` (`resourceID`),
  KEY `FK22_idx` (`coachID`),
  CONSTRAINT `FK21` FOREIGN KEY (`resourceID`) REFERENCES `resources` (`resourceID`),
  CONSTRAINT `FK22` FOREIGN KEY (`coachID`) REFERENCES `coaches` (`coachID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `coach_job_history`
--

DROP TABLE IF EXISTS `coach_job_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coach_job_history` (
  `coachID` varchar(45) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `teamID` varchar(45) NOT NULL,
  `role` enum('Head Coach','Assistant Coach') NOT NULL,
  PRIMARY KEY (`coachID`,`startDate`),
  UNIQUE KEY `coachID_UNIQUE` (`coachID`),
  KEY `FK2_idx` (`teamID`),
  CONSTRAINT `FK2` FOREIGN KEY (`teamID`) REFERENCES `teams` (`teamID`),
  CONSTRAINT `FK3` FOREIGN KEY (`coachID`) REFERENCES `coaches` (`coachID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `coaches`
--

DROP TABLE IF EXISTS `coaches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coaches` (
  `coachID` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `middleInitial` char(1) DEFAULT NULL,
  `birthday` date NOT NULL,
  `gender` enum('M','F') NOT NULL,
  `status` enum('active','inactive','suspended') NOT NULL,
  PRIMARY KEY (`coachID`),
  UNIQUE KEY `coachID_UNIQUE` (`coachID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resources` (
  `resourceID` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `status` enum('Damaged','Borrowed','Returned') NOT NULL,
  PRIMARY KEY (`resourceID`),
  UNIQUE KEY `resourceID_UNIQUE` (`resourceID`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teams` (
  `teamID` varchar(45) NOT NULL,
  `teamName` varchar(45) NOT NULL,
  `sport` varchar(45) NOT NULL,
  PRIMARY KEY (`teamID`),
  UNIQUE KEY `teamID_UNIQUE` (`teamID`),
  UNIQUE KEY `teamName_UNIQUE` (`teamName`),
  UNIQUE KEY `sport_UNIQUE` (`sport`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `venuereservation`
--

DROP TABLE IF EXISTS `venuereservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venuereservation` (
  `reserveID` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `venueID` varchar(45) NOT NULL,
  `startTime` int NOT NULL,
  `endTime` int NOT NULL,
  `coachID` varchar(45) NOT NULL,
  PRIMARY KEY (`reserveID`),
  UNIQUE KEY `reserveID_UNIQUE` (`reserveID`),
  KEY `FKV_idx` (`venueID`),
  KEY `FKC_idx` (`coachID`),
  CONSTRAINT `FKC` FOREIGN KEY (`coachID`) REFERENCES `coaches` (`coachID`),
  CONSTRAINT `FKV` FOREIGN KEY (`venueID`) REFERENCES `venues` (`venueID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `venues`
--

DROP TABLE IF EXISTS `venues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venues` (
  `venueID` varchar(45) NOT NULL,
  `venueName` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  PRIMARY KEY (`venueID`),
  UNIQUE KEY `venueID_UNIQUE` (`venueID`),
  UNIQUE KEY `venueName_UNIQUE` (`venueName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dump completed on 2024-11-22 22:05:19
