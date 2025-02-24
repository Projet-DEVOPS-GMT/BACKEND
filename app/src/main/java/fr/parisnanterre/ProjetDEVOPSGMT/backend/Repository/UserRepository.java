package fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Recherche un utilisateur par son nom d'utilisateur (email)
    Optional<User> findByUsername(String username);

    // Recherche un utilisateur par son nom d'utilisateur et mot de passe (pour la connexion)
    Optional<User> findByUsernameAndPassword(String username, String password);
}
