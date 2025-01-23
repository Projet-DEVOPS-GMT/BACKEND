package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.RestaurationController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Restauration;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.RestaurationService;
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

@WebMvcTest(RestaurationController.class)
public class RestaurationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestaurationService restaurationService;

    @InjectMocks
    private RestaurationController restaurationController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRestaurations() throws Exception {
        Restauration restauration1 = new Restauration();
        restauration1.setId(1L);
 

        Restauration restauration2 = new Restauration();
        restauration2.setId(2L);
      

        when(restaurationService.getAllRestaurations()).thenReturn(Arrays.asList(restauration1, restauration2));

        mockMvc.perform(get("/api/restaurations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nom").value("Restaurant A"))
                .andExpect(jsonPath("$[1].nom").value("Restaurant B"));

        verify(restaurationService, times(1)).getAllRestaurations();
    }

    @Test
    public void testGetRestaurationById() throws Exception {
        Restauration restauration = new Restauration();
        restauration.setId(1L);
       
        when(restaurationService.getRestaurationById(1L)).thenReturn(Optional.of(restauration));

        mockMvc.perform(get("/api/restaurations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Restaurant A"));

        verify(restaurationService, times(1)).getRestaurationById(1L);
    }

    @Test
    public void testCreateRestauration() throws Exception {
        Restauration restauration = new Restauration();
    
        Restauration savedRestauration = new Restauration();
        savedRestauration.setId(1L);
      

        when(restaurationService.createRestauration(any(Restauration.class))).thenReturn(savedRestauration);

        mockMvc.perform(post("/api/restaurations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restauration)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Restaurant C"));

        verify(restaurationService, times(1)).createRestauration(any(Restauration.class));
    }

    @Test
    public void testUpdateRestauration() throws Exception {
        Restauration existingRestauration = new Restauration();
        existingRestauration.setId(1L);
      

        Restauration updatedRestauration = new Restauration();
        updatedRestauration.setId(1L);
   
        when(restaurationService.updateRestauration(eq(1L), any(Restauration.class))).thenReturn(updatedRestauration);

        mockMvc.perform(put("/api/restaurations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRestauration)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Restaurant B"));

        verify(restaurationService, times(1)).updateRestauration(eq(1L), any(Restauration.class));
    }

    @Test
    public void testDeleteRestauration() throws Exception {
        doNothing().when(restaurationService).deleteRestauration(1L);

        mockMvc.perform(delete("/api/restaurations/1"))
                .andExpect(status().isNoContent());

        verify(restaurationService, times(1)).deleteRestauration(1L);
    }
}
