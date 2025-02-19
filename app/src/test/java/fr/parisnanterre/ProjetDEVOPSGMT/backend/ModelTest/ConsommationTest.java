package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsommationTest {

    private Consommation consommation;

    @BeforeEach
    public void setUp() {
        consommation = new Consommation();
    }

    @Test
    public void testSettersAndGetters() {
        Transport transport = new Transport();
        transport.setId(1L);

        Hebergement hebergement = new Hebergement();
        hebergement.setId(2L);

        Restauration restauration = new Restauration();
        restauration.setId(3L);

        Voyage voyage = new Voyage();
        voyage.setId(4L);

        consommation.setId(1L);
        consommation.setType("Transport");
        consommation.setPrix(BigDecimal.valueOf(100.50));
        consommation.setTauxCO2(BigDecimal.valueOf(20.75));
        consommation.setTransport(transport);
        consommation.setHebergement(hebergement);
        consommation.setRestauration(restauration);
        consommation.setVoyage(voyage);

        assertThat(consommation.getId()).isEqualTo(1L);
        assertThat(consommation.getType()).isEqualTo("Transport");
        assertThat(consommation.getPrix()).isEqualByComparingTo(BigDecimal.valueOf(100.50));
        assertThat(consommation.getTauxCO2()).isEqualByComparingTo(BigDecimal.valueOf(20.75));
        assertThat(consommation.getTransport()).isEqualTo(transport);
        assertThat(consommation.getHebergement()).isEqualTo(hebergement);
        assertThat(consommation.getRestauration()).isEqualTo(restauration);
        assertThat(consommation.getVoyage()).isEqualTo(voyage);
    }
}
