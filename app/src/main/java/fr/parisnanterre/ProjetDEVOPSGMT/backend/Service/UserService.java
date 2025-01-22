package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username); // Recherche utilisateur par email
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    public Optional<User> findUserByUsernameAndPassword(String username, String password);
}
