-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: cdiofinalsem2.cibykspwyepg.us-east-2.rds.amazonaws.com    Database: cdio_final
-- ------------------------------------------------------
-- Server version	8.0.19

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `IngredientBatch`
--

DROP TABLE IF EXISTS `IngredientBatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `IngredientBatch` (
  `IngredientBatchID` int NOT NULL,
  `IngredientID` int NOT NULL,
  `IngredientAmount` decimal(8,4) NOT NULL,
  `IngredientSupplier` varchar(45) NOT NULL,
  PRIMARY KEY (`IngredientBatchID`),
  KEY `IngredientID_idx` (`IngredientID`),
  CONSTRAINT `IngredientID` FOREIGN KEY (`IngredientID`) REFERENCES `Ingredients` (`IngredientID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IngredientBatch`
--

LOCK TABLES `IngredientBatch` WRITE;
/*!40000 ALTER TABLE `IngredientBatch` DISABLE KEYS */;
INSERT INTO `IngredientBatch` VALUES (1,1,975.9588,'Egekilde'),(2,3,99.2000,'CitronAS'),(3,2,59.7801,'LæsøSalt'),(4,6,19.9959,'NovoNordisk'),(5,10,30.0001,'Smirnoff'),(6,9,10.4949,'NovoNordisk'),(7,4,249.9291,'Dansukker'),(8,5,35.0001,'PeterLarsen'),(9,7,22.9913,'LagerAS'),(10,8,45.5001,'MønINC'),(11,1,100.0001,'AquaDor');
/*!40000 ALTER TABLE `IngredientBatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ingredients`
--

DROP TABLE IF EXISTS `Ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ingredients` (
  `IngredientID` int NOT NULL,
  `IngredientName` varchar(45) NOT NULL,
  PRIMARY KEY (`IngredientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ingredients`
--

LOCK TABLES `Ingredients` WRITE;
/*!40000 ALTER TABLE `Ingredients` DISABLE KEYS */;
INSERT INTO `Ingredients` VALUES (1,'Vand'),(2,'Salt'),(3,'Citron'),(4,'Sukker'),(5,'Koffein'),(6,'Saltsyre'),(7,'Eddike'),(8,'Kalk'),(9,'Carboxylsyre'),(10,'Ethanol');
/*!40000 ALTER TABLE `Ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductBatch`
--

DROP TABLE IF EXISTS `ProductBatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProductBatch` (
  `ProductBatchID` int NOT NULL AUTO_INCREMENT,
  `RecipeID` int NOT NULL,
  `status` int NOT NULL,
  `ProduktionsLederID` int NOT NULL,
  `creationDate` date NOT NULL,
  `finishDate` date DEFAULT NULL,
  `taraSum` decimal(8,4) DEFAULT NULL,
  `nettoSum` decimal(8,4) DEFAULT NULL,
  PRIMARY KEY (`ProductBatchID`),
  KEY `UserID_idx` (`ProduktionsLederID`),
  KEY `RecipeID_idx` (`RecipeID`),
  CONSTRAINT `RecipeID` FOREIGN KEY (`RecipeID`) REFERENCES `Recipes` (`RecipeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `UserID` FOREIGN KEY (`ProduktionsLederID`) REFERENCES `Users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductBatch`
--

