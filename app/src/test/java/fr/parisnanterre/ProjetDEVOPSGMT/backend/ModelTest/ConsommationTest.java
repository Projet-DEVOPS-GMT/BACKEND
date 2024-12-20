package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Restauration;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConsommationTest {

    private Consommation consommation;

    @BeforeEach
    public void setUp() {
        consommation = new Consommation();
    }

    @Test
    public void testSettersAndGetters() {
        // Création des entités associées
        Transport transport = new Transport();
        transport.setId(1L);

        Hebergement hebergement = new Hebergement();
        hebergement.setId(2L);

        Restauration restauration = new Restauration();
        restauration.setId(3L);

        Voyage voyage = new Voyage();
        voyage.setId(4L);

        // Définir les propriétés de consommation
        consommation.setId(100L);
        consommation.setType("Transport");
        consommation.setPrix(new BigDecimal("150.50"));
        consommation.setTauxCO2(new BigDecimal("20.35"));
        consommation.setTransport(transport);
        consommation.setHebergement(hebergement);
        consommation.setRestauration(restauration);
        consommation.setVoyage(voyage);

        // Vérifier les valeurs
        assertEquals(100L, consommation.getId());
        assertEquals("Transport", consommation.getType());
        assertEquals(new BigDecimal("150.50"), consommation.getPrix());
        assertEquals(new BigDecimal("20.35"), consommation.getTauxCO2());

        // Vérifier les relations
        assertNotNull(consommation.getTransport());
        assertEquals(1L, consommation.getTransport().getId());

        assertNotNull(consommation.getHebergement());
        assertEquals(2L, consommation.getHebergement().getId());

        assertNotNull(consommation.getRestauration());
        assertEquals(3L, consommation.getRestauration().getId());

        assertNotNull(consommation.getVoyage());
        assertEquals(4L, consommation.getVoyage().getId());
    }

    @Test
    public void testNullRelationships() {
        // Par défaut, les relations doivent être nulles
        assertNull(consommation.getTransport());
        assertNull(consommation.getHebergement());
        assertNull(consommation.getRestauration());
        assertNull(consommation.getVoyage());
    }

    @Test
    public void testUpdateValues() {
        // Mettre à jour les valeurs
        consommation.setType("Hebergement");
        consommation.setPrix(new BigDecimal("200.00"));
        consommation.setTauxCO2(new BigDecimal("15.00"));

        // Vérifier après mise à jour
        assertEquals("Hebergement", consommation.getType());
        assertEquals(new BigDecimal("200.00"), consommation.getPrix());
        assertEquals(new BigDecimal("15.00"), consommation.getTauxCO2());
    }
}
