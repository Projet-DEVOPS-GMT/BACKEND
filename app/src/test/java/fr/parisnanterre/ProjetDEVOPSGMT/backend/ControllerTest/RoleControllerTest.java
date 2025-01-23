package fr.parisnanterre.ProjetDEVOPSGMT.backend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler.RoleController;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Role;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.RoleService;
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

@WebMvcTest(RoleController.class)
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRoles() throws Exception {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setNomProfil("Admin");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setNomProfil("User");

        when(roleService.getAllRoles()).thenReturn(Arrays.asList(role1, role2));

        mockMvc.perform(get("/api/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Admin"))
                .andExpect(jsonPath("$[1].name").value("User"));

        verify(roleService, times(1)).getAllRoles();
    }

    @Test
    public void testGetRoleById() throws Exception {
        Role role = new Role();
        role.setId(1L);
        role.setNomProfil("Admin");

        when(roleService.getRoleById(1L)).thenReturn(Optional.of(role));

        mockMvc.perform(get("/api/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Admin"));

        verify(roleService, times(1)).getRoleById(1L);
    }

    @Test
    public void testCreateRole() throws Exception {
        Role role = new Role();
        role.setNomProfil("Admin");

        Role savedRole = new Role();
        savedRole.setId(1L);
        savedRole.setNomProfil("Admin");

        when(roleService.createRole(any(Role.class))).thenReturn(savedRole);

        mockMvc.perform(post("/api/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(role)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Admin"));

        verify(roleService, times(1)).createRole(any(Role.class));
    }

    @Test
    public void testUpdateRole() throws Exception {
        Role existingRole = new Role();
        existingRole.setId(1L);
        existingRole.setNomProfil("User");

        Role updatedRole = new Role();
        updatedRole.setId(1L);
        updatedRole.setNomProfil("Admin");

        when(roleService.updateRole(eq(1L), any(Role.class))).thenReturn(updatedRole);

        mockMvc.perform(put("/api/roles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRole)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Admin"));

        verify(roleService, times(1)).updateRole(eq(1L), any(Role.class));
    }

    @Test
    public void testDeleteRole() throws Exception {
        doNothing().when(roleService).deleteRole(1L);

        mockMvc.perform(delete("/api/roles/1"))
                .andExpect(status().isNoContent());

        verify(roleService, times(1)).deleteRole(1L);
    }
}
