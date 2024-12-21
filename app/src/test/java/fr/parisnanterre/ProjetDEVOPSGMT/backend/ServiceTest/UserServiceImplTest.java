package fr.parisnanterre.ProjetDEVOPSGMT.backend.ServiceTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.UserRepository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.UserServiceImpl;

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

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setNom("Doe");
        user1.setPrenom("John");
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setNom("Smith");
        user2.setPrenom("Jane");
        user2.setEmail("jane.smith@example.com");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setNom("Doe");
        user.setPrenom("John");
        user.setEmail("john.doe@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals("Doe", result.get().getNom());
        assertEquals("John", result.get().getPrenom());
        assertEquals("john.doe@example.com", result.get().getEmail());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUserByEmail() {
        User user = new User();
        user.setId(1L);
        user.setNom("Doe");
        user.setPrenom("John");
        user.setEmail("john.doe@example.com");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByEmail("john.doe@example.com");

        assertTrue(result.isPresent());
        assertEquals("Doe", result.get().getNom());
        assertEquals("John", result.get().getPrenom());
        assertEquals("john.doe@example.com", result.get().getEmail());
        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setId(1L);
        user.setNom("Doe");
        user.setPrenom("John");
        user.setEmail("john.doe@example.com");
        user.setMotDePasse("password123");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals("Doe", result.getNom());
        assertEquals("John", result.getPrenom());
        assertEquals("john.doe@example.com", result.getEmail());
        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testCreateUser_EmailAlreadyExists() {
        User user = new User();
        user.setId(1L);
        user.setNom("Doe");
        user.setPrenom("John");
        user.setEmail("john.doe@example.com");
        user.setMotDePasse("password123");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.createUser(user));

        assertEquals("Un utilisateur avec cet email existe déjà !", exception.getMessage());
        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindByEmailAndPassword() {
        User user = new User();
        user.setId(1L);
        user.setNom("Doe");
        user.setPrenom("John");
        user.setEmail("john.doe@example.com");
        user.setMotDePasse("password123");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        User result = userService.findByEmailAndPassword("john.doe@example.com", "password123");

        assertNotNull(result);
        assertEquals("Doe", result.getNom());
        assertEquals("John", result.getPrenom());
        assertEquals("john.doe@example.com", result.getEmail());
        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
    }

    @Test
    void testFindByEmailAndPassword_InvalidPassword() {
        User user = new User();
        user.setId(1L);
        user.setNom("Doe");
        user.setPrenom("John");
        user.setEmail("john.doe@example.com");
        user.setMotDePasse("password123");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        User result = userService.findByEmailAndPassword("john.doe@example.com", "wrongpassword");

        assertNull(result);
        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
    }
}
