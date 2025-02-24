package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Pays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VilleTest {

    private Ville ville;

    @BeforeEach
    public void setUp() {
        ville = new Ville();
    }

    @Test
    public void testSettersAndGetters() {
        Pays pays = new Pays();
        pays.setId(1L);
        pays.setNom("France");

        ville.setId(1L);
        ville.setNom("Paris");
        ville.setPays(pays);

        assertThat(ville.getId()).isEqualTo(1L);
        assertThat(ville.getNom()).isEqualTo("Paris");
        assertThat(ville.getPays()).isEqualTo(pays);
    }
}
