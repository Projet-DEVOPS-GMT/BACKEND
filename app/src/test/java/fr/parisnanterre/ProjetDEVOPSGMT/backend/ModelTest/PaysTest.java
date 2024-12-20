package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Pays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaysTest {

    private Pays pays;

    @BeforeEach
    public void setUp() {
        pays = new Pays();
    }

    @Test
    public void testSettersAndGetters() {
        // Définir les propriétés de pays
        pays.setId(1L);
        pays.setNom("France");
        pays.setFairScore(new BigDecimal("75.50"));

        // Vérifier les valeurs
        assertEquals(1L, pays.getId());
        assertEquals("France", pays.getNom());
        assertEquals(new BigDecimal("75.50"), pays.getFairScore());
    }

    @Test
    public void testUpdateValues() {
        // Mettre à jour les valeurs
        pays.setNom("Germany");
        pays.setFairScore(new BigDecimal("80.25"));

        // Vérifier après mise à jour
        assertEquals("Germany", pays.getNom());
        assertEquals(new BigDecimal("80.25"), pays.getFairScore());
    }
}
