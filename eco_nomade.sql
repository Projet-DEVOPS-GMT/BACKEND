-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : dim. 19 jan. 2025 à 16:20
-- Version du serveur : 5.7.39
-- Version de PHP : 8.2.0

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

CREATE TABLE `consommation` (
  `id` bigint(20) NOT NULL,
  `prix` decimal(10,2) NOT NULL,
  `tauxco2` decimal(10,2) NOT NULL,
  `type` varchar(50) NOT NULL,
  `id_hebergement` bigint(20) DEFAULT NULL,
  `id_restauration` bigint(20) DEFAULT NULL,
  `id_transport` bigint(20) DEFAULT NULL,
  `id_voyage` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `hebergement`
--

CREATE TABLE `hebergement` (
  `id` bigint(20) NOT NULL,
  `conditions_reservation` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `equipement` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `prix` decimal(10,2) DEFAULT NULL,
  `tauxco2` decimal(10,2) DEFAULT NULL,
  `type_hebergement` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `hebergement`
--

INSERT INTO `hebergement` (`id`, `conditions_reservation`, `description`, `equipement`, `photo`, `prix`, `tauxco2`, `type_hebergement`) VALUES
(1, 'Annulation gratuite jusqu\'à 24h avant l\'arrivée', 'Appartement moderne avec vue sur la ville, proche des commodités.', 'Wi-Fi, Climatisation, Cuisine équipée, Balcon', 'https://example.com/photos/appartement1.jpg', '120.00', '2.50', 'Appartement'),
(2, 'Annulation gratuite jusqu\'à 7 jours avant l\'arrivée', 'Chalet spacieux en montagne, idéal pour les familles.', 'Cheminée, Parking gratuit, Proche des pistes', 'https://example.com/photos/chalet1.jpg', '250.00', '5.20', 'Chalet'),
(3, 'Non remboursable', 'Chambre d\'hôtel confortable avec petit déjeuner inclus.', 'Wi-Fi, Télévision, Room service', 'https://example.com/photos/hotel1.jpg', '90.00', '3.10', 'Hôtel'),
(4, 'Annulation gratuite jusqu\'à 14 jours avant l\'arrivée', 'Villa luxueuse avec piscine privée et grand jardin.', 'Piscine, Wi-Fi, Climatisation, Barbecue', 'https://example.com/photos/villa1.jpg', '450.00', '8.00', 'Villa'),
(5, 'Annulation gratuite jusqu\'à 3 jours avant l\'arrivée', 'Emplacement pour tente avec accès aux sanitaires partagés.', 'Parking gratuit, Barbecue, Électricité', 'https://example.com/photos/camping1.jpg', '50.00', '1.00', 'Camping'),
(6, 'Annulation gratuite jusqu\'à 48h avant l\'arrivée', 'Appartement avec deux chambres dans le centre historique.', 'Wi-Fi, Cuisine équipée, Machine à laver', 'https://example.com/photos/appartement2.jpg', '140.00', '2.30', 'Appartement'),
(7, 'Non remboursable', 'Suite dans un hôtel 5 étoiles avec spa et restaurant gastronomique.', 'Wi-Fi, Piscine, Service en chambre 24/7', 'https://example.com/photos/hotel2.jpg', '200.00', '4.00', 'Hôtel'),
(8, 'Annulation gratuite jusqu\'à 5 jours avant l\'arrivée', 'Riad traditionnel au cœur de la médina avec patio intérieur.', 'Wi-Fi, Climatisation, Petit déjeuner inclus', 'https://example.com/photos/riad1.jpg', '180.00', '3.50', 'Riad'),
(9, 'Annulation gratuite jusqu\'à 48h avant l\'arrivée', 'Chambre dans une maison d\'hôtes avec accueil chaleureux.', 'Petit déjeuner inclus, Wi-Fi, Terrasse', 'https://example.com/photos/maison_hotes1.jpg', '75.00', '2.80', 'Maison d\'hôtes'),
(10, 'Annulation gratuite jusqu\'à 10 jours avant l\'arrivée', 'Chalet moderne avec sauna et vue panoramique sur les montagnes.', 'Sauna, Parking, Proche des pistes', 'https://example.com/photos/chalet2.jpg', '300.00', '6.00', 'Chalet'),
(11, 'Non remboursable', 'Bungalow en bord de plage avec accès direct à la mer.', 'Climatisation, Wi-Fi, Parking gratuit', 'https://example.com/photos/bungalow1.jpg', '100.00', '1.50', 'Bungalow'),
(12, 'Annulation gratuite jusqu\'à 24h avant l\'arrivée', 'Lit en dortoir dans une auberge de jeunesse conviviale.', 'Wi-Fi, Cuisine commune, Salon partagé', 'https://example.com/photos/auberge1.jpg', '30.00', '0.50', 'Auberge'),
(13, 'Non-fumeur', 'Appartement lumineux avec balcon et vue sur la ville', 'Wi-Fi, Climatisation, Cuisine équipée', 'photo11.jpg', '150.00', '12.00', 'Appartement'),
(14, 'Animaux acceptés', 'Maison isolée avec grand jardin et cheminée', 'Wi-Fi, Parking, Cuisine équipée', 'photo12.jpg', '200.00', '20.00', 'Maison de campagne'),
(15, 'Non-fumeur', 'Chambre confortable avec petit-déjeuner inclus', 'Wi-Fi, Salle de bain privée, Vue sur le jardin', 'photo13.jpg', '75.00', '10.00', 'Chambre d\'hôtes'),
(16, 'Non-fumeur', 'Villa privée avec piscine et salle de sport', 'Piscine, Jacuzzi, Cuisine haut de gamme', 'photo14.jpg', '800.00', '50.00', 'Villa de luxe'),
(17, 'Non-fumeur', 'Chalet chaleureux près des stations de ski', 'Cheminée, Parking, Sauna', 'photo15.jpg', '250.00', '25.00', 'Chalet en montagne'),
(18, 'Animaux acceptés', 'Bungalow cosy avec accès direct à la plage', 'Terrasse, Wi-Fi, Climatisation', 'photo16.jpg', '180.00', '22.00', 'Bungalow sur la plage'),
(19, 'Non-fumeur', 'Studio compact avec toutes les commodités modernes', 'Wi-Fi, Cuisine équipée, Climatisation', 'photo17.jpg', '120.00', '14.00', 'Studio moderne'),
(20, 'Non-fumeur', 'Riad authentique avec patio traditionnel', 'Climatisation, Hammam, Petit-déjeuner inclus', 'photo18.jpg', '220.00', '20.00', 'Riad marocain'),
(21, 'Animaux acceptés', 'Yourte avec vue panoramique, idéale pour un séjour nature', 'Lit double, Poêle à bois, Activités en plein air', 'photo19.jpg', '90.00', '8.00', 'Yourte éco-responsable'),
(22, 'Non-fumeur', 'Cabane dans les arbres avec terrasse et vue exceptionnelle', 'Wi-Fi, Petit-déjeuner inclus, Proche des sentiers', 'photo20.jpg', '170.00', '18.00', 'Cabane perchée'),
(23, 'Non-fumeur', 'Penthouse luxueux avec vue imprenable sur la ville', 'Terrasse, Jacuzzi, Wi-Fi haut débit', 'photo21.jpg', '400.00', '15.00', 'Penthouse'),
(24, 'Animaux acceptés', 'Maison conviviale dans un quartier calme', 'Cuisine équipée, Wi-Fi, Parking privé', 'photo22.jpg', '140.00', '13.00', 'Maison mitoyenne'),
(25, 'Non-fumeur', 'Tiny House éco-conçue au cœur de la nature', 'Cuisine compacte, Terrasse, Poêle à bois', 'photo23.jpg', '100.00', '7.00', 'Tiny House'),
(26, 'Non-fumeur', 'Maison moderne avec grandes baies vitrées', 'Piscine, Parking, Climatisation', 'photo24.jpg', '300.00', '20.00', 'Maison d\'architecte'),
(27, 'Non-fumeur', 'Studio avec accès direct à la plage', 'Cuisine équipée, Terrasse, Climatisation', 'photo25.jpg', '110.00', '9.00', 'Studio en bord de mer'),
(28, 'Non-fumeur', 'Hôtel design avec services personnalisés', 'Wi-Fi, Bar, Service en chambre', 'photo26.jpg', '250.00', '16.00', 'Hôtel boutique'),
(29, 'Animaux acceptés', 'Gîte familial dans une ferme rénovée', 'Wi-Fi, Parking, Activités pour enfants', 'photo27.jpg', '180.00', '22.00', 'Gîte rural'),
(30, 'Non-fumeur', 'Loft spacieux avec une décoration industrielle', 'Wi-Fi, Cuisine équipée, Climatisation', 'photo28.jpg', '300.00', '18.00', 'Appartement loft'),
(31, 'Non-fumeur', 'Villa avec piscine et vue sur la mer Méditerranée', 'Piscine, Jardin, Parking privé', 'photo29.jpg', '450.00', '30.00', 'Villa méditerranéenne'),
(32, 'Animaux acceptés', 'Maison en pierre avec charme authentique', 'Cheminée, Wi-Fi, Proche des sentiers de randonnée', 'photo30.jpg', '130.00', '14.00', 'Maison traditionnelle'),
(33, 'Non-fumeur', 'Riad avec piscine intérieure et service haut de gamme', 'Hammam, Wi-Fi, Service en chambre', 'photo31.jpg', '500.00', '40.00', 'Riad de luxe'),
(34, 'Animaux non acceptés', 'Cabane sur l\'eau avec vue panoramique', 'Kayak inclus, Terrasse, Cuisine équipée', 'photo32.jpg', '200.00', '25.00', 'Cabane flottante'),
(35, 'Non-fumeur', 'Chambre majestueuse dans un château historique', 'Petit-déjeuner, Wi-Fi, Visite du château', 'photo33.jpg', '350.00', '20.00', 'Chambre dans un château'),
(36, 'Animaux acceptés', 'Appartement spacieux pour les familles', 'Wi-Fi, Cuisine équipée, Parking gratuit', 'photo34.jpg', '150.00', '10.00', 'Appartement familial'),
(37, 'Non-fumeur', 'Maison avec terrasse et accès direct au lac', 'Canoë inclus, Wi-Fi, Barbecue', 'photo35.jpg', '300.00', '30.00', 'Maison au bord d\'un lac'),
(38, 'Animaux acceptés', 'Bungalow écologique au cœur de la jungle', 'Ventilateur, Terrasse, Cuisine équipée', 'photo36.jpg', '100.00', '20.00', 'Bungalow jungle'),
(39, 'Non-fumeur', 'Chalet avec vue imprenable sur le lac', 'Cheminée, Wi-Fi, Activités nautiques', 'photo37.jpg', '250.00', '18.00', 'Chalet au bord de l\'eau'),
(40, 'Non-fumeur', 'Studio pratique pour un séjour en ville', 'Climatisation, Wi-Fi, Cuisine équipée', 'photo38.jpg', '80.00', '12.00', 'Studio urbain'),
(41, 'Animaux acceptés', 'Villa entourée de palmiers avec piscine privée', 'Piscine, Wi-Fi, Parking', 'photo39.jpg', '600.00', '50.00', 'Villa tropicale'),
(42, 'Non-fumeur', 'Maison en centre-ville avec terrasse fleurie', 'Cuisine équipée, Wi-Fi, Climatisation', 'photo40.jpg', '200.00', '15.00', 'Maison de charme');

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

CREATE TABLE `pays` (
  `id` bigint(20) NOT NULL,
  `fair_score` decimal(5,2) NOT NULL,
  `nom` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`id`, `fair_score`, `nom`) VALUES
(1, '4.50', 'France'),
(2, '4.30', 'Allemagne'),
(3, '3.80', 'Espagne'),
(4, '3.90', 'Italie');

-- --------------------------------------------------------

--
-- Structure de la table `restauration`
--

CREATE TABLE `restauration` (
  `id` bigint(20) NOT NULL,
  `prix_moyenne` decimal(10,2) NOT NULL,
  `tauxco2` decimal(10,2) DEFAULT NULL,
  `type_restaurant` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `nom_profil` varchar(50) NOT NULL,
  `id_user` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `transport`
--

CREATE TABLE `transport` (
  `id` bigint(20) NOT NULL,
  `estimation_prix` double NOT NULL,
  `tauxco2` double NOT NULL,
  `type_transport` varchar(50) NOT NULL,
  `ville_depart` varchar(255) NOT NULL,
  `ville_destination` varchar(255) NOT NULL,
  `date_depart` date DEFAULT NULL,
  `date_retour` date DEFAULT NULL,
  `duree` time(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `email`, `mot_de_passe`, `nom`, `prenom`) VALUES
(1, 'arfa@gmail.com', 'Arfaben12!', 'Moustapha', 'Araaft'),
(2, 'manel@gmail.com', 'Manel12', 'GU', 'Manel'),
(3, 'bty@gmail.com', '2025economad!!', 'Timofte', 'Betty');

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

CREATE TABLE `ville` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `id_pays` bigint(20) NOT NULL,
  `pib` double NOT NULL,
  `population` bigint(20) NOT NULL,
  `taux_co2` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ville`
--

INSERT INTO `ville` (`id`, `nom`, `id_pays`, `pib`, `population`, `taux_co2`) VALUES
(1, 'Paris', 1, 70800, 2148000, 4.2),
(2, 'Marseille', 1, 47000, 861635, 4),
(3, 'Lyon', 1, 73800, 515695, 4.1),
(4, 'Toulouse', 1, 56100, 479180, 3.7),
(5, 'Nice', 1, 40000, 343000, 3.5),
(6, 'Nantes', 1, 41000, 314000, 3.6),
(7, 'Strasbourg', 1, 48000, 280000, 3.8),
(8, 'Montpellier', 1, 41000, 290000, 3.6),
(9, 'Bordeaux', 1, 50000, 250000, 3.7),
(10, 'Lille', 1, 46000, 232000, 4),
(11, 'Rennes', 1, 35000, 217000, 3.3),
(12, 'Le Havre', 1, 45000, 172000, 3.5),
(13, 'Saint-Étienne', 1, 34000, 171000, 3.3),
(14, 'Toulon', 1, 38000, 165000, 3.6),
(15, 'Le Mans', 1, 32000, 143000, 3.2),
(16, 'Aix-en-Provence', 1, 47000, 143000, 3.5),
(17, 'Clermont-Ferrand', 1, 38000, 141000, 3.4),
(18, 'Reims', 1, 43000, 182000, 3.6),
(19, 'Angers', 1, 42000, 154000, 3.4),
(20, 'La Rochelle', 1, 37000, 80000, 3.2),
(21, 'Caen', 1, 36000, 106000, 3.3),
(22, 'Perpignan', 1, 32000, 120000, 3.1),
(23, 'Grenoble', 1, 53000, 160000, 3.7),
(24, 'Nancy', 1, 40000, 104000, 3.4),
(25, 'Orléans', 1, 37000, 116000, 3.3),
(26, 'Metz', 1, 42000, 116000, 3.5),
(27, 'Brest', 1, 39000, 140000, 3.4),
(28, 'Poitiers', 1, 31000, 88000, 3.2),
(29, 'Chartres', 1, 29000, 38000, 3.1),
(30, 'Nevers', 1, 23000, 35000, 2.9),
(31, 'Tarbes', 1, 26000, 50000, 2.8),
(32, 'Chalon-sur-Saône', 1, 24000, 45000, 2.7),
(33, 'Évreux', 1, 28000, 60000, 3.1),
(34, 'Saint-Malo', 1, 31000, 46000, 3.2),
(35, 'Vannes', 1, 33000, 53000, 3.1),
(36, 'Ajaccio', 1, 34000, 67000, 3.5),
(37, 'Béziers', 1, 32000, 77000, 3.2),
(38, 'Annecy', 1, 49000, 130000, 3.6),
(39, 'Chambéry', 1, 44000, 62000, 3.4),
(40, 'Niort', 1, 30000, 59000, 2.9),
(41, 'Douai', 1, 31000, 41000, 3.3),
(42, 'Montauban', 1, 32000, 60000, 3),
(43, 'Angoulême', 1, 29000, 43000, 2.8),
(44, 'Sète', 1, 35000, 43000, 3.3),
(45, 'Le Puy-en-Velay', 1, 22000, 20000, 2.6),
(46, 'Vichy', 1, 23000, 25000, 2.7),
(47, 'Cannes', 1, 51000, 74000, 3.6),
(48, 'Fontainebleau', 1, 38000, 16000, 3.2),
(49, 'Villers-Cotterêts', 1, 21000, 15000, 2.8),
(50, 'Échirolles', 1, 34000, 37000, 3.2);

-- --------------------------------------------------------

--
-- Structure de la table `voyage`
--

CREATE TABLE `voyage` (
  `id` bigint(20) NOT NULL,
  `date_depart` date NOT NULL,
  `date_retour` date NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `ville_depart` bigint(20) NOT NULL,
  `ville_destination` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `consommation`
--
ALTER TABLE `consommation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm8v8dn0qq32rylgwic8d6j28q` (`id_restauration`),
  ADD KEY `FK61amtkv49jffag45c06574cx2` (`id_transport`),
  ADD KEY `FKsgwlyblb80bnf340gilio38df` (`id_voyage`),
  ADD KEY `FKfvnqklkp6neoo8f4dmkijev00` (`id_hebergement`);

--
-- Index pour la table `hebergement`
--
ALTER TABLE `hebergement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `pays`
--
ALTER TABLE `pays`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `restauration`
--
ALTER TABLE `restauration`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8qjyc7wgcql5ngo85w7d61k9y` (`id_user`);

--
-- Index pour la table `transport`
--
ALTER TABLE `transport`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Index pour la table `ville`
--
ALTER TABLE `ville`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKaxs0bkhk2wqmwevrwptgwfy9g` (`id_pays`);

--
-- Index pour la table `voyage`
--
ALTER TABLE `voyage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnvslq0hqkpfpve0mi9bwqq67c` (`id_user`),
  ADD KEY `FKoqsxmhx2wk0owfbgonokjrh4g` (`ville_depart`),
  ADD KEY `FK3vum1jg6n8qsqpxrk9a5fpmmh` (`ville_destination`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `consommation`
--
ALTER TABLE `consommation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `hebergement`
--
ALTER TABLE `hebergement`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT pour la table `pays`
--
ALTER TABLE `pays`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `restauration`
--
ALTER TABLE `restauration`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `transport`
--
ALTER TABLE `transport`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `ville`
--
ALTER TABLE `ville`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT pour la table `voyage`
--
ALTER TABLE `voyage`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `consommation`
--
ALTER TABLE `consommation`
  ADD CONSTRAINT `FK61amtkv49jffag45c06574cx2` FOREIGN KEY (`id_transport`) REFERENCES `transport` (`id`),
  ADD CONSTRAINT `FKfvnqklkp6neoo8f4dmkijev00` FOREIGN KEY (`id_hebergement`) REFERENCES `hebergement` (`id`),
  ADD CONSTRAINT `FKm8v8dn0qq32rylgwic8d6j28q` FOREIGN KEY (`id_restauration`) REFERENCES `restauration` (`id`),
  ADD CONSTRAINT `FKsgwlyblb80bnf340gilio38df` FOREIGN KEY (`id_voyage`) REFERENCES `voyage` (`id`);

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
