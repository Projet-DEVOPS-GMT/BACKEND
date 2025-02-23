-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 23 fév. 2025 à 22:39
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
(8, NULL, '2025-02-27', '2025-02-22', 89, NULL, NULL, 'Train', 'transport', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `eco_action`
--

DROP TABLE IF EXISTS `eco_action`;
CREATE TABLE IF NOT EXISTS `eco_action` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `co2_saved` decimal(10,2) NOT NULL,
  `date_action` datetime NOT NULL,
  `id_user` bigint NOT NULL,
  `co2saved` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `eco_action`
--

INSERT INTO `eco_action` (`id`, `description`, `co2_saved`, `date_action`, `id_user`, `co2saved`) VALUES
(1, 'Éteindre la lumière en quittant une pièce', 0.50, '2025-02-23 08:00:00', 1, 0),
(2, 'Prendre le bus au lieu de la voiture', 1.20, '2025-02-23 08:30:00', 2, 0),
(3, 'Réduire la température de chauffage de 1°C', 1.00, '2025-02-23 09:00:00', 1, 0),
(4, 'Utiliser des sacs réutilisables', 0.20, '2025-02-23 10:00:00', 2, 0);

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
(6, 'Annulation gratuite jusqu\'à 48h avant l\'arrivée', 'Appartement avec deux chambres dans le centre historique.', 'Wi-Fi, Cuisine équipée, Machine à laver', 'hebergements/appartement/5', 140.00, 2.30, 'Appartement', 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `nom_profil`, `id_user`) VALUES
(1, 'Utilisateur', 1),
(2, 'Administrateur', 2);

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
(4, 600, 500, 'Vol international', 'Paris', 'New York', '2024-12-20', '2025-01-10', '08:00:00.000000');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `nom`, `password`, `prenom`, `username`) VALUES
(1, 'arafat', '$2a$10$dtbSLLgaXT.uHyEZlvQiIeEorZHUEFl56GK9mqimSHNXRVxmQvZWm', 'arafat', 'arafat@gmail.com'),
(2, 'MOussa', '$2a$10$ijMegkaWdlXOwT6dyXjYZ.peRD0/rJSbWSBWBqk/HKp0GPUAEwIoy', 'MOussa', 'Moussa@gmail.com');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `voyage`
--

INSERT INTO `voyage` (`id`, `date_depart`, `date_retour`, `id_user`, `ville_depart`, `ville_destination`) VALUES
(1, '2024-12-25', '2024-12-30', 1, 1, 2),
(2, '2024-12-20', '2025-01-10', 2, 3, 4);

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
