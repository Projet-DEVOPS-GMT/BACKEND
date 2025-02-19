package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Pays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class PaysTest {

    private Pays pays;

    @BeforeEach
    public void setUp() {
        pays = new Pays();
    }

    @Test
    public void testSettersAndGetters() {
        pays.setId(1L);
        pays.setNom("France");
        pays.setFairScore(BigDecimal.valueOf(85.75));

        assertThat(pays.getId()).isEqualTo(1L);
        assertThat(pays.getNom()).isEqualTo("France");
        assertThat(pays.getFairScore()).isEqualByComparingTo(BigDecimal.valueOf(85.75));
    }
}
