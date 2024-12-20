/*package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.ConsommationController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.ConsommationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ConsommationControllerTest {

    @Mock
    private ConsommationService consommationService;  // Mock du service

    @InjectMocks
    private ConsommationController consommationController;  // Injecter le service dans le contrôleur

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(consommationController).build();  // Setup de MockMvc
    }

    @Test
    public void testGetAllConsommations() throws Exception {
        Consommation consommation1 = new Consommation();
        consommation1.setId(1L);
        consommation1.setTauxCO2(new BigDecimal("10.5"));
        consommation1.setPrix(new BigDecimal("100.00"));

        Consommation consommation2 = new Consommation();
        consommation2.setId(2L);
        consommation2.setTauxCO2(new BigDecimal("12.0"));
        consommation2.setPrix(new BigDecimal("120.00"));

        when(consommationService.getAllConsommations()).thenReturn(Arrays.asList(consommation1, consommation2));  // Mock du service

        mockMvc.perform(get("/api/consommations"))
                .andExpect(status().isOk())  // Vérifie le statut 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Vérifie que le type de contenu est JSON
                .andExpect(jsonPath("$[0].id").value(1L))  // Vérifie le premier élément
                .andExpect(jsonPath("$[1].id").value(2L));  // Vérifie le second élément
    }

    @Test
    public void testGetConsommationById() throws Exception {
        Consommation consommation = new Consommation();
        consommation.setId(1L);
        consommation.setTauxCO2(new BigDecimal("15.5"));
        consommation.setPrix(new BigDecimal("200.00"));

        when(consommationService.getConsommationById(1L)).thenReturn(Optional.of(consommation));  // Mock du service

        mockMvc.perform(get("/api/consommations/1"))
                .andExpect(status().isOk())  // Vérifie le statut 200 OK
                .andExpect(jsonPath("$.id").value(1L))  // Vérifie l'ID
                .andExpect(jsonPath("$.tauxCO2").value(15.5));  // Vérifie le tauxCO2
    }

    @Test
    public void testGetConsommationByIdNotFound() throws Exception {
        when(consommationService.getConsommationById(999L)).thenReturn(Optional.empty());  // Mock du service

        mockMvc.perform(get("/api/consommations/999"))
                .andExpect(status().isNotFound());  // Vérifie le statut 404 Not Found
    }

    @Test
    public void testCreateConsommation() throws Exception {
        // Création d'un objet Consommation
        Consommation consommation = new Consommation();
        consommation.setId(1L);  // Ajoutez un identifiant ici
        consommation.setTauxCO2(new BigDecimal("20.5"));
        consommation.setPrix(new BigDecimal("300.00"));

        // Mock du service
        when(consommationService.createConsommation(any(Consommation.class))).thenReturn(consommation);

        // Requête POST pour créer une nouvelle consommation
        mockMvc.perform(post("/api/consommations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"tauxCO2\":20.5,\"prix\":300.00}"))
            .andExpect(status().isCreated())  // Vérifie que le statut est 201 Created
            .andExpect(jsonPath("$.id").value(1L))  // Vérifie que l'id est présent et correct
            .andExpect(jsonPath("$.tauxCO2").value(20.5))  // Vérifie le tauxCO2
            .andExpect(jsonPath("$.prix").value(300.00));  // Vérifie le prix
}


    @Test
    public void testUpdateConsommation() throws Exception {
        Consommation consommation = new Consommation();
        consommation.setId(1L);
        consommation.setTauxCO2(new BigDecimal("25.5"));
        consommation.setPrix(new BigDecimal("500.00"));

        when(consommationService.updateConsommation(eq(1L), any(Consommation.class))).thenReturn(consommation);  // Mock du service

        mockMvc.perform(put("/api/consommations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tauxCO2\":25.5,\"prix\":500.00}"))
                .andExpect(status().isOk())  // Vérifie le statut 200 OK
                .andExpect(jsonPath("$.tauxCO2").value(25.5))  // Vérifie le tauxCO2
                .andExpect(jsonPath("$.prix").value(500.00));  // Vérifie le prix
    }

    @Test
    public void testDeleteConsommation() throws Exception {
        doNothing().when(consommationService).deleteConsommation(1L);  // Mock du service

        mockMvc.perform(delete("/api/consommations/1"))
                .andExpect(status().isNoContent());  // Vérifie le statut 204 No Content
    }
}
*/