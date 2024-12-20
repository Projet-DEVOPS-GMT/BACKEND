package fr.parisnanterre.ProjetDEVOPSGMT.backend.ServiceTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;

class ConsommationServiceImplTest {

    private Consommation consommation;

    @BeforeEach
    void setUp() {
        consommation = new Consommation();

        // Initialisation de Transport
        Transport transport = new Transport();
        transport.setTypeTransport("Voiture électrique"); // Utilisation de l'attribut typeTransport
        transport.setTauxCO2(120.5);
        transport.setEstimationPrix(75.0);

        // Initialisation de Voyage
        Voyage voyage = new Voyage();
        // Les objets Ville pour `villeDepart` et `villeDestination` doivent être créés et associés.
        // Pour simplifier ici, on ne les initialisera pas mais on peut les simuler en test unitaire mocké.

        consommation.setTransport(transport);
        consommation.setVoyage(voyage);

        consommation.setType("Transport");
        consommation.setPrix(new BigDecimal("100.50"));
        consommation.setTauxCO2(new BigDecimal("50.75"));
    }

    @Test
    void testConsommationFields() {
        assertNotNull(consommation.getTransport(), "Le transport ne doit pas être null.");
        assertNotNull(consommation.getVoyage(), "Le voyage ne doit pas être null.");
        assertEquals("Transport", consommation.getType(), "Le type doit être 'Transport'.");
        assertEquals(new BigDecimal("100.50"), consommation.getPrix(), "Le prix doit être 100.50.");
        assertEquals(new BigDecimal("50.75"), consommation.getTauxCO2(), "Le taux CO2 doit être 50.75.");
    }
}
