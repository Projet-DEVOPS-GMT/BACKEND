# BACKEND

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE.txt)
![Latest release](https://img.shields.io/github/v/release/Projet-DEVOPS-GMT/BACKEND)

[![Java CI Gradle build and test](https://github.com/Projet-DEVOPS-GMT/BACKEND/actions/workflows/gradle_build.yml/badge.svg?branch=main)](https://github.com/Projet-DEVOPS-GMT/BACKEND/actions/workflows/gradle_build.yml)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Projet-DEVOPS-GMT_BACKEND&metric=bugs)](https://sonarcloud.io/summary/new_code?id=Projet-DEVOPS-GMT_BACKEND)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Projet-DEVOPS-GMT_BACKEND&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Projet-DEVOPS-GMT_BACKEND)

[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Projet-DEVOPS-GMT_BACKEND&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=Projet-DEVOPS-GMT_BACKEND)

# Projet ECO Nomade - Backend

## Description
Ce projet est une application Spring Boot développée. Il fournit une API REST pour gérer les consommations (transport, hébergement, restauration et voyage), en mettant l'accent sur les données environnementales comme les coûts et les émissions de CO₂.

L'objectif principal est de permettre une gestion efficace des données liées aux voyages et de fournir des statistiques pour un voyage plus durable et moins chère en terme de prix et écologique.

---

## Prérequis

Avant de commencer, assurez-vous d'avoir les outils suivants installés sur votre machine :

1. **Java Development Kit (JDK)** version 17 ou supérieure
2. **Gradle** (vérifier avec `gradle -v`)
3. **Node.js** et **npm** (vérifier avec `node -v` et `npm -v`)
4. **Vue CLI** (vérifier avec `vue --version` ou installez-le via `npm install -g @vue/cli`)

## Lancement du backend Spring Boot

1. **Cloner le projet**
   ```bash
   git clone https://github.com/Projet-DEVOPS-GMT/BACKEND.git
   cd BACKEND
   ```

2. **Configurer le fichier `application.yml`**
   Modifiez le fichier de configuration pour correspondre à votre environnement (par exemple, configuration le nom de la base de données).

3. **Lancer le projet avec Gradle**
   ```bash
   gradle bootRun
   ```
   Par défaut, l'application sera accessible sur `http://localhost:8080`.
5. **Fonctionnalités**

### Gestion des entités :
- Consommations
- Transport
- Hébergement
- Restauration
- Voyage

### Calcul des émissions de CO₂ :
- Association des taux de CO₂ aux différents types de consommations.

### API REST :
- CRUD complet pour chaque entité.

### Validation et gestion des erreurs :
- Validation des entrées utilisateur.
- Gestion centralisée des exceptions.

### Couverture de tests :
- Tests unitaires pour les services, modèles et contrôleurs.


6. **Endpoints**
   Vous pouvez accéder à l'API via les endpoints configurés dans vos controllers. Par exemple :
  #### Consommations :
    - **GET** `/api/consommations` : Liste toutes les consommations.
    - **POST** `/api/consommations` : Ajoute une nouvelle consommation.
    - **PUT** `/api/consommations/{id}` : Met à jour une consommation existante.
    - **DELETE** `/api/consommations/{id}` : Supprime une consommation.

## Lancement du frontend Vue.js

1. **Aller dans le dossier frontend**
   ```bash
   cd ../frontend
   ```

2. **Installer les dépendances**
   ```bash
   npm install
   ```

3. **Lancer le serveur de développement**
   ```bash
   npm run serve
   ```
   Par défaut, l'application sera accessible sur `http://localhost:8081`.

4. **Configurer le fichier `apiClient`**
   Assurez-vous que la base URL dans `src/apiClient.js` pointe vers le backend :
   ```javascript
   import axios from 'axios';

   const apiClient = axios.create({
       baseURL: 'http://localhost:8080/api',
       headers: {
           'Content-Type': 'application/json',
       },
   });

   export default apiClient;
   ```

## Structure du projet

```
.
├── backend
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── fr/parisnanterre/ProjetDEVOPSGMT/backend
│   │   │   ├── resources
│   │   │   │   └── application.yml
├── frontend
│   ├── public
│   ├── src
│   │   ├── components
│   │   │   
│   │   ├── apiClient.js
```


## Problèmes courants

- **Erreur `Request method 'POST' is not supported` :**
  - Assurez-vous que le frontend envoie des requêtes à la bonne URL (voir `apiClient.js`).
 
- **Erreur de compilation dans le backend :**
  - Vérifiez que vous utilisez la bonne version de JDK (17+).

- **Erreur de connexion à la base de données :**
  - Assurez-vous que votre base de données est en cours d'exécution et que les paramètres dans `application.yml` sont corrects.

## Déploiement

### Backend
Pour construire le jar exécutable :
```bash
gradle build
```
Le fichier jar sera généré dans `build/libs`. Lancez-le avec :
```bash
java -jar build/libs/votre-projet.jar
```

### Frontend
Pour construire le frontend pour la production :
```bash
npm run build
```
Les fichiers optimisés seront dans le dossier `dist`. Vous pouvez les déployer sur un serveur web.

---

À présent, votre projet est prêt à être exécuté en local et déployé !

## Auteur

- **Membre 1**
- **Nom** : Arafat Moustapha
- **Contact** : arafat.moustapha@parisnanterre.fr
- **GitHub** : https://github.com/ARAFAT-Moustaph


- **Membre 2**
- **Nom** : Manel Guemmat
- **Contact** : manel.guemmat@parisnanterre.fr
- **GitHub** : https://github.com/ManelGuemm

- **Membre 3**
- **Nom** : Alexandra Timofte
- **Contact** : alexandra.timofte@parisnanterre.fr
- **GitHub** : https://github.com/timo0003

---

## Licence

Ce projet est sous licence MIT. Vous êtes libre de l'utiliser et de le modifier selon vos besoins.

