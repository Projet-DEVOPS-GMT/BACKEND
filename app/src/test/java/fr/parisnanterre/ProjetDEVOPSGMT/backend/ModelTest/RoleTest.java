package fr.parisnanterre.ProjetDEVOPSGMT.backend.ModelTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Role;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleTest {

    private Role role;

    @BeforeEach
    public void setUp() {
        role = new Role();
    }

    @Test
    public void testSettersAndGetters() {
        User user = new User();
        user.setId(1L);

        role.setId(1L);
        role.setNomProfil("ADMIN");
        role.setUser(user);

        assertThat(role.getId()).isEqualTo(1L);
        assertThat(role.getNomProfil()).isEqualTo("ADMIN");
        assertThat(role.getUser()).isEqualTo(user);
    }
}
