CREATE DATABASE  IF NOT EXISTS `eshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `eshop`;
-- MySQL dump 10.13  Distrib 8.0.35, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: eshop
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cart_unique` (`user_id`,`product_id`),
  KEY `cart_products_FK` (`product_id`),
  CONSTRAINT `cart_products_FK` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `cart_users_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` mediumtext,
  `parent` int DEFAULT NULL,
  `slug` varchar(180) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (4,'Χρώματα-Βερνίκια','Τα πάντα',NULL,'paints-varnishes'),(5,'Εσωτερικού χώρου','Εσωτερικού χώρου',4,'indoor-space-paints'),(6,'Εξωτερικού χώρου','Εξωτερικού χώρου',4,'outdoor-space-paints'),(9,'Εξοπλισμός','Εξοπλισμός',NULL,'equipment'),(22,'Εργαλεία','Τα καλύτερα εργαλεία στις καλύτερες τιμές.',NULL,'tools'),(23,'Εργαλεία χειρός','Χειρονακτικά εργαλεία αρίστης ποιότητος',22,'hand-tools'),(24,'Εργαλεία λείανσης και κοπής','Όλα τα σύγχρονα εργαλεία για λείανση και για κοπή',22,'grinding-n-cutting-tools'),(25,'Ριπολίνες-Λαδομπογιές','Μπογιές εξαιρετικής ποιότητος στις καλύτερες τιμές της αγοράς',4,'ripolines-oil-paints'),(26,'Αστάρια','Υψηλής ποιότητος αστάρια με μεγάλη αντοχή στο χρόνο',4,'primers'),(27,'Κήπος','Τα πάντα για τον κήπο',NULL,'garden'),(28,'Λάστιχα ποτίσματος','Τα πάντα για το πότισμα',27,'lastixa-potismatos');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cc_types`
--

DROP TABLE IF EXISTS `cc_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cc_types` (
  `type_id` int NOT NULL,
  `type_name` varchar(100) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_types`
--

