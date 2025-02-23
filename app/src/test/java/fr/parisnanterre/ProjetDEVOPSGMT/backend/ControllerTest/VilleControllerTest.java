package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.VilleController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.VilleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(VilleController.class)
public class VilleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VilleService villeService;

    @InjectMocks
    private VilleController villeController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllVilles() throws Exception {
        Ville ville1 = new Ville();
        ville1.setId(1L);
        ville1.setNom("Paris");

        Ville ville2 = new Ville();
        ville2.setId(2L);
        ville2.setNom("Lyon");

        when(villeService.getAllVilles()).thenReturn(Arrays.asList(ville1, ville2));

        mockMvc.perform(get("/api/villes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nom").value("Paris"))
                .andExpect(jsonPath("$[1].nom").value("Lyon"));

        verify(villeService, times(1)).getAllVilles();
    }

    @Test
    public void testGetVilleById() throws Exception {
        Ville ville = new Ville();
        ville.setId(1L);
        ville.setNom("Paris");

        when(villeService.getVilleById(1L)).thenReturn(Optional.of(ville));

        mockMvc.perform(get("/api/villes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Paris"));

        verify(villeService, times(1)).getVilleById(1L);
    }

    @Test
    public void testGetVilleByIdNotFound() throws Exception {
        when(villeService.getVilleById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/villes/1"))
                .andExpect(status().isNotFound());

        verify(villeService, times(1)).getVilleById(1L);
    }

    @Test
    public void testCreateVille() throws Exception {
        Ville ville = new Ville();
        ville.setNom("Paris");

        Ville savedVille = new Ville();
        savedVille.setId(1L);
        savedVille.setNom("Paris");

        when(villeService.createVille(any(Ville.class))).thenReturn(savedVille);

        mockMvc.perform(post("/api/villes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ville)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Paris"));

        verify(villeService, times(1)).createVille(any(Ville.class));
    }

    @Test
    public void testUpdateVille() throws Exception {
        Ville existingVille = new Ville();
        existingVille.setId(1L);
        existingVille.setNom("Paris");

        Ville updatedVille = new Ville();
        updatedVille.setId(1L);
        updatedVille.setNom("Lyon");

        when(villeService.updateVille(eq(1L), any(Ville.class))).thenReturn(updatedVille);

        mockMvc.perform(put("/api/villes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedVille)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Lyon"));

        verify(villeService, times(1)).updateVille(eq(1L), any(Ville.class));
    }

    @Test
    public void testDeleteVille() throws Exception {
        doNothing().when(villeService).deleteVille(1L);

        mockMvc.perform(delete("/api/villes/1"))
                .andExpect(status().isNoContent());

        verify(villeService, times(1)).deleteVille(1L);
    }

    @Test
    public void testGetVilleByName() throws Exception {
        Ville ville = new Ville();
        ville.setId(1L);
        ville.setNom("Paris");

        when(villeService.getVilleByName("Paris")).thenReturn(ville);

        mockMvc.perform(get("/api/villes/Paris"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Paris"));

        verify(villeService, times(1)).getVilleByName("Paris");
    }

    @Test
    public void testGetVilleByNameNotFound() throws Exception {
        when(villeService.getVilleByName("UnknownVille")).thenReturn(null);

        mockMvc.perform(get("/api/villes/UnknownVille"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Ville non trouvée."));

        verify(villeService, times(1)).getVilleByName("UnknownVille");
    }
}
