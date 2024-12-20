package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.RoleController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Role;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.RoleService;
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

@WebMvcTest(RoleController.class)
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllRoles() throws Exception {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setNomProfil("Admin");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setNomProfil("User");

        Mockito.when(roleService.getAllRoles()).thenReturn(Arrays.asList(role1, role2));

        mockMvc.perform(get("/api/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nomProfil").value("Admin"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nomProfil").value("User"));
    }

    @Test
    void testGetRoleById() throws Exception {
        Role role = new Role();
        role.setId(1L);
        role.setNomProfil("Admin");

        Mockito.when(roleService.getRoleById(1L)).thenReturn(Optional.of(role));

        mockMvc.perform(get("/api/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomProfil").value("Admin"));
    }

    @Test
    void testCreateRole() throws Exception {
        Role role = new Role();
        role.setId(1L);
        role.setNomProfil("Admin");

        Mockito.when(roleService.createRole(Mockito.any(Role.class))).thenReturn(role);

        mockMvc.perform(post("/api/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(role)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomProfil").value("Admin"));
    }

    @Test
    void testUpdateRole() throws Exception {
        Role existingRole = new Role();
        existingRole.setId(1L);
        existingRole.setNomProfil("Admin");

        Role updatedRole = new Role();
        updatedRole.setId(1L);
        updatedRole.setNomProfil("Super Admin");

        Mockito.when(roleService.updateRole(Mockito.eq(1L), Mockito.any(Role.class))).thenReturn(updatedRole);

        mockMvc.perform(put("/api/roles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRole)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomProfil").value("Super Admin"));
    }

    @Test
    void testDeleteRole() throws Exception {
        Mockito.doNothing().when(roleService).deleteRole(1L);

        mockMvc.perform(delete("/api/roles/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(roleService).deleteRole(1L);
    }
}
