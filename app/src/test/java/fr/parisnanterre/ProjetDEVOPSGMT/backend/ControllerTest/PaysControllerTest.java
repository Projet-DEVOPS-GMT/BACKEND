package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.PaysController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Pays;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.PaysService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaysController.class)
class PaysControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaysService paysService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllPays() throws Exception {
        Pays pays1 = new Pays();
        pays1.setId(1L);
        pays1.setNom("France");

        Pays pays2 = new Pays();
        pays2.setId(2L);
        pays2.setNom("Allemagne");

        Mockito.when(paysService.getAllPays()).thenReturn(Arrays.asList(pays1, pays2));

        mockMvc.perform(get("/api/pays"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nom").value("France"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nom").value("Allemagne"));
    }

    @Test
    void testGetPaysById() throws Exception {
        Pays pays = new Pays();
        pays.setId(1L);
        pays.setNom("France");

        Mockito.when(paysService.getPaysById(1L)).thenReturn(Optional.of(pays));

        mockMvc.perform(get("/api/pays/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("France"));
    }

    @Test
    void testCreatePays() throws Exception {
        Pays pays = new Pays();
        pays.setId(1L);
        pays.setNom("France");

        Mockito.when(paysService.createPays(Mockito.any(Pays.class))).thenReturn(pays);

        mockMvc.perform(post("/api/pays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pays)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("France"));
    }

    @Test
    void testUpdatePays() throws Exception {
        Pays existingPays = new Pays();
        existingPays.setId(1L);
        existingPays.setNom("France");

        Pays updatedPays = new Pays();
        updatedPays.setId(1L);
        updatedPays.setNom("Allemagne");

        Mockito.when(paysService.updatePays(Mockito.eq(1L), Mockito.any(Pays.class))).thenReturn(updatedPays);

        mockMvc.perform(put("/api/pays/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPays)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Allemagne"));
    }

    @Test
    void testDeletePays() throws Exception {
        Mockito.doNothing().when(paysService).deletePays(1L);

        mockMvc.perform(delete("/api/pays/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(paysService).deletePays(1L);
    }
}
