package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Pays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VilleTest {

    private Ville ville;
    private Pays pays;

    @BeforeEach
    public void setUp() {
        // Créer un pays pour associer à la ville
        pays = new Pays();
        pays.setId(1L);
        pays.setNom("France");
        pays.setFairScore(new BigDecimal("0.75"));

        // Créer une ville et associer le pays
        ville = new Ville();
        ville.setId(1L);
        ville.setNom("Paris");
        ville.setPays(pays);
    }

    @Test
    public void testSettersAndGetters() {
        // Vérifier les valeurs de la ville et du pays
        assertEquals(1L, ville.getId());
        assertEquals("Paris", ville.getNom());
        assertEquals(pays, ville.getPays());
        assertEquals(1L, ville.getPays().getId());
        assertEquals("France", ville.getPays().getNom());
    }

    @Test
    public void testUpdateVille() {
        // Mettre à jour la ville et son pays
        Pays newPays = new Pays();
        newPays.setId(2L);
        newPays.setNom("Espagne");
        newPays.setFairScore(new BigDecimal("0.80"));

        ville.setNom("Madrid");
        ville.setPays(newPays);

        // Vérifier les nouvelles valeurs
        assertEquals("Madrid", ville.getNom());
        assertEquals(newPays, ville.getPays());
        assertEquals(2L, ville.getPays().getId());
        assertEquals("Espagne", ville.getPays().getNom());
    }
}
