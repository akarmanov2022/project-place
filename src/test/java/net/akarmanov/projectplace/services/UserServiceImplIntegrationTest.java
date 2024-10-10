package net.akarmanov.projectplace.services;

import net.akarmanov.projectplace.models.UserRole;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.persistence.jpa.UserRepository;
import net.akarmanov.projectplace.services.exceptions.UserNotFoundException;
import net.akarmanov.projectplace.services.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setMiddleName("Middle");
        user.setPhoneNumber("123456789");
        user.setTelegramId("telegramId");
        user.setRole(UserRole.ADMIN);
        user = userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testGetUser_success() {
        User userDTO = userService.getUser(user.getId());
        assertNotNull(userDTO);
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getMiddleName(), userDTO.getMiddleName());
        assertEquals(user.getPhoneNumber(), userDTO.getPhoneNumber());
        assertEquals(user.getTelegramId(), userDTO.getTelegramId());
        assertEquals(user.getRole().toString(), userDTO.getRole().toString());
    }

    @Test
    void testGetUser_notFound() {
        assertThrows(UserNotFoundException.class, () -> userService.getUser(UUID.randomUUID()));
    }

    @Test
    void testCreateUser() {
        var userServiceUser = User.builder()
                .firstName("Jane")
                .lastName("Doe")
                .middleName("Middle")
                .phoneNumber("987654321")
                .telegramId("newTelegramId")
                .role(UserRole.ADMIN)
                .build();

        User createdUserDTO = userService.createUser(userServiceUser);
        assertNotNull(createdUserDTO);
        assertEquals(userServiceUser.getFirstName(), createdUserDTO.getFirstName());
        assertEquals(userServiceUser.getLastName(), createdUserDTO.getLastName());
        assertEquals(userServiceUser.getMiddleName(), createdUserDTO.getMiddleName());
        assertEquals(userServiceUser.getPhoneNumber(), createdUserDTO.getPhoneNumber());
        assertEquals(userServiceUser.getTelegramId(), createdUserDTO.getTelegramId());

        Optional<User> createdUser = userRepository.findById(createdUserDTO.getId());
        assertTrue(createdUser.isPresent());
        assertEquals(userServiceUser.getFirstName(), createdUser.get().getFirstName());
        assertEquals(userServiceUser.getLastName(), createdUser.get().getLastName());
        assertEquals(userServiceUser.getMiddleName(), createdUser.get().getMiddleName());
        assertEquals(userServiceUser.getPhoneNumber(), createdUser.get().getPhoneNumber());
        assertEquals(userServiceUser.getTelegramId(), createdUser.get().getTelegramId());
        assertEquals(userServiceUser.getRole().toString(), createdUser.get().getRole().toString());
    }

    @Test
    void testUpdateUser() {
        User updateUser = User.builder()
                .firstName("John")
                .lastName("Smith")
                .middleName("Middle")
                .phoneNumber("123456789")
                .telegramId("updatedTelegramId")
                .role(UserRole.ADMIN)
                .build();

        var updatedUserDTO = userService.updateUser(user.getId(), updateUser);
        assertNotNull(updatedUserDTO);
        assertEquals(updateUser.getFirstName(), updatedUserDTO.getFirstName());
        assertEquals(updateUser.getLastName(), updatedUserDTO.getLastName());
        assertEquals(updateUser.getMiddleName(), updatedUserDTO.getMiddleName());
        assertEquals(updateUser.getPhoneNumber(), updatedUserDTO.getPhoneNumber());
        assertEquals(updateUser.getTelegramId(), updatedUserDTO.getTelegramId());

        Optional<User> updatedUser = userRepository.findById(user.getId());
        assertTrue(updatedUser.isPresent());
        assertEquals(updateUser.getFirstName(), updatedUser.get().getFirstName());
        assertEquals(updateUser.getLastName(), updatedUser.get().getLastName());
        assertEquals(updateUser.getMiddleName(), updatedUser.get().getMiddleName());
        assertEquals(updateUser.getPhoneNumber(), updatedUser.get().getPhoneNumber());
        assertEquals(updateUser.getTelegramId(), updatedUser.get().getTelegramId());
        assertEquals(updateUser.getRole().toString(), updatedUser.get().getRole().toString());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(user.getId().toString());
        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertFalse(deletedUser.isPresent());
    }
}