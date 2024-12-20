package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.RestaurationController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Restauration;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.RestaurationService;
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

@WebMvcTest(RestaurationController.class)
class RestaurationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurationService restaurationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllRestaurations() throws Exception {
        Restauration restauration1 = new Restauration();
        restauration1.setId(1L);
        restauration1.setTypeRestaurant("Fast Food");
        restauration1.setPrixMoyenne(BigDecimal.valueOf(15.50));

        Restauration restauration2 = new Restauration();
        restauration2.setId(2L);
        restauration2.setTypeRestaurant("Gastronomique");
        restauration2.setPrixMoyenne(BigDecimal.valueOf(50.00));

        Mockito.when(restaurationService.getAllRestaurations()).thenReturn(Arrays.asList(restauration1, restauration2));

        mockMvc.perform(get("/api/restaurations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].typeRestaurant").value("Fast Food"))
                .andExpect(jsonPath("$[0].prixMoyenne").value(15.50))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].typeRestaurant").value("Gastronomique"))
                .andExpect(jsonPath("$[1].prixMoyenne").value(50.00));
    }

    @Test
    void testGetRestaurationById() throws Exception {
        Restauration restauration = new Restauration();
        restauration.setId(1L);
        restauration.setTypeRestaurant("Fast Food");
        restauration.setPrixMoyenne(BigDecimal.valueOf(15.50));

        Mockito.when(restaurationService.getRestaurationById(1L)).thenReturn(Optional.of(restauration));

        mockMvc.perform(get("/api/restaurations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeRestaurant").value("Fast Food"))
                .andExpect(jsonPath("$.prixMoyenne").value(15.50));
    }

    @Test
    void testCreateRestauration() throws Exception {
        Restauration restauration = new Restauration();
        restauration.setId(1L);
        restauration.setTypeRestaurant("Fast Food");
        restauration.setPrixMoyenne(BigDecimal.valueOf(15.50));

        Mockito.when(restaurationService.createRestauration(Mockito.any(Restauration.class))).thenReturn(restauration);

        mockMvc.perform(post("/api/restaurations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restauration)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeRestaurant").value("Fast Food"))
                .andExpect(jsonPath("$.prixMoyenne").value(15.50));
    }

    @Test
    void testUpdateRestauration() throws Exception {
        Restauration existingRestauration = new Restauration();
        existingRestauration.setId(1L);
        existingRestauration.setTypeRestaurant("Fast Food");
        existingRestauration.setPrixMoyenne(BigDecimal.valueOf(15.50));

        Restauration updatedRestauration = new Restauration();
        updatedRestauration.setId(1L);
        updatedRestauration.setTypeRestaurant("Gastronomique");
        updatedRestauration.setPrixMoyenne(BigDecimal.valueOf(50.00));

        Mockito.when(restaurationService.updateRestauration(Mockito.eq(1L), Mockito.any(Restauration.class))).thenReturn(updatedRestauration);

        mockMvc.perform(put("/api/restaurations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRestauration)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeRestaurant").value("Gastronomique"))
                .andExpect(jsonPath("$.prixMoyenne").value(50.00));
    }

    @Test
    void testDeleteRestauration() throws Exception {
        Mockito.doNothing().when(restaurationService).deleteRestauration(1L);

        mockMvc.perform(delete("/api/restaurations/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(restaurationService).deleteRestauration(1L);
    }
}
