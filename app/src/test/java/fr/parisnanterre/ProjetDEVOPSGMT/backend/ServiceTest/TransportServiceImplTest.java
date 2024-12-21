package fr.parisnanterre.ProjetDEVOPSGMT.backend.ServiceTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.TransportServiceImpl;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.TransportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransportServiceTest {

    @Mock
    private TransportRepository transportRepository;

    @InjectMocks
    private TransportServiceImpl transportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTransports() {
        Transport transport1 = new Transport();
        Transport transport2 = new Transport();
        when(transportRepository.findAll()).thenReturn(Arrays.asList(transport1, transport2));

        List<Transport> transports = transportService.getAllTransports();

        assertEquals(2, transports.size());
        verify(transportRepository, times(1)).findAll();
    }

    @Test
    void testGetTransportById() {
        Transport transport = new Transport();
        when(transportRepository.findById(1L)).thenReturn(Optional.of(transport));

        Optional<Transport> result = transportService.getTransportById(1L);

        assertTrue(result.isPresent());
        assertEquals(transport, result.get());
        verify(transportRepository, times(1)).findById(1L);
    }

    @Test
    void testGetTransportsByCities() {
        LocalDate dateDepart = LocalDate.of(2024, 12, 25);
        LocalDate dateRetour = LocalDate.of(2024, 12, 30);
        Transport transport = new Transport();
        when(transportRepository.findByVilleDepartAndVilleDestinationAndDateDepartAndDateRetour(
                "Paris", "Lyon", dateDepart, dateRetour))
                .thenReturn(Arrays.asList(transport));

        List<Transport> result = transportService.getTransportsByCities("Paris", "Lyon", dateDepart, dateRetour);

        assertEquals(1, result.size());
        verify(transportRepository, times(1)).findByVilleDepartAndVilleDestinationAndDateDepartAndDateRetour(
                "Paris", "Lyon", dateDepart, dateRetour);
    }

    @Test
    void testDeleteTransport() {
        doNothing().when(transportRepository).deleteById(1L);

        transportService.deleteTransport(1L);

        verify(transportRepository, times(1)).deleteById(1L);
    }
}
