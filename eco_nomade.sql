-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 24 fév. 2025 à 15:45
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `eco_nomade`
--

-- --------------------------------------------------------

--
-- Structure de la table `consommation`
--

DROP TABLE IF EXISTS `consommation`;
CREATE TABLE IF NOT EXISTS `consommation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `co2` double DEFAULT NULL,
  `date_arrivee` varchar(255) DEFAULT NULL,
  `date_depart` varchar(255) DEFAULT NULL,
  `montant` double NOT NULL,
  `plat_type` varchar(255) DEFAULT NULL,
  `restaurant_type` varchar(255) DEFAULT NULL,
  `transport_type` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `ville` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `consommation`
--

INSERT INTO `consommation` (`id`, `co2`, `date_arrivee`, `date_depart`, `montant`, `plat_type`, `restaurant_type`, `transport_type`, `type`, `ville`) VALUES
(1, NULL, '', '', 30, NULL, NULL, NULL, 'restauration', NULL),
(2, NULL, '', '', 30, NULL, NULL, NULL, 'restauration', NULL),
(3, NULL, '2025-02-28', '2025-02-23', 0, NULL, NULL, 'Train', 'transport', NULL),
(4, NULL, '2025-02-28', '2025-02-23', 0, NULL, NULL, 'Train', 'transport', NULL),
(5, NULL, '2025-02-28', '2025-02-23', 0, NULL, NULL, 'Train', 'transport', NULL),
(6, NULL, '2025-02-21', '2025-02-22', 0, NULL, NULL, 'Train', 'transport', NULL),
(7, NULL, '2025-04-04', '2025-03-15', 78, NULL, NULL, 'Train', 'transport', NULL),
(8, NULL, '2025-02-27', '2025-02-22', 89, NULL, NULL, 'Train', 'transport', NULL),
(9, NULL, '2025-02-19', '2025-02-27', 45, NULL, NULL, 'Train', 'transport', NULL),
(10, NULL, '2025-02-19', '2025-02-21', 89, NULL, NULL, 'Train', 'transport', NULL),
(11, NULL, '2025-02-19', '2025-02-21', 89, NULL, NULL, 'Train', 'transport', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `eco_action`
--

DROP TABLE IF EXISTS `eco_action`;
CREATE TABLE IF NOT EXISTS `eco_action` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `date_action` datetime NOT NULL,
  `id_user` bigint NOT NULL,
  `co2saved` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `eco_action`
--

INSERT INTO `eco_action` (`id`, `description`, `date_action`, `id_user`, `co2saved`) VALUES
(1, 'Éteindre la lumière en quittant une pièce', '2025-02-23 08:00:00', 1, 0),
(2, 'Prendre le bus au lieu de la voiture', '2025-02-23 08:30:00', 2, 10),
(3, 'Réduire la température de chauffage de 1°C', '2025-02-23 09:00:00', 1, 15),
(4, 'Utiliser des sacs réutilisables', '2025-02-23 10:00:00', 2, 10),
(6, 'Actions contre la faim', '2025-02-24 14:05:34', 7, 67),
(7, 'Tri poubelle', '2025-02-24 15:29:01', 8, 17),
(11, 'planter un arbre', '2025-02-24 16:15:30', 8, 20),
(12, 'prendre le bus', '2025-02-24 16:16:42', 8, 23);

-- --------------------------------------------------------

--
-- Structure de la table `hebergement`
--

DROP TABLE IF EXISTS `hebergement`;
CREATE TABLE IF NOT EXISTS `hebergement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `conditions_reservation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `equipement` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `prix` decimal(10,2) DEFAULT NULL,
  `tauxco2` decimal(10,2) DEFAULT NULL,
  `type_hebergement` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id_ville` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Hebergement_Ville` (`id_ville`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `hebergement`
--

INSERT INTO `hebergement` (`id`, `conditions_reservation`, `description`, `equipement`, `photo`, `prix`, `tauxco2`, `type_hebergement`, `id_ville`) VALUES
(1, 'Annulation gratuite jusqu\'à 24h avant l\'arrivée', 'Appartement moderne avec vue sur la ville, proche des commodités.', 'Wi-Fi, Climatisation, Cuisine équipée, Balcon', 'hebergements/hotel/3', 120.00, 2.50, 'Appartement', 1),
(2, 'Annulation gratuite jusqu\'à 7 jours avant l\'arrivée', 'Chalet spacieux en montagne, idéal pour les familles.', 'Cheminée, Parking gratuit, Proche des pistes', 'hebergements/appartement/1', 250.00, 5.20, 'Chalet', 3),
(3, 'Non remboursable', 'Chambre d\'hôtel confortable avec petit déjeuner inclus.', 'Wi-Fi, Télévision, Room service', 'hebergements/hotel/1', 90.00, 3.10, 'Hôtel', 2),
(4, 'Annulation gratuite jusqu\'à 14 jours avant l\'arrivée', 'Villa luxueuse avec piscine privée et grand jardin.', 'Piscine, Wi-Fi, Climatisation, Barbecue', 'hebergements/appartement/3', 450.00, 8.00, 'Villa', 5),
(6, 'Annulation gratuite jusqu\'à 48h avant l\'arrivée', 'Appartement avec deux chambres dans le centre historique.', 'Wi-Fi, Cuisine équipée, Machine à laver', 'hebergements/appartement/5', 140.00, 2.30, 'Appartement', 1),
(7, 'Non remboursable', 'Suite dans un hôtel 5 étoiles avec spa et restaurant gastronomique.', 'Wi-Fi, Piscine, Service en chambre 24/7', 'hebergements/hotel/2', 200.00, 4.00, 'Hôtel', 1),
(8, 'Annulation gratuite jusqu\'à 5 jours avant l\'arrivée', 'Riad traditionnel au cœur de la médina avec patio intérieur.', 'Wi-Fi, Climatisation, Petit déjeuner inclus', 'hebergements/appartement/3', 180.00, 3.50, 'Villa', 5),
(10, 'Annulation gratuite jusqu\'à 10 jours avant l\'arrivée', 'Chalet moderne avec sauna et vue panoramique sur les montagnes.', 'Sauna, Parking, Proche des pistes', 'hebergements/appartement/7', 300.00, 6.00, 'Chalet', 5),
(11, 'Non remboursable', 'Bungalow en bord de plage avec accès direct à la mer.', 'Climatisation, Wi-Fi, Parking gratuit', 'hebergements/hotel/6', 100.00, 1.50, 'Hôtel', 5),
(13, 'Non-fumeur', 'Appartement lumineux avec balcon et vue sur la ville', 'Wi-Fi, Climatisation, Cuisine équipée', 'hebergements/appartement/8', 150.00, 12.00, 'Appartement', 2),
(14, 'Animaux acceptés', 'Maison isolée avec grand jardin et cheminée', 'Wi-Fi, Parking, Cuisine équipée', 'hebergements/appartement/9', 200.00, 20.00, 'Villa', 4),
(16, 'Non-fumeur', 'Villa privée avec piscine et salle de sport', 'Piscine, Jacuzzi, Cuisine haut de gamme', 'hebergements/appartement/10', 800.00, 50.00, 'Villa', 2),
(17, 'Non-fumeur', 'Chalet chaleureux près des stations de ski', 'Cheminée, Parking, Sauna', 'hebergements/appartement/11', 250.00, 25.00, 'Chalet', 4),
(18, 'Animaux acceptés', 'Bungalow cosy avec accès direct à la plage', 'Terrasse, Wi-Fi, Climatisation', 'hebergements/hotel/8', 180.00, 22.00, 'Hôtel', 5),
(19, 'Non-fumeur', 'Studio compact avec toutes les commodités modernes', 'Wi-Fi, Cuisine équipée, Climatisation', 'hebergements/auberge/3', 120.00, 14.00, 'Appartement', 8),
(20, 'Non-fumeur', 'Riad authentique avec patio traditionnel', 'Climatisation, Hammam, Petit-déjeuner inclus', 'hebergements/auberge/4', 220.00, 20.00, 'Villa', 3),
(21, 'Animaux acceptés', 'Yourte avec vue panoramique, idéale pour un séjour nature', 'Lit double, Poêle à bois, Activités en plein air', 'hebergements/auberge/5', 90.00, 8.00, 'Villa', 3),
(22, 'Non-fumeur', 'Cabane dans les arbres avec terrasse et vue exceptionnelle', 'Wi-Fi, Petit-déjeuner inclus, Proche des sentiers', 'hebergements/auberge/6', 170.00, 18.00, 'Villa', 7),
(23, 'Non-fumeur', 'Penthouse luxueux avec vue imprenable sur la ville', 'Terrasse, Jacuzzi, Wi-Fi haut débit', 'hebergements/auberge/7', 400.00, 15.00, 'Appartement', 6),
(24, 'Animaux acceptés', 'Maison conviviale dans un quartier calme', 'Cuisine équipée, Wi-Fi, Parking privé', 'hebergements/hotel/9', 140.00, 13.00, 'Hôtel', 1),
(25, 'Non-fumeur', 'Tiny House éco-conçue au cœur de la nature', 'Cuisine compacte, Terrasse, Poêle à bois', 'hebergements/hotel/10', 100.00, 7.00, 'Hôtel', 7),
(26, 'Non-fumeur', 'Maison moderne avec grandes baies vitrées', 'Piscine, Parking, Climatisation', 'hebergements/hotel/11', 300.00, 20.00, 'Hôtel', 7),
(27, 'Non-fumeur', 'Studio avec accès direct à la plage', 'Cuisine équipée, Terrasse, Climatisation', 'hebergements/hotel/12', 110.00, 9.00, 'Hôtel', 7),
(28, 'Non-fumeur', 'Hôtel design avec services personnalisés', 'Wi-Fi, Bar, Service en chambre', 'hebergements/hotel/13', 250.00, 16.00, 'Hôtel', NULL),
(29, 'Animaux acceptés', 'Gîte familial dans une ferme rénovée', 'Wi-Fi, Parking, Activités pour enfants', 'hebergements/hotel/14', 180.00, 22.00, 'Hôtel', 7),
(30, 'Non-fumeur', 'Loft spacieux avec une décoration industrielle', 'Wi-Fi, Cuisine équipée, Climatisation', 'hebergements/hotel/15', 300.00, 18.00, 'Appartement', 7),
(31, 'Non-fumeur', 'Villa avec piscine et vue sur la mer Méditerranée', 'Piscine, Jardin, Parking privé', 'hebergements/villa/1', 450.00, 30.00, 'Villa', 7),
(32, 'Animaux acceptés', 'Maison en pierre avec charme authentique', 'Cheminée, Wi-Fi, Proche des sentiers de randonnée', 'hebergements/villa/2', 130.00, 14.00, 'Villa', 7),
(33, 'Non-fumeur', 'Riad avec piscine intérieure et service haut de gamme', 'Hammam, Wi-Fi, Service en chambre', 'hebergements/villa/3', 500.00, 40.00, 'Villa', 7),
(34, 'Animaux non acceptés', 'Cabane sur l\'eau avec vue panoramique', 'Kayak inclus, Terrasse, Cuisine équipée', 'hebergements/villa/4', 200.00, 25.00, 'Villa', 7),
(35, 'Non-fumeur', 'Chambre majestueuse dans un château historique', 'Petit-déjeuner, Wi-Fi, Visite du château', 'hebergements/hotel/16', 350.00, 20.00, 'Hôtel', 7),
(36, 'Animaux acceptés', 'Appartement spacieux pour les familles', 'Wi-Fi, Cuisine équipée, Parking gratuit', 'hebergements/appartement/12', 150.00, 10.00, 'Appartement', 1),
(37, 'Non-fumeur', 'Maison avec terrasse et accès direct au lac', 'Canoë inclus, Wi-Fi, Barbecue', 'hebergements/appartement/13', 300.00, 30.00, 'Villa', 2),
(38, 'Animaux acceptés', 'Bungalow écologique au cœur de la jungle', 'Ventilateur, Terrasse, Cuisine équipée', 'hebergements/appartement/14', 100.00, 20.00, 'Villa', 3),
(39, 'Non-fumeur', 'Chalet avec vue imprenable sur le lac', 'Cheminée, Wi-Fi, Activités nautiques', 'hebergements/appartement/15', 250.00, 18.00, 'Chalet', 4),
(40, 'Non-fumeur', 'Studio pratique pour un séjour en ville', 'Climatisation, Wi-Fi, Cuisine équipée', 'hebergements/appartement/16', 80.00, 12.00, 'Appartement', 5),
(41, 'Animaux acceptés', 'Villa entourée de palmiers avec piscine privée', 'Piscine, Wi-Fi, Parking', 'hebergements/villa/5', 600.00, 50.00, 'Villa', 6),
(42, 'Non-fumeur', 'Maison en centre-ville avec terrasse fleurie', 'Cuisine équipée, Wi-Fi, Climatisation', 'hebergements/appartement/17', 200.00, 15.00, 'Appartement', 7);

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

DROP TABLE IF EXISTS `pays`;
CREATE TABLE IF NOT EXISTS `pays` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fair_score` decimal(5,2) NOT NULL,
  `nom` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`id`, `fair_score`, `nom`) VALUES
