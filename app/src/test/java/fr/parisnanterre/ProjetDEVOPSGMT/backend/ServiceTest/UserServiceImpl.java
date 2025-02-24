package fr.parisnanterre.ProjetDEVOPSGMT.backend.ServiceTest;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.UserRepository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> users = userService.getAllUsers();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.getUserById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    void testGetUserByUsername() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.getUserByUsername("testuser");
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    void testCreateUser() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testCreateUserWithExistingUsername() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }

    @Test
    void testFindUserByUsernameAndPassword() {
        String rawPassword = "password";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.findUserByUsernameAndPassword("testuser", rawPassword);

        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    void testFindUserByUsernameAndPasswordInvalid() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.findUserByUsernameAndPassword("testuser", "wrongpassword");
        assertFalse(foundUser.isPresent());
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        User updatedUser = userService.updateUser(1L, user);
        assertNotNull(updatedUser);
        assertEquals("testuser", updatedUser.getUsername());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}
