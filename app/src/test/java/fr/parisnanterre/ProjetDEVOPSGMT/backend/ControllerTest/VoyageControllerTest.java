package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.VoyageController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.VoyageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(VoyageController.class)
public class VoyageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VoyageService voyageService;

    @InjectMocks
    private VoyageController voyageController;

    @Autowired
    private ObjectMapper objectMapper;

    private Ville ville1;
    private Ville ville2;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation des objets liés
        ville1 = new Ville();
        ville1.setId(1L);
        ville1.setNom("Paris");

        ville2 = new Ville();
        ville2.setId(2L);
        ville2.setNom("Lyon");

        user = new User();
        user.setId(1L);
        user.setNom("Doe");
        user.setPrenom("John");
    }

    @Test
    public void testGetAllVoyages() throws Exception {
        Voyage voyage1 = new Voyage();
        voyage1.setId(1L);
        voyage1.setVilleDepart(ville1);
        voyage1.setVilleDestination(ville2);
        voyage1.setDateDepart(LocalDate.of(2023, 6, 1));
        voyage1.setDateRetour(LocalDate.of(2023, 6, 10));
        voyage1.setUser(user);

        Voyage voyage2 = new Voyage();
        voyage2.setId(2L);
        voyage2.setVilleDepart(ville2);
        voyage2.setVilleDestination(ville1);
        voyage2.setDateDepart(LocalDate.of(2023, 7, 1));
        voyage2.setDateRetour(LocalDate.of(2023, 7, 15));
        voyage2.setUser(user);

        when(voyageService.getAllVoyages()).thenReturn(Arrays.asList(voyage1, voyage2));

        mockMvc.perform(get("/api/voyages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].villeDepart.nom").value("Paris"))
                .andExpect(jsonPath("$[0].villeDestination.nom").value("Lyon"))
                .andExpect(jsonPath("$[1].villeDepart.nom").value("Lyon"))
                .andExpect(jsonPath("$[1].villeDestination.nom").value("Paris"));

        verify(voyageService, times(1)).getAllVoyages();
    }

    @Test
    public void testGetVoyageById() throws Exception {
        Voyage voyage = new Voyage();
        voyage.setId(1L);
        voyage.setVilleDepart(ville1);
        voyage.setVilleDestination(ville2);
        voyage.setDateDepart(LocalDate.of(2023, 6, 1));
        voyage.setDateRetour(LocalDate.of(2023, 6, 10));
        voyage.setUser(user);

        when(voyageService.getVoyageById(1L)).thenReturn(Optional.of(voyage));

        mockMvc.perform(get("/api/voyages/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.villeDepart.nom").value("Paris"))
                .andExpect(jsonPath("$.villeDestination.nom").value("Lyon"))
                .andExpect(jsonPath("$.user.nom").value("Doe"));

        verify(voyageService, times(1)).getVoyageById(1L);
    }

    @Test
    public void testCreateVoyage() throws Exception {
        Voyage voyage = new Voyage();
        voyage.setVilleDepart(ville1);
        voyage.setVilleDestination(ville2);
        voyage.setDateDepart(LocalDate.of(2023, 6, 1));
        voyage.setDateRetour(LocalDate.of(2023, 6, 10));
        voyage.setUser(user);

        Voyage savedVoyage = new Voyage();
        savedVoyage.setId(1L);
        savedVoyage.setVilleDepart(ville1);
        savedVoyage.setVilleDestination(ville2);
        savedVoyage.setDateDepart(LocalDate.of(2023, 6, 1));
        savedVoyage.setDateRetour(LocalDate.of(2023, 6, 10));
        savedVoyage.setUser(user);

        when(voyageService.saveVoyage(any(Voyage.class))).thenReturn(savedVoyage);

        mockMvc.perform(post("/api/voyages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(voyage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.villeDepart.nom").value("Paris"))
                .andExpect(jsonPath("$.villeDestination.nom").value("Lyon"))
                .andExpect(jsonPath("$.user.nom").value("Doe"));

        verify(voyageService, times(1)).saveVoyage(any(Voyage.class));
    }

    @Test
    public void testUpdateVoyage() throws Exception {
        Voyage existingVoyage = new Voyage();
        existingVoyage.setId(1L);
        existingVoyage.setVilleDepart(ville1);
        existingVoyage.setVilleDestination(ville2);
        existingVoyage.setDateDepart(LocalDate.of(2023, 6, 1));
        existingVoyage.setDateRetour(LocalDate.of(2023, 6, 10));
        existingVoyage.setUser(user);

        Voyage updatedVoyage = new Voyage();
        updatedVoyage.setId(1L);
        updatedVoyage.setVilleDepart(ville2);
        updatedVoyage.setVilleDestination(ville1);
        updatedVoyage.setDateDepart(LocalDate.of(2023, 7, 1));
        updatedVoyage.setDateRetour(LocalDate.of(2023, 7, 15));
        updatedVoyage.setUser(user);

        when(voyageService.getVoyageById(1L)).thenReturn(Optional.of(existingVoyage));
        when(voyageService.saveVoyage(any(Voyage.class))).thenReturn(updatedVoyage);

        mockMvc.perform(put("/api/voyages/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedVoyage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.villeDepart.nom").value("Lyon"))
                .andExpect(jsonPath("$.villeDestination.nom").value("Paris"));

        verify(voyageService, times(1)).getVoyageById(1L);
        verify(voyageService, times(1)).saveVoyage(any(Voyage.class));
    }

    @Test
    public void testDeleteVoyage() throws Exception {
        when(voyageService.getVoyageById(1L)).thenReturn(Optional.of(new Voyage()));

        mockMvc.perform(delete("/api/voyages/1"))
                .andExpect(status().isNoContent());

        verify(voyageService, times(1)).getVoyageById(1L);
        verify(voyageService, times(1)).deleteVoyage(1L);
    }
}
