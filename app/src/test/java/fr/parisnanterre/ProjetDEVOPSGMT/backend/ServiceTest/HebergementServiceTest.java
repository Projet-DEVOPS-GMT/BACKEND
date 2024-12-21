package fr.parisnanterre.ProjetDEVOPSGMT.backend.ServiceTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.HebergementRepository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.HebergementServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HebergementServiceImplTest {

    @InjectMocks
    private HebergementServiceImpl hebergementService;

    @Mock
    private HebergementRepository hebergementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllHebergements() {
        Hebergement hebergement1 = new Hebergement();
        hebergement1.setId(1L);

        Hebergement hebergement2 = new Hebergement();
        hebergement2.setId(2L);

        List<Hebergement> hebergements = Arrays.asList(hebergement1, hebergement2);

        when(hebergementRepository.findAll()).thenReturn(hebergements);

        List<Hebergement> result = hebergementService.getAllHebergements();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(hebergementRepository, times(1)).findAll();
    }

    @Test
    void testGetHebergementById() {
        Hebergement hebergement = new Hebergement();
        hebergement.setId(1L);

        when(hebergementRepository.findById(1L)).thenReturn(Optional.of(hebergement));

        Optional<Hebergement> result = hebergementService.getHebergementById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(hebergementRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveHebergement() {
        Hebergement hebergement = new Hebergement();
        hebergement.setId(1L);

        when(hebergementRepository.save(any(Hebergement.class))).thenReturn(hebergement);

        Hebergement result = hebergementService.saveHebergement(hebergement);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(hebergementRepository, times(1)).save(hebergement);
    }

    @Test
    void testDeleteHebergement() {
        doNothing().when(hebergementRepository).deleteById(1L);

        hebergementService.deleteHebergement(1L);

        verify(hebergementRepository, times(1)).deleteById(1L);
    }
}
