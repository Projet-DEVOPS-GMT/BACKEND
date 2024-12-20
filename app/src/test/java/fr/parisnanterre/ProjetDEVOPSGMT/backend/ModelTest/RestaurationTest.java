package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Restauration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurationTest {

    private Restauration restauration;

    @BeforeEach
    public void setUp() {
        restauration = new Restauration();
    }

    @Test
    public void testSettersAndGetters() {
        // Définir les propriétés de restauration
        restauration.setId(1L);
        restauration.setTypeRestaurant("Restaurant Italien");
        restauration.setPrixMoyenne(new BigDecimal("25.50"));
        restauration.setTauxCO2(new BigDecimal("12.35"));

        // Vérifier les valeurs
        assertEquals(1L, restauration.getId());
        assertEquals("Restaurant Italien", restauration.getTypeRestaurant());
        assertEquals(new BigDecimal("25.50"), restauration.getPrixMoyenne());
        assertEquals(new BigDecimal("12.35"), restauration.getTauxCO2());
    }

    @Test
    public void testUpdateValues() {
        // Mettre à jour les valeurs
        restauration.setTypeRestaurant("Restaurant Mexicain");
        restauration.setPrixMoyenne(new BigDecimal("30.00"));
        restauration.setTauxCO2(new BigDecimal("15.00"));

        // Vérifier après mise à jour
        assertEquals("Restaurant Mexicain", restauration.getTypeRestaurant());
        assertEquals(new BigDecimal("30.00"), restauration.getPrixMoyenne());
        assertEquals(new BigDecimal("15.00"), restauration.getTauxCO2());
    }
}
