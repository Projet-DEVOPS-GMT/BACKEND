package fr.parisnanterre.ProjetDEVOPSGMT.backend.ServiceTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.VoyageRepository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.VoyageServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VoyageServiceImplTest {

    @InjectMocks
    private VoyageServiceImpl voyageService;

    @Mock
    private VoyageRepository voyageRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllVoyages() {
        Voyage voyage1 = new Voyage();
        voyage1.setId(1L);

        Voyage voyage2 = new Voyage();
        voyage2.setId(2L);

        List<Voyage> voyages = Arrays.asList(voyage1, voyage2);

        when(voyageRepository.findAll()).thenReturn(voyages);

        List<Voyage> result = voyageService.getAllVoyages();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(voyageRepository, times(1)).findAll();
    }

    @Test
    void testGetVoyageById() {
        Voyage voyage = new Voyage();
        voyage.setId(1L);

        when(voyageRepository.findById(1L)).thenReturn(Optional.of(voyage));

        Optional<Voyage> result = voyageService.getVoyageById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(voyageRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveVoyage() {
        Voyage voyage = new Voyage();
        voyage.setId(1L);

        when(voyageRepository.save(any(Voyage.class))).thenReturn(voyage);

        Voyage result = voyageService.saveVoyage(voyage);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(voyageRepository, times(1)).save(voyage);
    }

    @Test
    void testDeleteVoyage() {
        doNothing().when(voyageRepository).deleteById(1L);

        voyageService.deleteVoyage(1L);

        verify(voyageRepository, times(1)).deleteById(1L);
    }
}
