package fr.parisnanterre.ProjetDEVOPSGMT.backend.ServiceTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Restauration;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.RestaurationRepository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.RestaurationServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurationServiceImplTest {

    @InjectMocks
    private RestaurationServiceImpl restaurationService;

    @Mock
    private RestaurationRepository restaurationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRestaurations() {
        Restauration restauration1 = new Restauration();
        restauration1.setId(1L);
        restauration1.setTypeRestaurant("Fast Food");

        Restauration restauration2 = new Restauration();
        restauration2.setId(2L);
        restauration2.setTypeRestaurant("Gastronomique");

        List<Restauration> restaurations = Arrays.asList(restauration1, restauration2);

        when(restaurationRepository.findAll()).thenReturn(restaurations);

        List<Restauration> result = restaurationService.getAllRestaurations();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(restaurationRepository, times(1)).findAll();
    }

    @Test
    void testGetRestaurationById() {
        Restauration restauration = new Restauration();
        restauration.setId(1L);
        restauration.setTypeRestaurant("Fast Food");

        when(restaurationRepository.findById(1L)).thenReturn(Optional.of(restauration));

        Optional<Restauration> result = restaurationService.getRestaurationById(1L);

        assertTrue(result.isPresent());
        assertEquals("Fast Food", result.get().getTypeRestaurant());
        verify(restaurationRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateRestauration() {
        Restauration restauration = new Restauration();
        restauration.setId(1L);
        restauration.setTypeRestaurant("Fast Food");
        restauration.setPrixMoyenne(BigDecimal.valueOf(15.50));

        when(restaurationRepository.save(any(Restauration.class))).thenReturn(restauration);

        Restauration result = restaurationService.createRestauration(restauration);

        assertNotNull(result);
        assertEquals("Fast Food", result.getTypeRestaurant());
        assertEquals(BigDecimal.valueOf(15.50), result.getPrixMoyenne());
        verify(restaurationRepository, times(1)).save(restauration);
    }

    @Test
    void testDeleteRestauration() {
        doNothing().when(restaurationRepository).deleteById(1L);

        restaurationService.deleteRestauration(1L);

        verify(restaurationRepository, times(1)).deleteById(1L);
    }
}
