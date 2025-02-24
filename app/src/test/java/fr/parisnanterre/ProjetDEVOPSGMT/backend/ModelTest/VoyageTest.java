package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class VoyageTest {

    private Voyage voyage;

    @BeforeEach
    public void setUp() {
        voyage = new Voyage();
    }

    @Test
    public void testSettersAndGetters() {
        User user = new User();
        user.setId(1L);

        voyage.setId(1L);
        voyage.setVilleDepart("Paris");
        voyage.setVilleDestination("New York");
        voyage.setDateDepart(LocalDate.of(2024, 6, 15));
        voyage.setDateRetour(LocalDate.of(2024, 6, 30));
        voyage.setUser(user);

        assertThat(voyage.getId()).isEqualTo(1L);
        assertThat(voyage.getVilleDepart()).isEqualTo("Paris");
        assertThat(voyage.getVilleDestination()).isEqualTo("New York");
        assertThat(voyage.getDateDepart()).isEqualTo(LocalDate.of(2024, 6, 15));
        assertThat(voyage.getDateRetour()).isEqualTo(LocalDate.of(2024, 6, 30));
        assertThat(voyage.getUser()).isEqualTo(user);
    }
}