LOCK TABLES `cc_types` WRITE;
/*!40000 ALTER TABLE `cc_types` DISABLE KEYS */;
INSERT INTO `cc_types` VALUES (1,'Mastercard'),(2,'VISA'),(3,'American Express');
/*!40000 ALTER TABLE `cc_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_Id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(9,2) NOT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_items_orders_FK` (`order_Id`),
  KEY `order_items_products_FK` (`product_id`),
  CONSTRAINT `order_items_orders_FK` FOREIGN KEY (`order_Id`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_items_products_FK` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (2,9,21,22,8.30,NULL),(3,10,21,22,8.30,NULL),(4,10,22,1,54.60,NULL),(5,10,28,2,342.50,NULL),(6,11,21,22,8.30,NULL),(7,11,22,2,54.60,NULL),(8,11,28,2,342.50,NULL),(9,12,21,2,8.30,NULL),(10,12,22,2,54.60,NULL),(11,13,21,2,8.30,NULL),(12,13,22,2,54.60,NULL),(13,14,22,2,54.60,NULL),(14,14,26,1,6.00,NULL),(15,15,22,2,54.60,NULL),(16,15,26,2,6.00,NULL),(17,16,28,1,342.50,NULL),(18,17,21,1,8.30,NULL),(19,17,24,2,42.40,NULL),(20,17,26,2,6.00,NULL),(21,18,23,1,5.90,NULL),(22,18,37,5,22.01,NULL),(23,18,39,1,24.80,NULL);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `ccName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ccType` int DEFAULT NULL,
  `ccNumber` varchar(20) DEFAULT NULL,
  `cc_exp_month` int DEFAULT NULL,
  `cc_exp_year` int DEFAULT NULL,
  `ccCVC` int DEFAULT NULL,
  `is_self_pickup` tinyint(1) DEFAULT NULL,
  `street` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `zipCode` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `createdAt` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orders_cc_types_FK` (`ccType`),
  KEY `orders_users_FK` (`user_id`),
  CONSTRAINT `orders_cc_types_FK` FOREIGN KEY (`ccType`) REFERENCES `cc_types` (`type_id`),
  CONSTRAINT `orders_users_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (9,3,'Όνομα κατόχου',2,'1234123412341234',12,2025,123,NULL,'Οδός και αριθμός','Πόλη','Νομός','13121','Greece',NULL),(10,3,'Όνομα κατόχου',3,'1234123412341234',1,2025,123,NULL,'Οδός και αριθμός','Πόλη','Νομός','13121','Greece',NULL),(11,3,'Όνομα κατόχου',3,'1234123412341234',1,2029,123,NULL,'Οδός και αριθμός','Πόλη','Νομός','13121','Greece',NULL),(12,3,'Γιώργος Παντελιας',2,'1234123412341234',5,2025,123,NULL,'Κηφισού 35','Αθήνα','Αττικής','14242','Ελλάδα',NULL),(13,3,'Γιώργος Παντελιας',2,'1234123412341234',5,2025,123,NULL,'Κηφισού 35','Αθήνα','Αττικής','14242','Ελλάδα',NULL),(14,3,'Γιώργος Παντελιας',2,'1234123412341234',0,0,123,NULL,'Κηφισού 35','Αθήνα','Αττικής','14242','Ελλάδα',NULL),(15,3,'Γιώργος Παντελιας',2,'1234123412341234',5,2025,555,NULL,'Κηφισού 35','Αθήνα','Αττικής','14242','Ελλάδα',NULL),(16,3,'Γιώργος Παντελιας',2,'1234123412341234',5,2025,123,NULL,'Κηφισού 35','Αθήνα','Αττικής','14242','Ελλάδα',NULL),(17,3,'Γιώργος Παντελιας',2,'1234123412341234',5,2025,123,NULL,'Κηφισού 35','Αθήνα','Αττικής','14242','Ελλάδα',NULL),(18,3,'Γιώργος Ματσούκας',2,'1234123412341234',5,20226,123,NULL,'Κηφισού 35','Αθήνα','Αττικής','14242','Ελλάδα',NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` mediumtext,
  `slug` varchar(300) DEFAULT NULL,
  `category` int DEFAULT NULL,
  `picture` varchar(150) DEFAULT NULL,
  `price` decimal(9,2) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `stock` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `slug_UNIQUE` (`slug`),
  KEY `products_categories_FK` (`category`),
  CONSTRAINT `products_categories_FK` FOREIGN KEY (`category`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (21,'VIVECHROM SUPER NEOPAL - ΑΝΟΙΧΤΗ ΑΠΟΧΡΩΣΗ ΒΑΣΗ P','Το Super Neopal της Vivechrom είναι πλαστικό χρώμα κορυφαίας ποιότητας που διακρίνεται για τη μεγάλη του καλυπτικότητα και τις εξαιρετικές αντοχές του στο συχνό πλύσιμο. Δουλεύεται εύκολα, απλώνει θαυμάσια, στεγνώνει γρήγορα και έχει ισχυρή πρόσφυση. Το φωτεινό του λευκό και οι ζωηρές και αναλλοίωτες αποχρώσεις προσφέρουν ένα άψογο ματ φινίρισμα, δημιουργώντας όμορφους χώρους με ξεχωριστή προσωπικότητα. Το Super Neopal εφαρμόζεται σε σοβά, γυψοσανίδες, μπετόν, τούβλα, ξύλα. Δεν περιέχει αμμωνία. Δεν συνεισφέρει στην ανάπτυξη της φωτιάς και την εξάπλωση της φλόγας. Το Super Neopal διατίθεται σε λευκό  και σε απεριόριστο αριθμό αποχρώσεων μέσω του συστήματος ΧΡΩΜΟΣΥΝΘΕΣΕΙΣ της Vivechrom και σε συσκευασίες των 750ml, 3lt και 10lt. Αν δεν είστε βέβαιοι για ποιο κωδικό απόχρωσης πρέπει να επιλέξετε μην ανησυχείτε.','vivechrom-super-neopal-anoixti-apoxrosi-basi-p-325--936_1ltr',5,'vivechrom-super-neopal-anoixti-apoxrosi-basi-p.jpg',8.30,NULL,'VIVECHROM',30),(22,'VIVECHROM KITCHEN BATHROOM ECO - ΛΕΥΚΟ ΑΝΤΙΜΙΚΡΟΒΙΑΚΟ','Το NEOPAL KITCHEN & BATHROOM ECO είναι οικολογικό αντιμικροβιακό πλαστικό χρώμα κορυφαίας ποιότητας. Είναι πιστοποιημένο για την εξαιρετική αντιμικροβιακή του δράση. Περιέχει ειδικές μυκητοκτόνες ουσίες που προστατεύουν την επιφάνεια του φιλμ του χρώματος εμποδίζοντας δραστικά την ανάπτυξη μούχλας και βακτηρίων, σε χώρους με υψηλή υγρασία και υδρατμούς, όπως κουζίνες, μπάνια, ταβάνια, υπόγεια. Είναι ιδιαίτερα ανθεκτικό στο συχνό πλύσιμο και καθαρίζεται εύκολα. Έχει υψηλή καλυπτικότητα και άψογο ματ φινίρισμα. Δουλεύεται εύκολα, απλώνει θαυμάσια, στεγνώνει γρήγορα και έχει ισχυρή πρόσφυση. Δεν περιέχει αμμωνία. Δεν συνεισφέρει στην ανάπτυξη της φωτιάς και την εξάπλωση της φλόγας. Αν δεν είστε βέβαιοι για ποιο κωδικό απόχρωσης πρέπει να επιλέξετε μην ανησυχείτε.','vivechrom-kitchen-bathroom-57--198_75-ποσότητα-10ltr',5,'vivechrom-kitchen-bathroom.jpg',54.60,NULL,'VIVECHROM',44),(23,'VECHRO SMALTOPLAST EXTRA ΠΛΑΣΤΙΚΟ ΛΕΥΚΟ','Κορυφαίας ποιότητας ΟΙΚΟΛΟΓΙΚΟ ματ πλαστικό χρώμα για τη βαφή τοίχων, σοβάδων, σπατουλαριστών, γυψοσανίδων. Το SMALTOPLAST extra είναι χαμηλής οσμής και συνδυάζει υψηλή λευκότητα, μεγάλη καλυπτικότητα και εξαιρετική απόδοση. Απλώνει θαυμάσια, δουλεύεται εύκολα και δεν πιτσιλάει. Παρουσιάζει μεγάλες αντοχές στα αλκάλια και άριστη αντοχή στο συχνό πλύσιμο. Ελαχιστοποιημένη περιεκτικότητα σε επικίνδυνες ουσίες. Μειωμένη περιεκτικότητα σε πτητικές οργανικές ενώσεις. ','vechro-smaltoplast-extra-81--257_78-ποσότητα-0750_litra',5,'vechro-smaltoplast-extra.jpg',5.90,NULL,'VECHRO',4),(24,'VIVECHROM SUPER NEOPAL ECO - ΛΕΥΚΟ ΟΙΚΟΛΟΓΙΚΟ ΠΛΑΣΤΙΚΟ ΧΡΩΜΑ','Tο SUPER NEOPAL ECO είναι οικολογικό ματ πλαστικό χρώμα ασυναγώνιστης ποιότητας. Είναι πιστοποιημένο από το AFNOR και πληροί τα κριτήρια του οικολογικού σήματος Ecolabel της Ε.Ε. Δεν περιέχει αμμωνία ούτε επικίνδυνες ουσίες όπως βαρέα μέταλλα, ελεύθερη φορμαλδεΰδη, αρωματικούς υδρογονάνθρακες και είναι φιλικό προς το χρήστη και το περιβάλλον. Είναι απολύτως ασφαλές, συμβάλλει στην καλύτερη ποιότητα εσωτερικού αέρα (French VOC Regulation A+) και είναι ιδανικό για χώρους που κατοικούνται. Διακρίνεται για την ασύγκριτη καλυπτικότητα και απόδοση, την υψηλή του λευκότητα και τις αναλλοίωτες αποχρώσεις, και έχει εξαιρετική αντοχή στο συχνό πλύσιμο (Class 1, EN 13300). Εφαρμόζεται σε σοβά, μπετόν, τούβλα, ξύλα. Δουλεύεται εύκολα, έχει ισχυρή πρόσφυση και γρήγορο στέγνωμα. Απλώνει θαυμάσια και δημιουργεί εξαιρετικό μάτ φινίρισμα. Εμποδίζει την ανάπτυξη της φωτιάς και την εξάπλωση της φλόγας. ','vivechrom-neopal-eco-56--195_75-ποσότητα-10ltr',5,'vivechrom-neopal-eco.jpg',42.40,NULL,'VIVECHROM',77),(25,'COLORSTYLE SELECT CS 519 - ΛΕΥΚΟ ΠΛΑΣΤΙΚΟ ΒΕΛΟΥΤΕ','Υψηλής ποιότητας πλαστικό χρώμα. Δίνει ένα εξαιρετικό ομοιόμορφο βελουτέ τελείωμα και εφαρμόζεται σε κατάλληλα προετοιμασμένες σπατουλαριστές επιφάνειες, σοβά, γυψοσανίδες, μπετόν και ξύλο. Συνδιάζει αντοχή στα συχνά πλυσίματα, μεγάλη καλυπτικότητα, εύκολη εφαρμογή, γρήγορο στέγνωμα, με ειδική σύνθεση να μην πιτσιλάει. Ιδανικό να χρησιμοποιηθεί σε σχολεία, παιδικούς σταθμούς, νοσο- κομεία, γραφεία και σε επιφάνειες που λερώνονται πολύ.','colorstyle-select-cs-519-lefko-plastiko-beloute-334--966',5,'colorstyle-select-cs-519-lefko-plastiko-beloute.jpg',7.50,NULL,'COLORSTYLE',82),(26,'COLORSTYLE PRIMER CS 323 - ΑΝΤΙΜΟΥΧΛΙΚΟ ΥΠΟΣΤΡΩΜΑ','Υψηλής ποιότητας 100% ακρυλικό, αντιμουχλικό υπόστρωμα. Ειδικά σχεδιασμένο για κουζίνες και μπάνια και άλλες εσωτερικές επιφάνειες με προβλήματα μούχλας. Ιδανικό για καθαρές και σκληρές επιφάνειες. Έχει εξαιρετική πρόσφυση. Ειδική σύνθεση για να μην πιτσιλάει. Για καλύτερα αποτελέσματα συνιστάται η χρήση του σε συνδυασμό με το CS 322 Select Bath & Spa ακρυλικό αντιμουχλικό.','colorstyle-primer-cs-323-antimouxliko-ypostroma-195--495_78-ποσότητα-0750_litra',5,'colorstyle-primer-cs-323-antimouxliko-ypostroma.jpg',6.00,NULL,'COLORSTYLE',43),(27,'BOSCH GSR 120-LI ΔΡΑΠΑΝΟΚΑΤΣΑΒΙΔΟ 12V 2×2.0Ah','Επαναφορτιζόμενο κρουστικό δραπανοκατσάβιδο Bosch GSB 18V-21, με 2 μπαταρίες.','bosch-gsr-120-li-drapanokatsabido-12v-2x2-0ah-310--867-temaxio',22,'bosch-gsr-120-li-drapanokatsabido-12v-2x2-0ah.jpg',122.00,NULL,'BOSCH',0),(28,'BOSCH 0611267600 GBH 2-28 F ΠΙΣΤΟΛΕΤΟ 880W 3.2J+ΤΣΟΚ+ΚΑΣΕΤINA','Το GBH 2-28 F Professional με ταχυτσόκ είναι εργαλείο στην κατηγορία των περιστροφικών πιστολέτων ρεύματος SDS plus 2 κιλών της Bosch. Διαθέτει αντίστροφη λειτουργία, συμπλέκτη ελέγχου περιστροφής, μεταβλητή ταχύτητα και Vario-Lock.','bosch-0611267600-gbh-2-28-f-pistoleto-880w-3-2jtsokkasetina-312--869',22,'bosch-0611267600-gbh-2-28-f-pistoleto-880w-3-2jtsokkasetina.jpg',342.50,NULL,'BOSCH',5),(29,'BOSCH GSB 18-2-LI ΚΡΟΥΣΤΙΚΟ ΔΡΑΠΑΝΟΚΑΤΣΑΒΙΔΟ ΜΠΑΤΑΡΙΑΣ','Δραπανοκατσάβιδο Κρουστικό Bosch Pro Gsb 18-2 Li plus με 2 μπαταρίες 18V/2A H με κρουστική λειτουργιά και κιβώτιο 2 ταχυτήτων, και με το ενσωματωμένο φως led είναι το κατάλληλο εργαλείο για απαιτητικές εργασίες.','bosch-gsb-18-2-li-kroustiko-drapanokatsabido-batarias-311--868',22,'bosch-gsb-18-2-li-kroustiko-drapanokatsabido-batarias.jpg',215.00,NULL,'BOSCH',6),(30,'DEWALT ΓΩΝΙΑΚΟΣ ΤΡΟΧΟΣ 1500W ΡΥΘΜΙΖΟΜΕΝΟΣ','ΓΩΝΙΑΚΟΣ ΤΡΟΧΟΣ 1500W 125MM ΡΥΘΜΙΖΟΜΕΝΗ ΤΑΧΥΤΗΤΑ Ισχύς 1500W Ταχύτητα χωρίς φορτίο 2800- 10000rpm Μεγ. Διάμετρος Δίσκου 125 mm Σπείρωμα άξονα λείανσης M14 Βάρος 2.6 Kg Συμπεριλαμβάνεται: Ρυθμιζόμενος προφυλακτήρας χωρίς κλειδί Αντικραδασμική πλευρική λαβή πολλών θέσεων Κλειδί  ','dewalt-goniakos-troxos-1500w-rythmizomenos-345--980',22,'dewalt-goniakos-troxos-1500w-rythmizomenos.jpg',176.00,NULL,'DEWALT',4),(31,'DEWALT ΓΩΝΙΑΚΟΣ ΤΡΟΧΟΣ DWE4237','Γωνιακός τροχός ο οποίος διαθέτει κινητήρα με προστασία από τις τριβές για αυξημένη ανθεκτικότητα. Μικρή περιφέρεια επιτρέπει το άνετο πιάσιμο του εργαλείου και παρέχει εξαιρετική εργονομία. Κιβώτιο γραναζιών χαμηλού προφίλ επιτρέπει την πρόσβαση σε στενούς χώρους.  ','dewalt-goniakos-troxos-dwe4237-344--979',22,'dewalt-goniakos-troxos-dwe4237.jpg',152.00,NULL,'DEWALT',1),(32,'GWS 13-125 S CIE ΓΩΝΙΑΚΟΣ ΤΡΟΧΟΣ 060179F002','Τροχός ρεύματος με ρύθμιση στροφών. Διαθέτει μεγάλη διάρκεια ζωής χάρη στους κινητήρες με άμεση ψύξη κινητήρα, προστασία υπερφόρτωσης και μεγάλο χρόνο αντοχής των ψηκτρών. Πρωτοπόρος προστασία χρήστη χάρη στο KickBack Stop, στην προστασία από αθέλητη επανεκκίνηση, στον προφυλακτήρα με ασφάλεια περιστροφής και στη μείωση των κραδασμών. Βελτιστοποιημένη οδήγηση του αέρα και βελτιωμένη προστασία από τη σκόνη. Εύχρηστος χάρη στο λεπτό περίβλημα.','goniakos-troxos-gws-13-125-s-cie',22,'gws-13-125-s-cie-γωνιακος-τροχος-060179f002.jpg',167.40,NULL,'BOSCH',5),(33,'GWS 7-115 E ΓΩΝ. ΤΡΟΧΟΣ 700W 115mm 0601388203','Επαγγελματικός μικρός γωνιακός λειαντήρας με ένα μικρό μέγεθος λαβής από 176 mm προσφέρει άριστη εργονομία για εργασία με χαμηλή κόπωση','gws-7-115-e-goniakos',22,'gws-7-115-e-γων-τροχος-700w-115mm-0601388203-bosch.jpg',81.34,NULL,'BOSCH',5),(34,'GBH 2-26 F ΠΙΣΤΟΛΕΤΟ 830W 2.7J 06112A4000','Περιστροφικό πιστολέτο πολύπλευρης απόδοσης με ταχυτσόκ στην κατηγορία SDS plus','gbh-2-26-f-pistoleto',22,'gbh-2-26-f-πιστολετο-830w-27j-06112a4000.jpg',229.40,NULL,'BOSCH',4),(35,'BENMAN ΑΛΦΑΔΙ L12 ΔΥΟ ΜΑΤΙΑ','Κατασκευασμένο από αλουμίνιο πάχους 1,60mm','benman-alfadi-l12-dyo-matia-180--458',23,'benman-alfadi-l12-dyo-matia.jpg',11.50,NULL,'BENMAN',12),(36,'F.F. GROUP ΜΕΤΡΟΤΑΙΝΙΑ AUTO LOCK','Μηχανισμός αυτόματου κλειδώματος  (AUTO LOCK) που επιτρέπει στη λάμα να επεκταθεί ομαλά.','f-f-group-metrotainia-auto-lock-188--476',23,'f-f-group-metrotainia-auto-lock.jpg',8.00,NULL,'F.F. GROUP',4),(37,'ΣΕΤ ΜΟΝΩΜΕΝΩΝ ΚΑΤΣΑΒΙΔΙΩΝ VDE 6 ΤΕΜΑΧΙΩΝ PRO COMFORT 1000V','ΣΕΤ ΜΟΝΩΜΕΝΩΝ ΚΑΤΣΑΒΙΔΙΩΝ','irwin-set-monomenon-katsabidion-vde-6-temaxion-pro-comfort-139--382',23,'irwin-set-monomenon-katsabidion-vde-6-temaxion-pro-comfort.jpg',22.01,NULL,'IRWIN',5),(38,'ΣΕΤ ΕΙΔΙΚΩΝ ΚΑΤΣΑΒΙΔΙΩΝ','ΛΑΜΑ ΚΑΤΣΑΒΙΔΙΟΥ ΑΠΟ ΧΑΛΥΒΟΚΡΑΜΑ ΧΡΩΜΙΟΥ-ΒΑΝΑΔΙΟΥ','irwin-set-eidikon-katsabidion-140--383',23,'irwin-set-eidikon-katsabidion.jpg',11.40,NULL,'IRWIN',9),(39,'DEWALT ΣΠΑΤΟΥΛΕΣ ΣΤΟΚΟΥ ΜΕ ΗΛΕΚΤΡOKΟΚΟΛΛΗΜΕΝΗ ΛΑΒΗ','DeWalt EU2-405/06/08/10 SS σπάτουλα στόκου με ηλεκτρ. λαβή 5\'\',6\'\',8\'\',10\'\' με λάμα από 100% ανοξείδωτο ατσάλι, ηλεκτροκολλημένη  λαβή σε τρία σημεία και εργονομική λαβή για άνεση και προστασία.','dewalt-spatoula-stokou-144--389',23,'dewalt-spatoula-stokou.jpg',24.80,NULL,'DEWALT',6),(40,'ΒΕΝΜΑΝ ΛΑΣΤΙΧΟ ΠΟΤΙΣΜΑΤΟΣ HOBBY LINE 1/2\'\'','Benman λάστιχο ποτίσματος Hobby Line 25μ','benman-lastixo-potismatos-hobby-line-1-2-261--683',28,'benman-lastixo-potismatos-hobby-line-1-2.jpg',20.00,NULL,'Benman',6);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'george','Pandelias','pandeliasg@gmail.com','US','dsds','dsds','GR','dsdsd');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-16  4:59:25
