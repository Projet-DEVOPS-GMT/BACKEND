package fr.parisnanterre.ProjetDEVOPSGMT.backend.ServiceTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Role;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.RoleRepository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.RoleServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleServiceImplTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRoles() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setNomProfil("Admin");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setNomProfil("User");

        List<Role> roles = Arrays.asList(role1, role2);

        when(roleRepository.findAll()).thenReturn(roles);

        List<Role> result = roleService.getAllRoles();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void testGetRoleById() {
        Role role = new Role();
        role.setId(1L);
        role.setNomProfil("Admin");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Optional<Role> result = roleService.getRoleById(1L);

        assertTrue(result.isPresent());
        assertEquals("Admin", result.get().getNomProfil());
        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateRole() {
        Role role = new Role();
        role.setId(1L);
        role.setNomProfil("Admin");

        when(roleRepository.save(any(Role.class))).thenReturn(role);

        Role result = roleService.createRole(role);

        assertNotNull(result);
        assertEquals("Admin", result.getNomProfil());
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void testDeleteRole() {
        doNothing().when(roleRepository).deleteById(1L);

        roleService.deleteRole(1L);

        verify(roleRepository, times(1)).deleteById(1L);
    }
}
