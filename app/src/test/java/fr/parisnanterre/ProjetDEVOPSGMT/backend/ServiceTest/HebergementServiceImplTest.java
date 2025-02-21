package fr.parisnanterre.ProjetDEVOPSGMT.backend.ServiceTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.HebergementRepository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.HebergementServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class HebergementServiceImplTest {

    @Mock
    private HebergementRepository hebergementRepository;

    @InjectMocks
    private HebergementServiceImpl hebergementService;

    private Hebergement hebergement;
    private Ville ville;

    @BeforeEach
    void setUp() {
        ville = new Ville();
        ville.setId(1L);
        ville.setNom("Paris");

        hebergement = new Hebergement();
        hebergement.setId(1L);
        hebergement.setVille(ville);
        hebergement.setTypeHebergement("Hotel");
        hebergement.setPrix(new BigDecimal("100.50"));
        hebergement.setTauxCO2(new BigDecimal("20.5"));
        hebergement.setDescription("Un hôtel confortable");
        hebergement.setEquipement("WiFi, TV, Climatisation");
        hebergement.setPhoto("image.jpg");
        hebergement.setConditionsReservation("Annulation gratuite");
    }

    @Test
    void testGetAllHebergements() {
        when(hebergementRepository.findAll()).thenReturn(List.of(hebergement));
        List<Hebergement> hebergements = hebergementService.getAllHebergements();
        assertFalse(hebergements.isEmpty());
        assertEquals(1, hebergements.size());
    }

    @Test
    void testGetHebergementById() {
        when(hebergementRepository.findById(1L)).thenReturn(Optional.of(hebergement));
        Optional<Hebergement> foundHebergement = hebergementService.getHebergementById(1L);
        assertTrue(foundHebergement.isPresent());
        // assertEquals("Hotel Test", foundHebergement.get().getNom());
        assertEquals(new BigDecimal("100.50"), foundHebergement.get().getPrix());
        assertEquals("WiFi, TV, Climatisation", foundHebergement.get().getEquipement());
    }

    @Test
    void testGetHebergementsByCriteria() {
        when(hebergementRepository.findByVilleAndTypeHebergement(ville, "Hotel")).thenReturn(List.of(hebergement));
        List<Hebergement> results = hebergementService.getHebergementsByCriteria(ville, "Hotel");
        assertEquals(1, results.size());
        // assertEquals("Hotel Test", results.get(0).getNom());
        String typeHebergement = "Hotel";
        Hebergement hebergement1 = new Hebergement();
        Hebergement hebergement2 = new Hebergement();

        Mockito.when(hebergementRepository.findAll())
                .thenReturn(Arrays.asList(hebergement1, hebergement2));

        List<Hebergement> result = hebergementService.getHebergementsByCriteria(null, null);

        assertEquals(2, result.size());
        assertTrue(result.contains(hebergement1));
        assertTrue(result.contains(hebergement2));
        
    }
 
    @Test
    void testSaveHebergement() {
        when(hebergementRepository.save(any(Hebergement.class))).thenReturn(hebergement);
        Hebergement savedHebergement = hebergementService.saveHebergement(hebergement);
        assertNotNull(savedHebergement);
        // assertEquals("Hotel Test", savedHebergement.getNom());
        assertEquals(new BigDecimal("100.50"), savedHebergement.getPrix());
        assertEquals("Annulation gratuite", savedHebergement.getConditionsReservation());
    }

    @Test
    void testDeleteHebergement() {
        doNothing().when(hebergementRepository).deleteById(1L);
        hebergementService.deleteHebergement(1L);
        verify(hebergementRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllTypeHebergements() {
        when(hebergementRepository.findDistinctTypeHebergement()).thenReturn(List.of("Hotel", "Appartement"));
        List<String> types = hebergementService.getAllTypeHebergements();
        assertEquals(2, types.size());
        assertTrue(types.contains("Hotel"));
        assertTrue(types.contains("Appartement"));
    }
}
