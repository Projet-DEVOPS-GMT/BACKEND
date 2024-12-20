package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.VoyageController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.VoyageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VoyageController.class)
class VoyageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private VoyageService voyageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllVoyages() throws Exception {
        Voyage voyage1 = new Voyage();
        voyage1.setId(1L);
        voyage1.setDateDepart(LocalDate.of(2024, 1, 1));
        voyage1.setDateRetour(LocalDate.of(2024, 1, 10));

        Voyage voyage2 = new Voyage();
        voyage2.setId(2L);
        voyage2.setDateDepart(LocalDate.of(2024, 2, 1));
        voyage2.setDateRetour(LocalDate.of(2024, 2, 10));

        Mockito.when(voyageService.getAllVoyages()).thenReturn(Arrays.asList(voyage1, voyage2));

        mockMvc.perform(get("/api/voyages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].dateDepart").value("2024-01-01"))
                .andExpect(jsonPath("$[0].dateRetour").value("2024-01-10"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].dateDepart").value("2024-02-01"))
                .andExpect(jsonPath("$[1].dateRetour").value("2024-02-10"));
    }

    @Test
    void testGetVoyageById() throws Exception {
        Voyage voyage = new Voyage();
        voyage.setId(1L);
        voyage.setDateDepart(LocalDate.of(2024, 1, 1));
        voyage.setDateRetour(LocalDate.of(2024, 1, 10));

        Mockito.when(voyageService.getVoyageById(1L)).thenReturn(Optional.of(voyage));

        mockMvc.perform(get("/api/voyages/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.dateDepart").value("2024-01-01"))
                .andExpect(jsonPath("$.dateRetour").value("2024-01-10"));
    }

    @Test
    void testCreateVoyage() throws Exception {
        Voyage voyage = new Voyage();
        voyage.setId(1L);
        voyage.setDateDepart(LocalDate.of(2024, 1, 1));
        voyage.setDateRetour(LocalDate.of(2024, 1, 10));

        Mockito.when(voyageService.saveVoyage(Mockito.any(Voyage.class))).thenReturn(voyage);

        mockMvc.perform(post("/api/voyages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(voyage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.dateDepart").value("2024-01-01"))
                .andExpect(jsonPath("$.dateRetour").value("2024-01-10"));
    }

    @Test
    void testUpdateVoyage() throws Exception {
        Voyage existingVoyage = new Voyage();
        existingVoyage.setId(1L);
        existingVoyage.setDateDepart(LocalDate.of(2024, 1, 1));
        existingVoyage.setDateRetour(LocalDate.of(2024, 1, 10));

        Voyage updatedVoyage = new Voyage();
        updatedVoyage.setId(1L);
        updatedVoyage.setDateDepart(LocalDate.of(2024, 3, 1));
        updatedVoyage.setDateRetour(LocalDate.of(2024, 3, 10));

        Mockito.when(voyageService.getVoyageById(1L)).thenReturn(Optional.of(existingVoyage));
        Mockito.when(voyageService.saveVoyage(Mockito.any(Voyage.class))).thenReturn(updatedVoyage);

        mockMvc.perform(put("/api/voyages/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedVoyage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.dateDepart").value("2024-03-01"))
                .andExpect(jsonPath("$.dateRetour").value("2024-03-10"));
    }

    @Test
    void testDeleteVoyage() throws Exception {
        Mockito.when(voyageService.getVoyageById(1L)).thenReturn(Optional.of(new Voyage()));

        mockMvc.perform(delete("/api/voyages/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(voyageService).deleteVoyage(1L);
    }
}
