package fr.parisnanterre.ProjetDEVOPSGMT.backend.ServiceTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.ConsommationRepository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.ConsommationServiceImpl;

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

class ConsommationServiceImplTest {

    @InjectMocks
    private ConsommationServiceImpl consommationService;

    @Mock
    private ConsommationRepository consommationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllConsommations() {
        Consommation consommation1 = new Consommation();
        consommation1.setId(1L);

        Consommation consommation2 = new Consommation();
        consommation2.setId(2L);

        List<Consommation> consommations = Arrays.asList(consommation1, consommation2);

        when(consommationRepository.findAll()).thenReturn(consommations);

        List<Consommation> result = consommationService.getAllConsommations();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(consommationRepository, times(1)).findAll();
    }

    @Test
    void testCreateConsommation() {
        Consommation consommation = new Consommation();
        consommation.setId(1L);

        when(consommationRepository.save(any(Consommation.class))).thenReturn(consommation);

        Consommation result = consommationService.createConsommation(consommation);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(consommationRepository, times(1)).save(consommation);
    }

    @Test
    void testGetConsommationById() {
        Consommation consommation = new Consommation();
        consommation.setId(1L);

        when(consommationRepository.findById(1L)).thenReturn(Optional.of(consommation));

        Optional<Consommation> result = consommationService.getConsommationById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(consommationRepository, times(1)).findById(1L);
    }
   
    @Test
    void testDeleteConsommation() {
        doNothing().when(consommationRepository).deleteById(1L);

        consommationService.deleteConsommation(1L);

        verify(consommationRepository, times(1)).deleteById(1L);
    }
}
