package fr.parisnanterre.ProjetDEVOPSGMT.backend.ServiceTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Pays;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.VilleRepository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.VilleServiceImpl;

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

class VilleServiceImplTest {

    @InjectMocks
    private VilleServiceImpl villeService;

    @Mock
    private VilleRepository villeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllVilles() {
        Ville ville1 = new Ville();
        ville1.setId(1L);
        ville1.setNom("Paris");

        Ville ville2 = new Ville();
        ville2.setId(2L);
        ville2.setNom("Berlin");

        List<Ville> villes = Arrays.asList(ville1, ville2);

        when(villeRepository.findAll()).thenReturn(villes);

        List<Ville> result = villeService.getAllVilles();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(villeRepository, times(1)).findAll();
    }

    @Test
    void testGetVilleById() {
        Ville ville = new Ville();
        ville.setId(1L);
        ville.setNom("Paris");

        when(villeRepository.findById(1L)).thenReturn(Optional.of(ville));

        Optional<Ville> result = villeService.getVilleById(1L);

        assertTrue(result.isPresent());
        assertEquals("Paris", result.get().getNom());
        verify(villeRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateVille() {
        Ville ville = new Ville();
        ville.setId(1L);
        ville.setNom("Paris");

        when(villeRepository.save(any(Ville.class))).thenReturn(ville);

        Ville result = villeService.createVille(ville);

        assertNotNull(result);
        assertEquals("Paris", result.getNom());
        verify(villeRepository, times(1)).save(ville);
    }

    @Test
    void testDeleteVille() {
        doNothing().when(villeRepository).deleteById(1L);

        villeService.deleteVille(1L);

        verify(villeRepository, times(1)).deleteById(1L);
    }
}