LOCK TABLES `ProductBatch` WRITE;
/*!40000 ALTER TABLE `ProductBatch` DISABLE KEYS */;
INSERT INTO `ProductBatch` VALUES (1,1,2,11,'2020-06-23','2020-06-23',1.5002,2.6700),(2,6,0,11,'2020-06-23',NULL,NULL,NULL),(3,3,2,11,'2020-06-23','2020-06-23',1.1002,1.0110),(4,2,0,11,'2020-06-23',NULL,NULL,NULL),(5,4,2,11,'2020-06-23','2020-06-23',4.8005,10.7662),(6,5,0,11,'2020-06-23',NULL,NULL,NULL),(7,4,1,3,'2020-06-23',NULL,NULL,NULL);
/*!40000 ALTER TABLE `ProductBatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductBatchComponents`
--

DROP TABLE IF EXISTS `ProductBatchComponents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProductBatchComponents` (
  `ProductBatchComponentsID` int NOT NULL AUTO_INCREMENT,
  `ProductBatchID` int DEFAULT NULL,
  `IngredientID` int DEFAULT NULL,
  `IngredientBatchID` int DEFAULT NULL,
  `LaborantID` int DEFAULT NULL,
  `tara` decimal(8,4) DEFAULT NULL,
  `netto` decimal(8,4) DEFAULT NULL,
  `terminal` int DEFAULT NULL,
  `amount` decimal(8,4) DEFAULT NULL,
  `tolerance` decimal(8,4) DEFAULT NULL,
  PRIMARY KEY (`ProductBatchComponentsID`),
  KEY `IngredientID_fk_idx` (`IngredientID`),
  KEY `ProductBatchID_fk_idx` (`ProductBatchID`),
  KEY `LaborantID_fk_idx` (`LaborantID`),
  KEY `IngredientBatchID_fk_idx` (`IngredientBatchID`),
  CONSTRAINT `IngredientBatchID_fk1` FOREIGN KEY (`IngredientBatchID`) REFERENCES `IngredientBatch` (`IngredientBatchID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `IngredientID_fk` FOREIGN KEY (`IngredientID`) REFERENCES `Ingredients` (`IngredientID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `LaborantID_fk` FOREIGN KEY (`LaborantID`) REFERENCES `Users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ProductBatchID_fk` FOREIGN KEY (`ProductBatchID`) REFERENCES `ProductBatch` (`ProductBatchID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductBatchComponents`
--

LOCK TABLES `ProductBatchComponents` WRITE;
/*!40000 ALTER TABLE `ProductBatchComponents` DISABLE KEYS */;
INSERT INTO `ProductBatchComponents` VALUES (1,1,1,1,9,1.0001,2.4500,1,2.4321,4.5401),(2,1,2,3,9,0.5001,0.2200,1,0.2391,12.4912),(3,2,3,NULL,NULL,NULL,NULL,NULL,0.1501,2.1001),(4,2,9,NULL,NULL,NULL,NULL,NULL,0.0051,6.0001),(5,2,10,NULL,NULL,NULL,NULL,NULL,0.5001,2.5001),(6,3,1,1,9,1.0001,0.9400,1,0.9301,5.0001),(7,3,4,7,9,0.1001,0.0710,1,0.0701,2.3001),(8,4,1,NULL,NULL,NULL,NULL,NULL,0.5001,4.5101),(9,4,3,NULL,NULL,NULL,NULL,NULL,0.0501,6.3001),(10,4,5,NULL,NULL,NULL,NULL,NULL,0.0051,10.0001),(11,5,1,1,14,1.0001,10.3500,1,10.3001,0.5001),(12,5,3,2,14,1.2001,0.4001,1,0.4001,2.3901),(13,5,6,4,14,0.2001,0.0021,1,0.0021,1.4001),(14,5,7,9,14,0.3001,0.0088,1,0.0091,9.0001),(15,5,9,6,14,2.1001,0.0052,1,0.0051,4.5001),(16,6,1,NULL,NULL,NULL,NULL,NULL,4.5001,2.0001),(17,7,1,1,13,5.2001,10.3013,1,10.3001,0.5001),(18,7,3,2,13,0.9001,0.4000,1,0.4001,2.3901),(19,7,6,4,5,1.0001,0.0021,1,0.0021,1.4001),(20,7,7,NULL,NULL,NULL,NULL,NULL,0.0091,9.0001),(21,7,9,NULL,NULL,NULL,NULL,NULL,0.0051,4.5001);
/*!40000 ALTER TABLE `ProductBatchComponents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RecipeComponents`
--

DROP TABLE IF EXISTS `RecipeComponents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RecipeComponents` (
  `RecipeID` int NOT NULL,
  `IngredientID` int NOT NULL,
  `nonNetto` decimal(8,4) NOT NULL,
  `tolerance` decimal(8,4) NOT NULL,
  PRIMARY KEY (`RecipeID`,`IngredientID`),
  KEY `IngredientID_idx` (`IngredientID`),
  CONSTRAINT `ingredientIDf` FOREIGN KEY (`IngredientID`) REFERENCES `Ingredients` (`IngredientID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `recipeIDf` FOREIGN KEY (`RecipeID`) REFERENCES `Recipes` (`RecipeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RecipeComponents`
--

LOCK TABLES `RecipeComponents` WRITE;
/*!40000 ALTER TABLE `RecipeComponents` DISABLE KEYS */;
INSERT INTO `RecipeComponents` VALUES (1,1,2.4321,4.5401),(1,2,0.2391,12.4912),(2,1,0.5001,4.5101),(2,3,0.0501,6.3001),(2,5,0.0051,10.0001),(3,1,0.9301,5.0001),(3,4,0.0701,2.3001),(4,1,10.3001,0.5001),(4,3,0.4001,2.3901),(4,6,0.0021,1.4001),(4,7,0.0091,9.0001),(4,9,0.0051,4.5001),(5,1,4.5001,2.0001),(6,3,0.1501,2.1001),(6,9,0.0051,6.0001),(6,10,0.5001,2.5001);
/*!40000 ALTER TABLE `RecipeComponents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Recipes`
--

DROP TABLE IF EXISTS `Recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Recipes` (
  `RecipeID` int NOT NULL,
  `recipeName` varchar(45) NOT NULL,
  PRIMARY KEY (`RecipeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recipes`
--

LOCK TABLES `Recipes` WRITE;
/*!40000 ALTER TABLE `Recipes` DISABLE KEYS */;
INSERT INTO `Recipes` VALUES (1,'Saltvand'),(2,'Citronvand'),(3,'Sukkervand'),(4,'Surtvand'),(5,'Kalkvand'),(6,'Citronalkohol');
/*!40000 ALTER TABLE `Recipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Firstname` varchar(45) NOT NULL,
  `Lastname` varchar(45) NOT NULL,
  `Initials` varchar(45) NOT NULL,
  `Role` varchar(45) NOT NULL,
  `isActive` tinyint NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'Pernille','Rosenkrantz','PR','Admin',1),(2,'Merete','Riisager','MR','Farmaceut',1),(3,'Ellen','Nørby','ETN','Produktionsleder',1),(4,'Christine','Antorini','CA','Admin',1),(5,'Troels','Poulsen','TLP','Laborant',1),(6,'Tina','Nedergaard','TN','Admin',1),(7,'Bertel','Haarder','BH','Farmaceut',1),(8,'Ulla','Tørnæs','UT','Produktionsleder',0),(9,'Magrethe','Vestager','MV','Laborant',1),(10,'Ole','Jensen','OVJ','Farmaceut',1),(11,'Dorte','Bennedsen','DB','Produktionsleder',1),(12,'Knud','Heinesen','KH','Laborant',0),(13,'Ritt','Bjerregaard','RB','Laborant',1),(14,'Helge','Larsen','HL','Laborant',1);
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-24 18:28:01
