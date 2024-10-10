package net.akarmanov.projectplace.services;

import net.akarmanov.projectplace.api.user.UserRoleDto;
import net.akarmanov.projectplace.api.user.dto.UserCreateDTO;
import net.akarmanov.projectplace.api.user.dto.UserDTO;
import net.akarmanov.projectplace.models.UserRole;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.persistence.jpa.UserRepository;
import net.akarmanov.projectplace.services.exceptions.UserNotFoundException;
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
        UserDTO userDTO = userService.getUser(user.getId());
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
        var newUserDTO = UserCreateDTO.builder()
                .firstName("Jane")
                .lastName("Doe")
                .middleName("Middle")
                .phoneNumber("987654321")
                .telegramId("newTelegramId")
                .role(UserRoleDto.ADMIN)
                .build();

        UserDTO createdUserDTO = userService.createUser(newUserDTO);
        assertNotNull(createdUserDTO);
        assertEquals(newUserDTO.getFirstName(), createdUserDTO.getFirstName());
        assertEquals(newUserDTO.getLastName(), createdUserDTO.getLastName());
        assertEquals(newUserDTO.getMiddleName(), createdUserDTO.getMiddleName());
        assertEquals(newUserDTO.getPhoneNumber(), createdUserDTO.getPhoneNumber());
        assertEquals(newUserDTO.getTelegramId(), createdUserDTO.getTelegramId());
        assertEquals(newUserDTO.getRole(), createdUserDTO.getRole());

        Optional<User> createdUser = userRepository.findById(createdUserDTO.getId());
        assertTrue(createdUser.isPresent());
        assertEquals(newUserDTO.getFirstName(), createdUser.get().getFirstName());
        assertEquals(newUserDTO.getLastName(), createdUser.get().getLastName());
        assertEquals(newUserDTO.getMiddleName(), createdUser.get().getMiddleName());
        assertEquals(newUserDTO.getPhoneNumber(), createdUser.get().getPhoneNumber());
        assertEquals(newUserDTO.getTelegramId(), createdUser.get().getTelegramId());
        assertEquals(newUserDTO.getRole().toString(), createdUser.get().getRole().toString());
    }

    @Test
    void testUpdateUser() {
        UserDTO updateUserDTO = UserDTO.builder()
                .firstName("John")
                .lastName("Smith")
                .middleName("Middle")
                .phoneNumber("123456789")
                .telegramId("updatedTelegramId")
                .role(UserRoleDto.ADMIN)
                .build();

        UserDTO updatedUserDTO = userService.updateUser(user.getId(), updateUserDTO);
        assertNotNull(updatedUserDTO);
        assertEquals(updateUserDTO.getFirstName(), updatedUserDTO.getFirstName());
        assertEquals(updateUserDTO.getLastName(), updatedUserDTO.getLastName());
        assertEquals(updateUserDTO.getMiddleName(), updatedUserDTO.getMiddleName());
        assertEquals(updateUserDTO.getPhoneNumber(), updatedUserDTO.getPhoneNumber());
        assertEquals(updateUserDTO.getTelegramId(), updatedUserDTO.getTelegramId());
        assertEquals(updateUserDTO.getRole(), updatedUserDTO.getRole());

        Optional<User> updatedUser = userRepository.findById(user.getId());
        assertTrue(updatedUser.isPresent());
        assertEquals(updateUserDTO.getFirstName(), updatedUser.get().getFirstName());
        assertEquals(updateUserDTO.getLastName(), updatedUser.get().getLastName());
        assertEquals(updateUserDTO.getMiddleName(), updatedUser.get().getMiddleName());
        assertEquals(updateUserDTO.getPhoneNumber(), updatedUser.get().getPhoneNumber());
        assertEquals(updateUserDTO.getTelegramId(), updatedUser.get().getTelegramId());
        assertEquals(updateUserDTO.getRole().toString(), updatedUser.get().getRole().toString());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(user.getId().toString());
        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertFalse(deletedUser.isPresent());
    }
}