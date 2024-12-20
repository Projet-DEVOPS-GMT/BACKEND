package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.ConsommationController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.ConsommationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConsommationController.class)
class ConsommationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsommationService consommationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllConsommations() throws Exception {
        Consommation consommation1 = new Consommation();
        consommation1.setId(1L);
        consommation1.setType("Transport");

        Consommation consommation2 = new Consommation();
        consommation2.setId(2L);
        consommation2.setType("Hébergement");

        Mockito.when(consommationService.getAllConsommations()).thenReturn(Arrays.asList(consommation1, consommation2));

        mockMvc.perform(get("/api/consommations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].type").value("Transport"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].type").value("Hébergement"));
    }

    @Test
    void testCreateConsommation() throws Exception {
        Consommation consommation = new Consommation();
        consommation.setId(1L);
        consommation.setType("Transport");
        consommation.setTauxCO2(BigDecimal.valueOf(250));

        Mockito.when(consommationService.createConsommation(Mockito.any(Consommation.class))).thenReturn(consommation);

        mockMvc.perform(post("/api/consommations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(consommation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("Transport"))
                .andExpect(jsonPath("$.tauxCO2").value(250));
    }

    @Test
    void testGetConsommationById() throws Exception {
        Consommation consommation = new Consommation();
        consommation.setId(1L);
        consommation.setType("Transport");

        Mockito.when(consommationService.getConsommationById(1L)).thenReturn(Optional.of(consommation));

        mockMvc.perform(get("/api/consommations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("Transport"));
    }

    @Test
    void testUpdateConsommation() throws Exception {
        Consommation existingConsommation = new Consommation();
        existingConsommation.setId(1L);
        existingConsommation.setType("Transport");

        Consommation updatedConsommation = new Consommation();
        updatedConsommation.setId(1L);
        updatedConsommation.setType("Hébergement");

        Mockito.when(consommationService.updateConsommation(Mockito.eq(1L), Mockito.any(Consommation.class))).thenReturn(updatedConsommation);

        mockMvc.perform(put("/api/consommations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedConsommation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("Hébergement"));
    }

    @Test
    void testDeleteConsommation() throws Exception {
        Mockito.doNothing().when(consommationService).deleteConsommation(1L);

        mockMvc.perform(delete("/api/consommations/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(consommationService).deleteConsommation(1L);
    }
}
