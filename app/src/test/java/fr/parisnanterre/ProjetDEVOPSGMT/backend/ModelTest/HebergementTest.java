package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class HebergementTest {

    private Hebergement hebergement;

    @BeforeEach
    public void setUp() {
        hebergement = new Hebergement();
    }

    @Test
    public void testSettersAndGetters() {
        Ville ville = new Ville();
        ville.setId(1L);
        ville.setNom("Paris");

        hebergement.setId(1L);
        hebergement.setTypeHebergement("Hôtel");
        hebergement.setPrix(BigDecimal.valueOf(150.75));
        hebergement.setTauxCO2(BigDecimal.valueOf(10.5));
        hebergement.setDescription("Un hôtel de luxe au centre-ville");
        hebergement.setEquipement("Piscine, Wi-Fi gratuit");
        hebergement.setPhoto("hotel.jpg");
        hebergement.setConditionsReservation("Annulation gratuite 48h avant l'arrivée");
        hebergement.setVille(ville);

        assertThat(hebergement.getId()).isEqualTo(1L);
        assertThat(hebergement.getTypeHebergement()).isEqualTo("Hôtel");
        assertThat(hebergement.getPrix()).isEqualByComparingTo(BigDecimal.valueOf(150.75));
        assertThat(hebergement.getTauxCO2()).isEqualByComparingTo(BigDecimal.valueOf(10.5));
        assertThat(hebergement.getDescription()).isEqualTo("Un hôtel de luxe au centre-ville");
        assertThat(hebergement.getEquipement()).isEqualTo("Piscine, Wi-Fi gratuit");
        assertThat(hebergement.getPhoto()).isEqualTo("hotel.jpg");
        assertThat(hebergement.getConditionsReservation()).isEqualTo("Annulation gratuite 48h avant l'arrivée");
        assertThat(hebergement.getVille()).isEqualTo(ville);
    }
}
