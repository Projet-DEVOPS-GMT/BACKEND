/* package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.HebergementController;



import com.fasterxml.jackson.databind.ObjectMapper;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.HebergementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HebergementController.class)
public class HebergementControllerTest {

    @Mock
    private HebergementService hebergementService;

    @InjectMocks
    private HebergementController hebergementController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private Hebergement hebergement;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        hebergement = new Hebergement();
        hebergement.setId(1L);
        hebergement.setTypeHebergement("Appartement");
        hebergement.setPrix(new BigDecimal("100.00"));
        hebergement.setTauxCO2(new BigDecimal("25.50"));
        hebergement.setDescription("Appartement cosy avec vue");
        hebergement.setEquipement("WiFi, TV, Cuisine équipée");
        hebergement.setPhoto("photo.jpg");
        hebergement.setConditionsReservation("Annulation gratuite avant 24h");
    }

    @Test
    public void testCreateHebergement() throws Exception {
        when(hebergementService.saveHebergement(any(Hebergement.class))).thenReturn(hebergement);

        mockMvc.perform(post("/api/hebergements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hebergement)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeHebergement").value("Appartement"))
                .andExpect(jsonPath("$.prix").value("100.00"))
                .andExpect(jsonPath("$.tauxCO2").value("25.50"))
                .andExpect(jsonPath("$.description").value("Appartement cosy avec vue"))
                .andExpect(jsonPath("$.equipement").value("WiFi, TV, Cuisine équipée"))
                .andExpect(jsonPath("$.photo").value("photo.jpg"))
                .andExpect(jsonPath("$.conditionsReservation").value("Annulation gratuite avant 24h"));
    }

    @Test
    public void testGetHebergementById() throws Exception {
        when(hebergementService.getHebergementById(anyLong())).thenReturn(java.util.Optional.of(hebergement));

        mockMvc.perform(get("/api/hebergements/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeHebergement").value("Appartement"))
                .andExpect(jsonPath("$.prix").value("100.00"))
                .andExpect(jsonPath("$.tauxCO2").value("25.50"))
                .andExpect(jsonPath("$.description").value("Appartement cosy avec vue"))
                .andExpect(jsonPath("$.equipement").value("WiFi, TV, Cuisine équipée"))
                .andExpect(jsonPath("$.photo").value("photo.jpg"))
                .andExpect(jsonPath("$.conditionsReservation").value("Annulation gratuite avant 24h"));
    }

    @Test
    public void testGetHebergementById_NotFound() throws Exception {
        when(hebergementService.getHebergementById(anyLong())).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/api/hebergements/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateHebergement() throws Exception {
        Hebergement updatedHebergement = new Hebergement();
        updatedHebergement.setTypeHebergement("Maison");
        updatedHebergement.setPrix(new BigDecimal("200.00"));
        updatedHebergement.setTauxCO2(new BigDecimal("30.00"));
        updatedHebergement.setDescription("Maison spacieuse avec jardin");
        updatedHebergement.setEquipement("Piscine, BBQ, Terrasse");
        updatedHebergement.setPhoto("new_photo.jpg");
        updatedHebergement.setConditionsReservation("Annulation 48h avant");

        when(hebergementService.getHebergementById(anyLong())).thenReturn(java.util.Optional.of(hebergement));
        when(hebergementService.saveHebergement(any(Hebergement.class))).thenReturn(updatedHebergement);

        mockMvc.perform(put("/api/hebergements/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedHebergement)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeHebergement").value("Maison"))
                .andExpect(jsonPath("$.prix").value("200.00"))
                .andExpect(jsonPath("$.tauxCO2").value("30.00"))
                .andExpect(jsonPath("$.description").value("Maison spacieuse avec jardin"))
                .andExpect(jsonPath("$.equipement").value("Piscine, BBQ, Terrasse"))
                .andExpect(jsonPath("$.photo").value("new_photo.jpg"))
                .andExpect(jsonPath("$.conditionsReservation").value("Annulation 48h avant"));
    }

    @Test
    public void testUpdateHebergement_NotFound() throws Exception {
        Hebergement updatedHebergement = new Hebergement();
        updatedHebergement.setTypeHebergement("Maison");

        when(hebergementService.getHebergementById(anyLong())).thenReturn(java.util.Optional.empty());

        mockMvc.perform(put("/api/hebergements/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedHebergement)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteHebergement() throws Exception {
        when(hebergementService.getHebergementById(anyLong())).thenReturn(java.util.Optional.of(hebergement));

        mockMvc.perform(delete("/api/hebergements/{id}", 1))
                .andExpect(status().isNoContent());

        verify(hebergementService, times(1)).deleteHebergement(anyLong());
    }

    @Test
    public void testDeleteHebergement_NotFound() throws Exception {
        when(hebergementService.getHebergementById(anyLong())).thenReturn(java.util.Optional.empty());

        mockMvc.perform(delete("/api/hebergements/{id}", 1))
                .andExpect(status().isNotFound());

        verify(hebergementService, times(0)).deleteHebergement(anyLong());
    }
}
*/