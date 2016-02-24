-- MySQL dump 10.13  Distrib 5.5.37, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: emp_management
-- ------------------------------------------------------
-- Server version	5.5.37-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `emp_management`
--

/*!40000 DROP DATABASE IF EXISTS `emp_management`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `emp_management` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `emp_management`;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `client_id` varchar(45) NOT NULL,
  `client_name` varchar(45) NOT NULL,
  `client_mob1` varchar(45) DEFAULT NULL,
  `client_mob2` varchar(45) DEFAULT NULL,
  `client_add` varchar(255) DEFAULT NULL,
  `isactive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--


--
-- Table structure for table `client_emp`
--

DROP TABLE IF EXISTS `client_emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_emp` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `client_id` varchar(45) NOT NULL,
  `emp_id` varchar(45) NOT NULL,
  `start_dt` date NOT NULL,
  `end_dt` date DEFAULT NULL COMMENT 'completion_date',
  `remark` varchar(255) DEFAULT NULL COMMENT 'remark from client',
  `status` varchar(45) DEFAULT NULL COMMENT 'when employee complete his job',
  `job_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_emp`
--


--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emp` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `emp_id` varchar(45) NOT NULL,
  `f_name` varchar(45) NOT NULL,
  `m_name` varchar(45) DEFAULT NULL,
  `l_name` varchar(45) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `landmark` varchar(50) DEFAULT NULL,
  `way` varchar(50) DEFAULT NULL,
  `gurdian_name` varchar(45) DEFAULT NULL,
  `age` int(10) unsigned DEFAULT NULL,
  `post` varchar(45) DEFAULT NULL,
  `photo_id` varchar(255) DEFAULT NULL,
  `voter_id` varchar(255) DEFAULT NULL,
  `pan_id` varchar(255) DEFAULT NULL,
  `mob_no1` varchar(45) DEFAULT NULL,
  `mob_no2` varchar(45) DEFAULT NULL,
  `adminssion_dt` date DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  `Remark` varchar(45) DEFAULT NULL,
  `panchayet_id` varchar(255) DEFAULT NULL,
  `voter_id_back` varchar(255) DEFAULT NULL,
  `other` varchar(255) DEFAULT NULL,
  `permanent_add` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--


-- Dump completed on 2014-07-15 18:45:45
