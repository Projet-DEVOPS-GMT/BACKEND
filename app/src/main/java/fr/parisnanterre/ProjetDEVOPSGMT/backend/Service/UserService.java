package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();  // Récupérer tous les utilisateurs
    Optional<User> getUserById(Long id);  // Récupérer un utilisateur par son ID
    Optional<User> getUserByUsername(String username);  // Recherche utilisateur par username
    User createUser(User user);  // Créer un nouvel utilisateur
    User updateUser(Long id, User user);  // Mettre à jour un utilisateur existant
    void deleteUser(Long id);  // Supprimer un utilisateur
    Optional<User> findUserByUsernameAndPassword(String username, String password);  // Recherche par username et mot de passe
}
