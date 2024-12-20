package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HebergementTest {

    private Hebergement hebergement;

    @BeforeEach
    public void setUp() {
        hebergement = new Hebergement();
    }

    @Test
    public void testSettersAndGetters() {
        // Définir les propriétés de Hebergement
        hebergement.setId(1L);
        hebergement.setTypeHebergement("Hôtel");
        hebergement.setPrix(new BigDecimal("99.99"));
        hebergement.setTauxCO2(new BigDecimal("10.50"));
        hebergement.setDescription("Hôtel 3 étoiles situé en centre-ville.");
        hebergement.setEquipement("Wi-Fi, Climatisation, Piscine");
        hebergement.setPhoto("photo_url");
        hebergement.setConditionsReservation("Annulation gratuite jusqu'à 24h avant l'arrivée.");

        // Vérifier les valeurs des propriétés
        assertEquals(1L, hebergement.getId());
        assertEquals("Hôtel", hebergement.getTypeHebergement());
        assertEquals(new BigDecimal("99.99"), hebergement.getPrix());
        assertEquals(new BigDecimal("10.50"), hebergement.getTauxCO2());
        assertEquals("Hôtel 3 étoiles situé en centre-ville.", hebergement.getDescription());
        assertEquals("Wi-Fi, Climatisation, Piscine", hebergement.getEquipement());
        assertEquals("photo_url", hebergement.getPhoto());
        assertEquals("Annulation gratuite jusqu'à 24h avant l'arrivée.", hebergement.getConditionsReservation());
    }

    @Test
    public void testNullProperties() {
        // Vérifier que les propriétés sont nulles au départ si elles ne sont pas définies
        assertNull(hebergement.getTypeHebergement());
        assertNull(hebergement.getPrix());
        assertNull(hebergement.getTauxCO2());
        assertNull(hebergement.getDescription());
        assertNull(hebergement.getEquipement());
        assertNull(hebergement.getPhoto());
        assertNull(hebergement.getConditionsReservation());
    }

    @Test
    public void testUpdateValues() {
        // Mettre à jour les valeurs
        hebergement.setTypeHebergement("Chambre d'hôtes");
        hebergement.setPrix(new BigDecimal("75.00"));
        hebergement.setTauxCO2(new BigDecimal("5.50"));

        // Vérifier après mise à jour
        assertEquals("Chambre d'hôtes", hebergement.getTypeHebergement());
        assertEquals(new BigDecimal("75.00"), hebergement.getPrix());
        assertEquals(new BigDecimal("5.50"), hebergement.getTauxCO2());
    }
}
