package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.VoyageController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.VoyageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VoyageController.class)
public class VoyageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VoyageService voyageService;

    @InjectMocks
    private VoyageController voyageController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllVoyages() throws Exception {
        Voyage voyage1 = new Voyage();
        voyage1.setId(1L);
        voyage1.setVilleDestination("Paris");

        Voyage voyage2 = new Voyage();
        voyage2.setId(2L);
        voyage2.setVilleDestination("Lyon");

        when(voyageService.getAllVoyages()).thenReturn(Arrays.asList(voyage1, voyage2));

        mockMvc.perform(get("/api/voyages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].destination").value("Paris"))
                .andExpect(jsonPath("$[1].destination").value("Lyon"));

        verify(voyageService, times(1)).getAllVoyages();
    }

    @Test
    public void testGetVoyageById() throws Exception {
        Voyage voyage = new Voyage();
        voyage.setId(1L);
        voyage.setVilleDestination("Paris");

        when(voyageService.getVoyageById(1L)).thenReturn(Optional.of(voyage));

        mockMvc.perform(get("/api/voyages/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.destination").value("Paris"));

        verify(voyageService, times(1)).getVoyageById(1L);
    }

    @Test
    public void testGetVoyageByIdNotFound() throws Exception {
        when(voyageService.getVoyageById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/voyages/1"))
                .andExpect(status().isNotFound());

        verify(voyageService, times(1)).getVoyageById(1L);
    }

    @Test
    public void testCreateVoyage() throws Exception {
        Voyage voyage = new Voyage();
        voyage.setVilleDestination("Paris");

        Voyage savedVoyage = new Voyage();
        savedVoyage.setId(1L);
        savedVoyage.setVilleDestination("Paris");

        when(voyageService.saveVoyage(any(Voyage.class))).thenReturn(savedVoyage);

        mockMvc.perform(post("/api/voyages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(voyage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.destination").value("Paris"));

        verify(voyageService, times(1)).saveVoyage(any(Voyage.class));
    }

    @Test
    public void testUpdateVoyage() throws Exception {
        Voyage existingVoyage = new Voyage();
        existingVoyage.setId(1L);
        existingVoyage.setVilleDestination("Paris");

        Voyage updatedVoyage = new Voyage();
        updatedVoyage.setId(1L);
        updatedVoyage.setVilleDestination("Lyon");

        when(voyageService.getVoyageById(1L)).thenReturn(Optional.of(existingVoyage));
        when(voyageService.saveVoyage(any(Voyage.class))).thenReturn(updatedVoyage);

        mockMvc.perform(put("/api/voyages/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedVoyage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.destination").value("Lyon"));

        verify(voyageService, times(1)).getVoyageById(1L);
        verify(voyageService, times(1)).saveVoyage(any(Voyage.class));
    }

    @Test
    public void testUpdateVoyageNotFound() throws Exception {
        when(voyageService.getVoyageById(1L)).thenReturn(Optional.empty());

        Voyage updatedVoyage = new Voyage();
        updatedVoyage.setVilleDestination("Lyon");

        mockMvc.perform(put("/api/voyages/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedVoyage)))
                .andExpect(status().isNotFound());

        verify(voyageService, times(1)).getVoyageById(1L);
        verify(voyageService, times(0)).saveVoyage(any(Voyage.class));
    }

    @Test
    public void testDeleteVoyage() throws Exception {
        Voyage existingVoyage = new Voyage();
        existingVoyage.setId(1L);

        when(voyageService.getVoyageById(1L)).thenReturn(Optional.of(existingVoyage));
        doNothing().when(voyageService).deleteVoyage(1L);

        mockMvc.perform(delete("/api/voyages/1"))
                .andExpect(status().isNoContent());

        verify(voyageService, times(1)).getVoyageById(1L);
        verify(voyageService, times(1)).deleteVoyage(1L);
    }

    @Test
    public void testDeleteVoyageNotFound() throws Exception {
        when(voyageService.getVoyageById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/voyages/1"))
                .andExpect(status().isNotFound());

        verify(voyageService, times(1)).getVoyageById(1L);
        verify(voyageService, times(0)).deleteVoyage(1L);
    }
}
