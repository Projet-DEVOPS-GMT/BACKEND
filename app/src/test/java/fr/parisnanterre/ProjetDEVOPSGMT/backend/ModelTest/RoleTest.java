package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Role;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoleTest {

    private Role role;

    @BeforeEach
    public void setUp() {
        role = new Role();
    }

    @Test
    public void testSettersAndGetters() {
        // Création d'un utilisateur pour tester la relation @ManyToOne
        User user = new User();
        user.setId(1L);
        
        // Définir les propriétés de role
        role.setId(1L);
        role.setNomProfil("Admin");
        role.setUser(user);

        // Vérifier les valeurs
        assertEquals(1L, role.getId());
        assertEquals("Admin", role.getNomProfil());
        
        // Vérifier la relation ManyToOne avec l'utilisateur
        assertNotNull(role.getUser());
        assertEquals(1L, role.getUser().getId());
    }

    @Test
    public void testUpdateValues() {
        // Mettre à jour les valeurs
        User newUser = new User();
        newUser.setId(2L);

        role.setNomProfil("User");
        role.setUser(newUser);

        // Vérifier après mise à jour
        assertEquals("User", role.getNomProfil());
        assertNotNull(role.getUser());
        assertEquals(2L, role.getUser().getId());
    }
}
