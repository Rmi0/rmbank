-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: rmbank
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `number` int(10) NOT NULL,
  `customerid` int(11) DEFAULT NULL,
  `balance` double(13,2) DEFAULT NULL,
  `blocked` char(1) DEFAULT 'N',
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (224236232,2,1.00,'N'),(433493632,2,0.00,'N'),(676946534,1,0.00,'N'),(734778143,2,0.00,'N'),(734905787,2,0.00,'N'),(940505962,1,0.00,'N'),(1003267067,2,0.00,'N'),(1005419019,1,0.00,'N'),(1102299143,2,0.00,'N'),(1267170751,1,0.00,'N'),(1911779815,2,0.00,'N'),(2139999999,2,192.78,'N'),(2147483647,2,692.78,'N');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` bigint(16) DEFAULT NULL,
  `accnum` int(11) DEFAULT NULL,
  `blocked` char(1) DEFAULT 'N',
  `pin` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `active` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_2` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Peter','Hascash','Y'),(2,'Thomas','Withdraw','Y');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerdetails`
--

DROP TABLE IF EXISTS `customerdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customerdetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `city` varchar(20) NOT NULL,
  `address` varchar(25) NOT NULL,
  `dob` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerdetails`
--

LOCK TABLES `customerdetails` WRITE;
/*!40000 ALTER TABLE `customerdetails` DISABLE KEYS */;
INSERT INTO `customerdetails` VALUES (1,1,'Las Vegas','Grand Casino Street 23','1986-03-25'),(2,2,'Los Angeles','Imperial Highway 3','1992-07-12');
/*!40000 ALTER TABLE `customerdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`type`) REFERENCES `employeetype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Richard','Miscik',1),(2,'John','Cash',3),(3,'James','Foundjob',2);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeeaccount`
--

DROP TABLE IF EXISTS `employeeaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employeeaccount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empid` int(11) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `empid` (`empid`),
  CONSTRAINT `employeeaccount_ibfk_1` FOREIGN KEY (`empid`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeeaccount`
--

LOCK TABLES `employeeaccount` WRITE;
/*!40000 ALTER TABLE `employeeaccount` DISABLE KEYS */;
INSERT INTO `employeeaccount` VALUES (1,1,'admin','letmein'),(2,2,'john','letmein'),(3,3,'james','letmein');
/*!40000 ALTER TABLE `employeeaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeeloginhistory`
--

DROP TABLE IF EXISTS `employeeloginhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employeeloginhistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empid` int(11) DEFAULT NULL,
  `datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `empid` (`empid`),
  CONSTRAINT `employeeloginhistory_ibfk_1` FOREIGN KEY (`empid`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeeloginhistory`
--

LOCK TABLES `employeeloginhistory` WRITE;
/*!40000 ALTER TABLE `employeeloginhistory` DISABLE KEYS */;
INSERT INTO `employeeloginhistory` VALUES (147,1,'2017-04-21 19:08:59'),(148,1,'2017-04-21 19:10:45'),(149,1,'2017-05-05 17:41:37'),(150,1,'2017-05-05 17:42:35'),(151,1,'2017-05-05 17:52:28'),(152,1,'2017-05-05 17:53:26'),(153,1,'2017-05-05 17:56:52'),(154,1,'2017-05-05 17:57:55'),(155,1,'2017-05-05 18:00:26'),(156,1,'2017-05-05 18:05:05'),(157,1,'2017-05-05 18:43:14'),(158,1,'2017-05-05 18:43:40'),(159,1,'2017-05-05 18:44:06'),(160,1,'2017-05-05 18:45:10'),(161,1,'2017-05-05 18:47:21'),(162,1,'2017-05-05 18:49:44'),(163,1,'2017-05-05 18:51:53'),(164,1,'2017-05-05 18:54:00'),(165,1,'2017-05-05 18:57:23'),(166,1,'2017-05-12 18:49:06'),(167,1,'2017-05-12 19:07:12'),(168,1,'2017-05-12 19:08:03'),(169,1,'2017-05-12 19:08:21'),(170,1,'2017-05-12 19:13:06'),(171,1,'2017-05-12 19:14:26'),(172,1,'2017-05-12 19:14:44'),(173,1,'2017-05-12 19:16:39'),(174,1,'2017-05-12 19:18:56'),(175,1,'2017-05-12 19:19:29'),(176,1,'2017-05-12 19:23:25'),(177,1,'2017-05-12 19:25:50'),(178,1,'2017-05-12 19:27:09'),(179,1,'2017-05-12 19:31:02'),(180,1,'2017-05-12 19:31:44'),(181,1,'2017-05-12 19:32:14'),(182,1,'2017-05-12 19:32:59'),(183,1,'2017-05-12 19:33:12'),(184,1,'2017-05-12 19:33:41'),(185,1,'2017-05-12 19:33:59'),(186,1,'2017-05-12 19:34:41'),(187,1,'2017-05-12 19:42:06');
/*!40000 ALTER TABLE `employeeloginhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeetype`
--

DROP TABLE IF EXISTS `employeetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employeetype` (
  `id` int(11) NOT NULL,
  `name` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeetype`
--

LOCK TABLES `employeetype` WRITE;
/*!40000 ALTER TABLE `employeetype` DISABLE KEYS */;
INSERT INTO `employeetype` VALUES (1,'admin'),(2,'worker'),(3,'executive');
/*!40000 ALTER TABLE `employeetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expdate`
--

DROP TABLE IF EXISTS `expdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expdate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardid` int(11) DEFAULT NULL,
  `expmonth` int(2) DEFAULT NULL,
  `expyear` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expdate`
--

LOCK TABLES `expdate` WRITE;
/*!40000 ALTER TABLE `expdate` DISABLE KEYS */;
/*!40000 ALTER TABLE `expdate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-12 21:58:03
