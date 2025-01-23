package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoyageTest {

    private Voyage voyage;
    private Ville villeDepart;
    private Ville villeDestination;
    private User user;

    @BeforeEach
    public void setUp() {
        // Créer les villes pour le départ et la destination
        villeDepart = new Ville();
        villeDepart.setId(1L);
        villeDepart.setNom("Paris");

        villeDestination = new Ville();
        villeDestination.setId(2L);
        villeDestination.setNom("Madrid");

        // Créer un utilisateur
        user = new User();
        user.setId(1L);
        user.setNom("Dupont");
        user.setPrenom("Jean");
        user.setUsername("jean.dupont@example.com");
        user.setPassword("password123");

        // Créer un voyage
        voyage = new Voyage();
        voyage.setId(1L);
        voyage.setVilleDepart(villeDepart);
        voyage.setVilleDestination(villeDestination);
        voyage.setDateDepart(LocalDate.of(2024, 5, 1));
        voyage.setDateRetour(LocalDate.of(2024, 5, 10));
        voyage.setUser(user);
    }

    @Test
    public void testSettersAndGetters() {
        // Vérifier les valeurs du voyage
        assertEquals(1L, voyage.getId());
        assertEquals(villeDepart, voyage.getVilleDepart());
        assertEquals(villeDestination, voyage.getVilleDestination());
        assertEquals(LocalDate.of(2024, 5, 1), voyage.getDateDepart());
        assertEquals(LocalDate.of(2024, 5, 10), voyage.getDateRetour());
        assertEquals(user, voyage.getUser());
    }

    @Test
    public void testUpdateVoyage() {
        // Mettre à jour les informations du voyage
        Ville nouvelleVilleDepart = new Ville();
        nouvelleVilleDepart.setId(3L);
        nouvelleVilleDepart.setNom("Lyon");

        Ville nouvelleVilleDestination = new Ville();
        nouvelleVilleDestination.setId(4L);
        nouvelleVilleDestination.setNom("Barcelone");

        User nouvelUser = new User();
        nouvelUser.setId(2L);
        nouvelUser.setNom("Martin");
        nouvelUser.setPrenom("Claire");
        nouvelUser.setUsername("claire.martin@example.com");
        nouvelUser.setPassword("newpassword123");

        voyage.setVilleDepart(nouvelleVilleDepart);
        voyage.setVilleDestination(nouvelleVilleDestination);
        voyage.setDateDepart(LocalDate.of(2024, 6, 1));
        voyage.setDateRetour(LocalDate.of(2024, 6, 10));
        voyage.setUser(nouvelUser);

        // Vérifier les nouvelles valeurs
        assertEquals(nouvelleVilleDepart, voyage.getVilleDepart());
        assertEquals(nouvelleVilleDestination, voyage.getVilleDestination());
        assertEquals(LocalDate.of(2024, 6, 1), voyage.getDateDepart());
        assertEquals(LocalDate.of(2024, 6, 10), voyage.getDateRetour());
        assertEquals(nouvelUser, voyage.getUser());
    }
}
