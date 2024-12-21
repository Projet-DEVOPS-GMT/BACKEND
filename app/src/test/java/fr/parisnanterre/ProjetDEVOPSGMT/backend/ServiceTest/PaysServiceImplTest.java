package fr.parisnanterre.ProjetDEVOPSGMT.backend.ServiceTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Pays;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.PaysRepository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.PaysServiceImpl;

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

class PaysServiceImplTest {

    @InjectMocks
    private PaysServiceImpl paysService;

    @Mock
    private PaysRepository paysRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPays() {
        Pays pays1 = new Pays();
        pays1.setId(1L);
        pays1.setNom("France");

        Pays pays2 = new Pays();
        pays2.setId(2L);
        pays2.setNom("Germany");

        List<Pays> paysList = Arrays.asList(pays1, pays2);

        when(paysRepository.findAll()).thenReturn(paysList);

        List<Pays> result = paysService.getAllPays();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(paysRepository, times(1)).findAll();
    }

    @Test
    void testGetPaysById() {
        Pays pays = new Pays();
        pays.setId(1L);
        pays.setNom("France");

        when(paysRepository.findById(1L)).thenReturn(Optional.of(pays));

        Optional<Pays> result = paysService.getPaysById(1L);

        assertTrue(result.isPresent());
        assertEquals("France", result.get().getNom());
        verify(paysRepository, times(1)).findById(1L);
    }

    @Test
    void testCreatePays() {
        Pays pays = new Pays();
        pays.setId(1L);
        pays.setNom("France");

        when(paysRepository.save(any(Pays.class))).thenReturn(pays);

        Pays result = paysService.createPays(pays);

        assertNotNull(result);
        assertEquals("France", result.getNom());
        verify(paysRepository, times(1)).save(pays);
    }

   
    @Test
    void testDeletePays() {
        doNothing().when(paysRepository).deleteById(1L);

        paysService.deletePays(1L);

        verify(paysRepository, times(1)).deleteById(1L);
    }
}
