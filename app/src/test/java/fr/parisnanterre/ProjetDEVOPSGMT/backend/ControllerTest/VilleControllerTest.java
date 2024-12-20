package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.VilleController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.VilleService;
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

@WebMvcTest(VilleController.class)
class VilleControllerTest {

    @Autowired
    private MockMvc mockMvc;

   
    private VilleService villeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllVilles() throws Exception {
        Ville ville1 = new Ville();
        ville1.setId(1L);
        ville1.setNom("Paris");

        Ville ville2 = new Ville();
        ville2.setId(2L);
        ville2.setNom("Berlin");

        Mockito.when(villeService.getAllVilles()).thenReturn(Arrays.asList(ville1, ville2));

        mockMvc.perform(get("/api/villes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nom").value("Paris"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nom").value("Berlin"));
    }

    @Test
    void testGetVilleById() throws Exception {
        Ville ville = new Ville();
        ville.setId(1L);
        ville.setNom("Paris");

        Mockito.when(villeService.getVilleById(1L)).thenReturn(Optional.of(ville));

        mockMvc.perform(get("/api/villes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Paris"));
    }

    @Test
    void testCreateVille() throws Exception {
        Ville ville = new Ville();
        ville.setId(1L);
        ville.setNom("Paris");

        Mockito.when(villeService.createVille(Mockito.any(Ville.class))).thenReturn(ville);

        mockMvc.perform(post("/api/villes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ville)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Paris"));
    }

    @Test
    void testUpdateVille() throws Exception {
        Ville existingVille = new Ville();
        existingVille.setId(1L);
        existingVille.setNom("Paris");

        Ville updatedVille = new Ville();
        updatedVille.setId(1L);
        updatedVille.setNom("Lyon");

        Mockito.when(villeService.updateVille(Mockito.eq(1L), Mockito.any(Ville.class))).thenReturn(updatedVille);

        mockMvc.perform(put("/api/villes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedVille)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Lyon"));
    }

    @Test
    void testDeleteVille() throws Exception {
        Mockito.doNothing().when(villeService).deleteVille(1L);

        mockMvc.perform(delete("/api/villes/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(villeService).deleteVille(1L);
    }
}
