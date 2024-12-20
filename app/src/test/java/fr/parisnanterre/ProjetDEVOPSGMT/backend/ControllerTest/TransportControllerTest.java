package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.TransportController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.TransportService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransportControllerTest {

    @Mock
    private TransportService transportService;

    @InjectMocks
    private TransportController transportController;

    public TransportControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings({ "deprecation", "null" })
    @Test
    void testCompareTransport() {
        Transport transport = new Transport();
        LocalDate dateDepart = LocalDate.of(2024, 12, 25);
        LocalDate dateRetour = LocalDate.of(2024, 12, 30);

        when(transportService.getTransportsByCities("Paris", "Lyon", dateDepart, dateRetour))
                .thenReturn(Arrays.asList(transport));

        ResponseEntity<List<Transport>> response = transportController.compareTransport("Paris", "Lyon",
                "2024-12-25", "2024-12-30");

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(transportService, times(1)).getTransportsByCities("Paris", "Lyon", dateDepart, dateRetour);
    }

    @SuppressWarnings("deprecation")
    @Test
    void testGetTransportById() {
        Transport transport = new Transport();
        when(transportService.getTransportById(1L)).thenReturn(Optional.of(transport));

        ResponseEntity<Transport> response = transportController.getTransportById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(transportService, times(1)).getTransportById(1L);
    }

    @SuppressWarnings("deprecation")
    @Test
    void testGetTransportById_NotFound() {
        when(transportService.getTransportById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Transport> response = transportController.getTransportById(1L);

        assertEquals(404, response.getStatusCodeValue());
        verify(transportService, times(1)).getTransportById(1L);
    }

    @SuppressWarnings("deprecation")
    @Test
    void testDeleteTransport() {
        when(transportService.getTransportById(1L)).thenReturn(Optional.of(new Transport()));
        doNothing().when(transportService).deleteTransport(1L);

        ResponseEntity<Void> response = transportController.deleteTransport(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(transportService, times(1)).deleteTransport(1L);
    }

    @SuppressWarnings("deprecation")
    @Test
    void testDeleteTransport_NotFound() {
        when(transportService.getTransportById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = transportController.deleteTransport(1L);

        assertEquals(404, response.getStatusCodeValue());
        verify(transportService, never()).deleteTransport(anyLong());
    }
}