(1, 5.50, 'France'),
(2, 4.30, 'Espagne'),
(3, 4.80, 'Italie'),
(4, 5.00, 'Allemagne'),
(5, 3.90, 'Portugal'),
(6, 4.10, 'Belgique'),
(7, 4.20, 'Pays-Bas'),
(8, 4.70, 'Royaume-Uni');

-- --------------------------------------------------------

--
-- Structure de la table `restauration`
--

DROP TABLE IF EXISTS `restauration`;
CREATE TABLE IF NOT EXISTS `restauration` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `prix_moyenne` decimal(10,2) NOT NULL,
  `tauxco2` decimal(10,2) DEFAULT NULL,
  `type_restaurant` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom_profil` varchar(50) NOT NULL,
  `id_user` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8qjyc7wgcql5ngo85w7d61k9y` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `transport`
--

DROP TABLE IF EXISTS `transport`;
CREATE TABLE IF NOT EXISTS `transport` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estimation_prix` double NOT NULL,
  `tauxco2` double NOT NULL,
  `type_transport` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ville_depart` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ville_destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `date_depart` date DEFAULT NULL,
  `date_retour` date DEFAULT NULL,
  `duree` time(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `transport`
--

INSERT INTO `transport` (`id`, `estimation_prix`, `tauxco2`, `type_transport`, `ville_depart`, `ville_destination`, `date_depart`, `date_retour`, `duree`) VALUES
(1, 50, 14, 'Train grande vitesse', 'Paris', 'Lyon', '2024-12-25', '2024-12-30', '02:30:00.000000'),
(2, 25, 30, 'Train régional', 'Paris', 'Lyon', '2024-12-25', '2024-12-30', '03:00:00.000000'),
(3, 100, 250, 'Vol domestique', 'Paris', 'Lyon', '2024-12-25', '2024-12-30', '01:15:00.000000'),
(4, 600, 500, 'Vol international', 'Paris', 'New York', '2024-12-20', '2025-01-10', '08:00:00.000000'),
(5, 15, 45, 'Bus longue distance', 'Paris', 'Berlin', '2024-12-21', '2024-12-26', '10:00:00.000000'),
(6, 2, 30, 'Bus urbain', 'Paris', 'Paris', '2024-12-20', '2024-12-20', '00:20:00.000000'),
(7, 1.5, 20, 'Métro', 'Paris', 'Paris', '2024-12-20', '2024-12-20', '00:15:00.000000'),
(8, 1.8, 15, 'Tramway', 'Paris', 'Paris', '2024-12-20', '2024-12-20', '00:25:00.000000'),
(9, 25, 180, 'Taxi', 'Paris', 'Lyon', '2024-12-25', '2024-12-25', '04:00:00.000000'),
(10, 20, 120, 'Covoiturage', 'Paris', 'Marseille', '2024-12-22', '2024-12-29', '06:00:00.000000'),
(11, 70, 35, 'Train de nuit', 'Paris', 'Nice', '2024-12-23', '2024-12-26', '12:00:00.000000'),
(12, 50, 150, 'Ferry', 'Marseille', 'Corse', '2024-12-20', '2024-12-24', '08:00:00.000000'),
(13, 90, 300, 'Avion low-cost', 'Paris', 'Barcelona', '2024-12-22', '2024-12-25', '02:00:00.000000'),
(14, 30, 40, 'Bus touristique', 'Paris', 'Versailles', '2024-12-21', '2024-12-23', '01:30:00.000000'),
(15, 0.5, 5, 'Scooter électrique', 'Paris', 'Paris', '2024-12-20', '2024-12-20', '00:05:00.000000'),
(16, 80, 250, 'Voiture de location', 'Paris', 'Côte d\'Azur', '2024-12-19', '2024-12-21', '03:30:00.000000'),
(17, 10, 25, 'Train local', 'Paris', 'Toulouse', '2024-12-23', '2024-12-27', '05:00:00.000000'),
(18, 20, 60, 'Autocar interurbain', 'Paris', 'Bordeaux', '2024-12-24', '2024-12-27', '06:30:00.000000'),
(19, 10, 35, 'Navette aéroport', 'Paris', 'Orly', '2024-12-20', '2024-12-20', '00:30:00.000000'),
(20, 5, 25, 'Tuk-tuk', 'Bangkok', 'Bangkok', '2024-12-21', '2024-12-22', '00:40:00.000000'),
(21, 75, 200, 'Bateau rapide', 'Venise', 'Murano', '2024-12-19', '2024-12-20', '00:30:00.000000'),
(22, 120, 20, 'Train international', 'Paris', 'Bruxelles', '2024-12-18', '2024-12-20', '02:00:00.000000'),
(23, 1500, 800, 'Avion d’affaires', 'Paris', 'Dubai', '2024-12-15', '2024-12-25', '07:30:00.000000'),
(24, 1, 25, 'Bus scolaire', 'Paris', 'Versailles', '2024-12-20', '2024-12-21', '00:50:00.000000'),
(25, 2.5, 18, 'Monorail', 'Las Vegas', 'Las Vegas', '2024-12-20', '2024-12-20', '00:10:00.000000'),
(26, 45, 15, 'Train express régional', 'Paris', 'Rouen', '2024-12-21', '2024-12-22', '01:30:00.000000'),
(27, 8, 50, 'Minibus', 'Paris', 'Marseille', '2024-12-22', '2024-12-23', '02:30:00.000000'),
(28, 20, 30, 'Train touristique', 'Paris', 'Normandie', '2024-12-24', '2024-12-27', '02:30:00.000000'),
(29, 300, 400, 'Avion cargo (passager)', 'Paris', 'Shanghai', '2024-12-25', '2024-12-30', '12:00:00.000000'),
(30, 10, 50, 'Moto-taxi', 'Bali', 'Bali', '2024-12-20', '2024-12-20', '00:30:00.000000'),
(31, 55, 12, 'Train à grande vitesse', 'Paris', 'Lille', '2024-12-19', '2024-12-21', '01:30:00.000000'),
(32, 20, 25, 'Train régional', 'Paris', 'Nantes', '2024-12-23', '2024-12-25', '02:00:00.000000'),
(33, 90, 230, 'Vol domestique', 'Paris', 'Toulouse', '2024-12-24', '2024-12-27', '01:30:00.000000'),
(34, 700, 450, 'Vol international', 'Paris', 'Tokyo', '2024-12-18', '2024-12-22', '11:00:00.000000'),
(35, 18, 50, 'Bus longue distance', 'Paris', 'Madrid', '2024-12-20', '2024-12-23', '13:00:00.000000'),
(36, 2.5, 35, 'Navette aéroport', 'Paris', 'Charles de Gaulle', '2024-12-19', '2024-12-19', '00:40:00.000000'),
(37, 45, 15, 'Train rapide', 'Paris', 'Toulouse', '2024-12-18', '2024-12-20', '04:00:00.000000'),
(38, 8, 30, 'Métro', 'Paris', 'Paris', '2024-12-20', '2024-12-20', '00:10:00.000000'),
(39, 25, 35, 'Train rapide', 'Paris', 'Marseille', '2024-12-22', '2024-12-23', '02:00:00.000000'),
(40, 4, 18, 'Covoiturage', 'Paris', 'Tours', '2024-12-24', '2024-12-24', '01:10:00.000000'),
(41, 60, 160, 'Vol international', 'Paris', 'Rome', '2024-12-19', '2024-12-19', '01:50:00.000000'),
(42, 100, 400, 'Vol intercontinental', 'Paris', 'Sydney', '2024-12-25', '2024-12-25', '21:00:00.000000'),
(43, 1.2, 20, 'Location de vélo', 'Paris', 'Paris', '2024-12-20', '2024-12-20', '00:15:00.000000'),
(44, 12, 30, 'Autocar', 'Paris', 'Lyon', '2024-12-21', '2024-12-22', '02:40:00.000000'),
(45, 40, 120, 'Ferry', 'Marseille', 'Tunisie', '2024-12-20', '2024-12-23', '09:00:00.000000'),
(46, 70, 180, 'Train', 'Paris', 'Bordeaux', '2024-12-22', '2024-12-22', '03:00:00.000000'),
(47, 120, 500, 'Vol à bas prix', 'Paris', 'Toronto', '2024-12-20', '2024-12-20', '08:00:00.000000'),
(48, 18, 45, 'Bus', 'Paris', 'Marseille', '2024-12-23', '2024-12-23', '07:00:00.000000'),
(49, 40, 100, 'Voiture de location', 'Paris', 'Bruxelles', '2024-12-19', '2024-12-20', '04:00:00.000000'),
(50, 50, 250, 'Vol domestique', 'Paris', 'Marseille', '2024-12-18', '2024-12-20', '02:00:00.000000');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `username` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `nom`, `password`, `prenom`, `username`) VALUES
(1, 'arafat', '$2a$10$dtbSLLgaXT.uHyEZlvQiIeEorZHUEFl56GK9mqimSHNXRVxmQvZWm', 'arafat', 'arafat@gmail.com'),
(2, 'MOussa', '$2a$10$ijMegkaWdlXOwT6dyXjYZ.peRD0/rJSbWSBWBqk/HKp0GPUAEwIoy', 'MOussa', 'Moussa@gmail.com'),
(3, 'Arafata  aaa', '$2a$10$gNVwfSq0FexKETf94D4ClekxUluYELX9zldgml0zsRcHaK1C5IiEK', 'Arafata  aaa', 'mous@gmail.com'),
(4, 'abcdd', '$2a$10$EVYPYCayEm4B.vRxcW4nq.dWj.7zy96uOXIq5hEWoDrkfEPTPo5pO', 'abcdd', 'abcdd@gmail.com'),
(5, 'abcd', '$2a$10$sjRsAusPf4l9FGc7fpI/bO.Xc6R4tttQlNjd2w4FujDOD4i7uP/76', 'abcd', 'abcde@gmail.com'),
(6, 'MOUSTAPHA', '$2a$10$Qq0AJKS3AAHe0PIeVMKJkuTIzuMK1chF0jRUWjsN4M5eFUUffYgni', 'ARAFAT', 'test_nord@netysoft.com'),
(7, 'MOUSTAPHA', '$2a$10$ITXMYhUHf3cxP1I.rqAn8O5KumI.skLn63MI1TZHSsLVx1Xa2ADhG', 'ARAFAT', 'ali@gmail.com'),
(8, 'GUEMMAT', '$2a$10$g1uDP3dRWXvqTMTkYUO0jelgKqTN9iMxmuXYebV9/2TRTAQl.LLLq', 'Manel', 'manel@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

DROP TABLE IF EXISTS `ville`;
CREATE TABLE IF NOT EXISTS `ville` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `id_pays` bigint NOT NULL,
  `pib` double NOT NULL,
  `population` bigint NOT NULL,
  `taux_co2` double NOT NULL,
  `tauxCo2` double NOT NULL,
  `fair_score` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaxs0bkhk2wqmwevrwptgwfy9g` (`id_pays`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `ville`
--

INSERT INTO `ville` (`id`, `nom`, `id_pays`, `pib`, `population`, `taux_co2`, `tauxCo2`, `fair_score`) VALUES
(1, 'Paris', 1, 710000000000, 2100000, 5400, 5400, 4.6),
(2, 'Barcelone', 2, 200000000000, 1600000, 4200, 4200, 0),
(3, 'Rome', 3, 300000000000, 2800000, 4600, 4600, 0),
(4, 'Berlin', 4, 1000000000000, 3500000, 3500, 3500, 0),
(5, 'Lisbonne', 5, 250000000000, 500000, 4200, 4200, 0),
(6, 'Bruxelles', 6, 500000000000, 1800000, 3000, 3000, 0),
(7, 'Amsterdam', 7, 600000000000, 1000000, 3500, 3500, 0),
(8, 'Londres', 8, 2500000000000, 8900000, 3200, 3200, 0);

-- --------------------------------------------------------

--
-- Structure de la table `voyage`
--

DROP TABLE IF EXISTS `voyage`;
CREATE TABLE IF NOT EXISTS `voyage` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_depart` date NOT NULL,
  `date_retour` date NOT NULL,
  `id_user` bigint NOT NULL,
  `ville_depart` bigint NOT NULL,
  `ville_destination` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnvslq0hqkpfpve0mi9bwqq67c` (`id_user`),
  KEY `FKoqsxmhx2wk0owfbgonokjrh4g` (`ville_depart`),
  KEY `FK3vum1jg6n8qsqpxrk9a5fpmmh` (`ville_destination`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `eco_action`
--
ALTER TABLE `eco_action`
  ADD CONSTRAINT `eco_action_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `hebergement`
--
ALTER TABLE `hebergement`
  ADD CONSTRAINT `FK_Hebergement_Ville` FOREIGN KEY (`id_ville`) REFERENCES `ville` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `role`
--
ALTER TABLE `role`
  ADD CONSTRAINT `FK8qjyc7wgcql5ngo85w7d61k9y` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `ville`
--
ALTER TABLE `ville`
  ADD CONSTRAINT `FKaxs0bkhk2wqmwevrwptgwfy9g` FOREIGN KEY (`id_pays`) REFERENCES `pays` (`id`);

--
-- Contraintes pour la table `voyage`
--
ALTER TABLE `voyage`
  ADD CONSTRAINT `FK3vum1jg6n8qsqpxrk9a5fpmmh` FOREIGN KEY (`ville_destination`) REFERENCES `ville` (`id`),
  ADD CONSTRAINT `FKnvslq0hqkpfpve0mi9bwqq67c` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKoqsxmhx2wk0owfbgonokjrh4g` FOREIGN KEY (`ville_depart`) REFERENCES `ville` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
