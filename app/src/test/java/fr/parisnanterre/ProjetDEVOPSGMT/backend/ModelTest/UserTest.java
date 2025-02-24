package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testSettersAndGetters() {
        // Définir les propriétés de l'utilisateur
        user.setId(1L);
        user.setNom("Dupont");
        user.setPrenom("Jean");
        user.setUsername("jean.dupont@example.com");
        user.setPassword("motdepasse123");

        // Vérifier les valeurs
        assertEquals(1L, user.getId());
        assertEquals("Dupont", user.getNom());
        assertEquals("Jean", user.getPrenom());
        assertEquals("jean.dupont@example.com", user.getUsername());
        assertEquals("motdepasse123", user.getPassword());
    }

    @Test
    public void testUpdateValues() {
        // Mettre à jour les valeurs
        user.setNom("Martin");
        user.setPrenom("Sophie");
        user.setUsername("sophie.martin@example.com");
        user.setPassword("nouveaumotdepasse");

        // Vérifier après mise à jour
        assertEquals("Martin", user.getNom());
        assertEquals("Sophie", user.getPrenom());
        assertEquals("sophie.martin@example.com", user.getUsername());
        assertEquals("nouveaumotdepasse", user.getPassword());
    }
}
