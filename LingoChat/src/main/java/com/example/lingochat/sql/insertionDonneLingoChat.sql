-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lingochat
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `forum`
--

INSERT INTO forum (id, utilisateur_id, description, nom) VALUES (1,1,'Description','Nom du Forum1');
INSERT INTO forum (id, utilisateur_id, description, nom) VALUES (2,2,'Description 2','Nom du forum 2');

--
-- Dumping data for table `langue`
--

INSERT INTO langue (id, nom, photo) VALUES (1,'Anglais','imgUSA.png');
INSERT INTO langue (id, nom, photo) VALUES (2,'Francais','imgFR.png');
INSERT INTO langue (id, nom, photo) VALUES (3,'Allemand','imgGermany.png');
INSERT INTO langue (id, nom, photo) VALUES (4,'Indien','imgIndia.png');
INSERT INTO langue (id, nom, photo) VALUES (5,'Coreen','imgKorea.png');
INSERT INTO langue (id, nom, photo) VALUES (6,'Russe','imgRussia.png');
INSERT INTO langue (id, nom, photo) VALUES (7,'Argentine','imgArgentina.png');
INSERT INTO langue (id, nom, photo) VALUES (8,'Japonais','imgJapan.png');
INSERT INTO langue (id, nom, photo) VALUES (9,'Mexico','imgMexico.png');
INSERT INTO langue (id, nom, photo) VALUES (10,'Espagne','imgSpain.png');

--
-- Dumping data for table `lien`
--

INSERT INTO lien (id, utilisateur_id, description, url) VALUES (1,2,'Ma chaine YT!','https://www.youtube.com/@bbcearth');
INSERT INTO lien (id, utilisateur_id, description, url) VALUES (2,2,'GitLab','https://git.dti.crosemont.quebec/');
INSERT INTO lien (id, utilisateur_id, description, url) VALUES (3,2,'CS50','https://learning.edx.org/course/course-v1:HarvardX+CS50+X/home');
INSERT INTO lien (id, utilisateur_id, description, url) VALUES (38,3,'Maximum de 4 liens','https://www.pinterest.ca/');
INSERT INTO lien (id, utilisateur_id, description, url) VALUES (40,2,'Angular','http://localhost:4200/');

--
-- Dumping data for table `post`
--

INSERT INTO post (forum_id, id, id_utilisateur, date, description, photo) VALUES (1,1,1,NULL,'Description post 1',NULL);
INSERT INTO post (forum_id, id, id_utilisateur, date, description, photo) VALUES (2,3,2,NULL,'Description post 3',NULL);
INSERT INTO post (forum_id, id, id_utilisateur, date, description, photo) VALUES (1,14,3,NULL,'test',NULL);
INSERT INTO post (forum_id, id, id_utilisateur, date, description, photo) VALUES (2,22,3,NULL,'test',NULL);

--
-- Dumping data for table `reference`
--

INSERT INTO reference (auteur_id, id, utilisateur_id, description) VALUES (2,1,1,'Reference de Leanna a Marie');
INSERT INTO reference (auteur_id, id, utilisateur_id, description) VALUES (3,2,1,'Reference de Marco a Marie');
INSERT INTO reference (auteur_id, id, utilisateur_id, description) VALUES (1,3,2,'Reference de Marie a Leanna');
INSERT INTO reference (auteur_id, id, utilisateur_id, description) VALUES (3,21,1,'Trop sympa');
INSERT INTO reference (auteur_id, id, utilisateur_id, description) VALUES (3,22,2,'Recommende +rep');

--
-- Dumping data for table `utilisateur`
--

INSERT INTO utilisateur (active, id, user_name, mot_de_passe, photo, status, courriel) VALUES (_binary '',1,'Marie :)','marie123','imgPersonne1.png','utilisateur','marie1@example.com');
INSERT INTO utilisateur (active, id, user_name, mot_de_passe, photo, status, courriel) VALUES (_binary '',2,'Leanna','leanna123','imgPersonne2.png','membreplus','leanna2@example.com');
INSERT INTO utilisateur (active, id, user_name, mot_de_passe, photo, status, courriel) VALUES (_binary '',3,'Marco','marco123','imgPersonne3.png','admin','marco3@example.com');
INSERT INTO utilisateur (active, id, user_name, mot_de_passe, photo, status, courriel) VALUES (_binary '',4,'Martin','martin123','imgPersonne4.png','utilisateur','martin4@example.com');

--
-- Dumping data for table `utilisateur_friend`
--

INSERT INTO utilisateur_friend (friend_id, user_id) VALUES (1,2);
INSERT INTO utilisateur_friend (friend_id, user_id) VALUES (3,2);
INSERT INTO utilisateur_friend (friend_id, user_id) VALUES (1,3);
INSERT INTO utilisateur_friend (friend_id, user_id) VALUES (2,3);

--
-- Dumping data for table `utilisateur_friend_favorite`
--

INSERT INTO utilisateur_friend_favorite (user_id, friend_id) VALUES (3,2);
INSERT INTO utilisateur_friend_favorite (user_id, friend_id) VALUES (2,3);

--
-- Dumping data for table `utilisateur_langue_apprise`
--

INSERT INTO utilisateur_langue_apprise (langue_id, utilisateur_id) VALUES (2,1);
INSERT INTO utilisateur_langue_apprise (langue_id, utilisateur_id) VALUES (6,1);
INSERT INTO utilisateur_langue_apprise (langue_id, utilisateur_id) VALUES (8,1);
INSERT INTO utilisateur_langue_apprise (langue_id, utilisateur_id) VALUES (4,2);
INSERT INTO utilisateur_langue_apprise (langue_id, utilisateur_id) VALUES (10,2);
INSERT INTO utilisateur_langue_apprise (langue_id, utilisateur_id) VALUES (3,3);

--
-- Dumping data for table `utilisateur_langue_parle`
--

INSERT INTO utilisateur_langue_parle (langue_id, utilisateur_id) VALUES (3,1);
INSERT INTO utilisateur_langue_parle (langue_id, utilisateur_id) VALUES (1,2);
INSERT INTO utilisateur_langue_parle (langue_id, utilisateur_id) VALUES (2,2);
INSERT INTO utilisateur_langue_parle (langue_id, utilisateur_id) VALUES (1,3);
INSERT INTO utilisateur_langue_parle (langue_id, utilisateur_id) VALUES (6,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
