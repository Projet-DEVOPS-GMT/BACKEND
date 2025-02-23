package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);  // Retourne un Optional pour gérer les valeurs manquantes
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);  // Recherche d'utilisateur par nom d'utilisateur (email)
    }

    @Override
    public User createUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  // Cryptage du mot de passe
        String password = user.getPassword();  // Mot de passe de l'utilisateur
        user.setPassword(encoder.encode(password));  // Encodage du mot de passe

        // Vérification si l'utilisateur existe déjà avec cet email
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Un utilisateur avec cet email existe déjà !");
        }

        return userRepository.save(user);  // Sauvegarde l'utilisateur
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setNom(user.getNom());
            existingUser.setPrenom(user.getPrenom());
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());  // Mettez à jour le mot de passe si nécessaire
            return userRepository.save(existingUser);  // Sauvegarde l'utilisateur mis à jour
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);  // Supprime l'utilisateur par son ID
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);  // Retourne l'utilisateur si le mot de passe est valide
            }
        }
        return Optional.empty();  // Si l'utilisateur n'existe pas ou le mot de passe est incorrect
    }
}
