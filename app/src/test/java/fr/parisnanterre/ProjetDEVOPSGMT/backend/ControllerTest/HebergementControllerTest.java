package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.HebergementController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.HebergementService;
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

@WebMvcTest(HebergementController.class)
public class HebergementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private HebergementService hebergementService;

    @InjectMocks
    private HebergementController hebergementController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllHebergements() throws Exception {
        Hebergement hebergement1 = new Hebergement();
        hebergement1.setId(1L);
        hebergement1.setTypeHebergement("Hôtel");
        hebergement1.setTauxCO2(BigDecimal.valueOf(200));

        Hebergement hebergement2 = new Hebergement();
        hebergement2.setId(2L);
        hebergement2.setTypeHebergement("Appartement");
        hebergement2.setTauxCO2(BigDecimal.valueOf(150));

        when(hebergementService.getAllHebergements()).thenReturn(Arrays.asList(hebergement1, hebergement2));

        mockMvc.perform(get("/api/hebergements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].typeHebergement").value("Hôtel"))
                .andExpect(jsonPath("$[1].typeHebergement").value("Appartement"));

        verify(hebergementService, times(1)).getAllHebergements();
    }

    @Test
    public void testGetHebergementById() throws Exception {
        Hebergement hebergement = new Hebergement();
        hebergement.setId(1L);
        hebergement.setTypeHebergement("Hôtel");
        hebergement.setTauxCO2(BigDecimal.valueOf(200));

        when(hebergementService.getHebergementById(1L)).thenReturn(Optional.of(hebergement));

        mockMvc.perform(get("/api/hebergements/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeHebergement").value("Hôtel"))
                .andExpect(jsonPath("$.tauxCO2").value(200));

        verify(hebergementService, times(1)).getHebergementById(1L);
    }

    @Test
    public void testCreateHebergement() throws Exception {
        Hebergement hebergement = new Hebergement();
        hebergement.setTypeHebergement("Hôtel");
        hebergement.setTauxCO2(BigDecimal.valueOf(200));

        Hebergement savedHebergement = new Hebergement();
        savedHebergement.setId(1L);
        savedHebergement.setTypeHebergement("Hôtel");
        savedHebergement.setTauxCO2(BigDecimal.valueOf(200));

        when(hebergementService.saveHebergement(any(Hebergement.class))).thenReturn(savedHebergement);

        mockMvc.perform(post("/api/hebergements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hebergement)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeHebergement").value("Hôtel"))
                .andExpect(jsonPath("$.tauxCO2").value(200));

        verify(hebergementService, times(1)).saveHebergement(any(Hebergement.class));
    }

    @Test
    public void testUpdateHebergement() throws Exception {
        Hebergement existingHebergement = new Hebergement();
        existingHebergement.setId(1L);
        existingHebergement.setTypeHebergement("Hôtel");
        existingHebergement.setTauxCO2(BigDecimal.valueOf(200));

        Hebergement updatedHebergement = new Hebergement();
        updatedHebergement.setId(1L);
        updatedHebergement.setTypeHebergement("Appartement");
        updatedHebergement.setTauxCO2(BigDecimal.valueOf(150));

        when(hebergementService.getHebergementById(1L)).thenReturn(Optional.of(existingHebergement));
        when(hebergementService.saveHebergement(any(Hebergement.class))).thenReturn(updatedHebergement);

        mockMvc.perform(put("/api/hebergements/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedHebergement)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeHebergement").value("Appartement"))
                .andExpect(jsonPath("$.tauxCO2").value(150));

        verify(hebergementService, times(1)).getHebergementById(1L);
        verify(hebergementService, times(1)).saveHebergement(any(Hebergement.class));
    }

    @Test
    public void testDeleteHebergement() throws Exception {
        when(hebergementService.getHebergementById(1L)).thenReturn(Optional.of(new Hebergement()));

        mockMvc.perform(delete("/api/hebergements/1"))
                .andExpect(status().isNoContent());

        verify(hebergementService, times(1)).getHebergementById(1L);
        verify(hebergementService, times(1)).deleteHebergement(1L);
    }

    @Test
    public void testDeleteHebergementNotFound() throws Exception {
        when(hebergementService.getHebergementById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/hebergements/1"))
                .andExpect(status().isNotFound());

        verify(hebergementService, times(1)).getHebergementById(1L);
        verify(hebergementService, times(0)).deleteHebergement(1L);
    }
}
