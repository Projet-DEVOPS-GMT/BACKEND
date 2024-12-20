package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.HebergementController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.HebergementService;
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

@WebMvcTest(HebergementController.class)
class HebergementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HebergementService hebergementService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllHebergements() throws Exception {
        Hebergement hebergement1 = new Hebergement();
        hebergement1.setId(1L);
        hebergement1.setTypeHebergement("Hotel");

        Hebergement hebergement2 = new Hebergement();
        hebergement2.setId(2L);
        hebergement2.setTypeHebergement("Appartement");

        Mockito.when(hebergementService.getAllHebergements()).thenReturn(Arrays.asList(hebergement1, hebergement2));

        mockMvc.perform(get("/api/hebergements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].typeHebergement").value("Hotel"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].typeHebergement").value("Appartement"));
    }

    @Test
    void testGetHebergementById() throws Exception {
        Hebergement hebergement = new Hebergement();
        hebergement.setId(1L);
        hebergement.setTypeHebergement("Hotel");

        Mockito.when(hebergementService.getHebergementById(1L)).thenReturn(Optional.of(hebergement));

        mockMvc.perform(get("/api/hebergements/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeHebergement").value("Hotel"));
    }

    @Test
    void testCreateHebergement() throws Exception {
        Hebergement hebergement = new Hebergement();
        hebergement.setId(1L);
        hebergement.setTypeHebergement("Hotel");

        Mockito.when(hebergementService.saveHebergement(Mockito.any(Hebergement.class))).thenReturn(hebergement);

        mockMvc.perform(post("/api/hebergements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hebergement)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeHebergement").value("Hotel"));
    }

    @Test
    void testUpdateHebergement() throws Exception {
        Hebergement existingHebergement = new Hebergement();
        existingHebergement.setId(1L);
        existingHebergement.setTypeHebergement("Hotel");

        Hebergement updatedHebergement = new Hebergement();
        updatedHebergement.setId(1L);
        updatedHebergement.setTypeHebergement("Appartement");

        Mockito.when(hebergementService.getHebergementById(1L)).thenReturn(Optional.of(existingHebergement));
        Mockito.when(hebergementService.saveHebergement(Mockito.any(Hebergement.class))).thenReturn(updatedHebergement);

        mockMvc.perform(put("/api/hebergements/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedHebergement)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeHebergement").value("Appartement"));
    }

    @Test
    void testDeleteHebergement() throws Exception {
        Mockito.when(hebergementService.getHebergementById(1L)).thenReturn(Optional.of(new Hebergement()));

        mockMvc.perform(delete("/api/hebergements/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(hebergementService).deleteHebergement(1L);
    }
}
