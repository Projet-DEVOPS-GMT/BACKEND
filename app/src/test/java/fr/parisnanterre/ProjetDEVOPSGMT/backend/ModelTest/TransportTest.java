package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TransportTest {

    private Transport transport;

    @BeforeEach
    public void setUp() {
        transport = new Transport();
    }

    @Test
    public void testSettersAndGetters() {
        transport.setId(1L);
        transport.setTypeTransport("Avion");
        transport.setTauxCO2(0.5);
        transport.setEstimationPrix(200.0);
        transport.setVilleDepart("Paris");
        transport.setVilleDestination("New York");
        transport.setDateDepart(LocalDate.of(2024, 12, 20));
        transport.setDateRetour(LocalDate.of(2024, 12, 27));

        assertEquals(1L, transport.getId());
        assertEquals("Avion", transport.getTypeTransport());
        assertEquals(0.5, transport.getTauxCO2());
        assertEquals(200.0, transport.getEstimationPrix());
        assertEquals("Paris", transport.getVilleDepart());
        assertEquals("New York", transport.getVilleDestination());
        assertEquals(LocalDate.of(2024, 12, 20), transport.getDateDepart());
        assertEquals(LocalDate.of(2024, 12, 27), transport.getDateRetour());
    }

    @Test
    public void testUpdateValues() {
        transport.setTypeTransport("Train");
        transport.setTauxCO2(0.1);
        transport.setEstimationPrix(100.0);
        transport.setVilleDepart("Lyon");
        transport.setVilleDestination("Marseille");
        transport.setDateDepart(LocalDate.of(2024, 12, 21));
        transport.setDateRetour(LocalDate.of(2024, 12, 22));

        assertEquals("Train", transport.getTypeTransport());
        assertEquals(0.1, transport.getTauxCO2());
        assertEquals(100.0, transport.getEstimationPrix());
        assertEquals("Lyon", transport.getVilleDepart());
        assertEquals("Marseille", transport.getVilleDestination());
        assertEquals(LocalDate.of(2024, 12, 21), transport.getDateDepart());
        assertEquals(LocalDate.of(2024, 12, 22), transport.getDateRetour());
    }

    @Test
    public void testSetAndGetDuration() {
        assertNull(transport.getDuration());

        LocalTime expectedDuration = LocalTime.of(2, 45);
        transport.setDuration(expectedDuration);
        assertEquals(expectedDuration, transport.getDuration());
    }
}
