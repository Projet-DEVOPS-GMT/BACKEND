package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.PaysController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Pays;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.PaysService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(PaysController.class)
public class PaysControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PaysService paysService;

    @InjectMocks
    private PaysController paysController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPays() throws Exception {
        Pays pays1 = new Pays();
        pays1.setId(1L);
        pays1.setNom("France");

        Pays pays2 = new Pays();
        pays2.setId(2L);
        pays2.setNom("Espagne");

        when(paysService.getAllPays()).thenReturn(Arrays.asList(pays1, pays2));

        mockMvc.perform(get("/api/pays"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nom").value("France"))
                .andExpect(jsonPath("$[1].nom").value("Espagne"));

        verify(paysService, times(1)).getAllPays();
    }

    @Test
    public void testGetPaysById() throws Exception {
        Pays pays = new Pays();
        pays.setId(1L);
        pays.setNom("France");

        when(paysService.getPaysById(1L)).thenReturn(Optional.of(pays));

        mockMvc.perform(get("/api/pays/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("France"));

        verify(paysService, times(1)).getPaysById(1L);
    }

    @Test
    public void testCreatePays() throws Exception {
        Pays pays = new Pays();
        pays.setNom("Italie");

        Pays savedPays = new Pays();
        savedPays.setId(1L);
        savedPays.setNom("Italie");

        when(paysService.createPays(any(Pays.class))).thenReturn(savedPays);

        mockMvc.perform(post("/api/pays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pays)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Italie"));

        verify(paysService, times(1)).createPays(any(Pays.class));
    }

    @Test
    public void testUpdatePays() throws Exception {
        Pays existingPays = new Pays();
        existingPays.setId(1L);
        existingPays.setNom("France");

        Pays updatedPays = new Pays();
        updatedPays.setId(1L);
        updatedPays.setNom("Allemagne");

        when(paysService.updatePays(eq(1L), any(Pays.class))).thenReturn(updatedPays);

        mockMvc.perform(put("/api/pays/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPays)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Allemagne"));

        verify(paysService, times(1)).updatePays(eq(1L), any(Pays.class));
    }

    @Test
    public void testDeletePays() throws Exception {
        doNothing().when(paysService).deletePays(1L);

        mockMvc.perform(delete("/api/pays/1"))
                .andExpect(status().isNoContent());

        verify(paysService, times(1)).deletePays(1L);
    }
}
