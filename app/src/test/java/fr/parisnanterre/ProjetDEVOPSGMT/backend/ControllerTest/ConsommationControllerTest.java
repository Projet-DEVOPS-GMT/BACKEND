package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.ConsommationController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.ConsommationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConsommationController.class)
public class ConsommationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ConsommationService consommationService;

    @InjectMocks
    private ConsommationController consommationController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllConsommations() throws Exception {
        Consommation consommation1 = new Consommation();
        consommation1.setId(1L);
        consommation1.setType("Transport");

        Consommation consommation2 = new Consommation();
        consommation2.setId(2L);
        consommation2.setType("Hébergement");

        when(consommationService.getAllConsommations()).thenReturn(Arrays.asList(consommation1, consommation2));

        mockMvc.perform(get("/api/consommations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].type").value("Transport"))
                .andExpect(jsonPath("$[1].type").value("Hébergement"));

        verify(consommationService, times(1)).getAllConsommations();
    }

    @Test
    public void testCreateConsommation() throws Exception {
        Consommation consommation = new Consommation();
        consommation.setType("Restauration");
        consommation.setPrix(BigDecimal.valueOf(100));

        Consommation savedConsommation = new Consommation();
        savedConsommation.setId(1L);
        savedConsommation.setType("Restauration");
        savedConsommation.setPrix(BigDecimal.valueOf(100));
        savedConsommation.setTauxCO2(BigDecimal.valueOf(300)); // 3 * prix

        when(consommationService.createConsommation(any(Consommation.class))).thenReturn(savedConsommation);

        mockMvc.perform(post("/api/consommations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(consommation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("Restauration"))
                .andExpect(jsonPath("$.tauxCO2").value(300));

        verify(consommationService, times(1)).createConsommation(any(Consommation.class));
    }

    @Test
    public void testGetConsommationById() throws Exception {
        Consommation consommation = new Consommation();
        consommation.setId(1L);
        consommation.setType("Hébergement");
        consommation.setPrix(BigDecimal.valueOf(150));
        consommation.setTauxCO2(BigDecimal.valueOf(2250)); // 15 * prix

        when(consommationService.getConsommationById(1L)).thenReturn(Optional.of(consommation));

        mockMvc.perform(get("/api/consommations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("Hébergement"))
                .andExpect(jsonPath("$.tauxCO2").value(2250));

        verify(consommationService, times(1)).getConsommationById(1L);
    }

    @Test
    public void testUpdateConsommation() throws Exception {
        Consommation existingConsommation = new Consommation();
        existingConsommation.setId(1L);
        existingConsommation.setType("Hébergement");
        existingConsommation.setPrix(BigDecimal.valueOf(150));
        existingConsommation.setTauxCO2(BigDecimal.valueOf(2250));

        Consommation updatedConsommation = new Consommation();
        updatedConsommation.setId(1L);
        updatedConsommation.setType("Transport");
        updatedConsommation.setPrix(BigDecimal.valueOf(100));
        updatedConsommation.setTauxCO2(BigDecimal.valueOf(41)); // 41 CO2 (dummy calculation)

        when(consommationService.updateConsommation(eq(1L), any(Consommation.class))).thenReturn(updatedConsommation);

        mockMvc.perform(put("/api/consommations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedConsommation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("Transport"))
                .andExpect(jsonPath("$.tauxCO2").value(41));

        verify(consommationService, times(1)).updateConsommation(eq(1L), any(Consommation.class));
    }

    @Test
    public void testDeleteConsommation() throws Exception {
        doNothing().when(consommationService).deleteConsommation(1L);

        mockMvc.perform(delete("/api/consommations/1"))
                .andExpect(status().isNoContent());

        verify(consommationService, times(1)).deleteConsommation(1L);
    }
}
