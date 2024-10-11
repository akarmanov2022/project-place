package net.akarmanov.projectplace.api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.akarmanov.projectplace.api.dto.SingInRequest;
import net.akarmanov.projectplace.api.dto.SingUpRequest;
import net.akarmanov.projectplace.api.dto.UserRoleDto;
import net.akarmanov.projectplace.models.UserRole;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.persistence.jpa.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthRestControllerTest {

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    private static final UsernamePasswordAuthenticationToken principal =
            new UsernamePasswordAuthenticationToken(USERNAME, PASSWORD);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        userRepository.save(User.builder()
                .username(USERNAME)
                .password(passwordEncoder.encode(PASSWORD))
                .phoneNumber("+71234567890")
                .telegramId("telegramId")
                .firstName("John")
                .enabled(true)
                .role(UserRole.ADMIN)
                .build());
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
    }

    @Test
    void testSignIn_success() throws Exception {
        var signIn = SingInRequest.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
        mockMvc.perform(post("/auth/sing-in")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signIn)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    void testSignIn_failure() throws Exception {
        var signIn = SingInRequest.builder()
                .username(USERNAME)
                .password("wrongPassword")
                .build();
        mockMvc.perform(post("/auth/sing-in")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signIn)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSignUp_success() throws Exception {
        var signUp = SingUpRequest.builder()
                .username("newUsername")
                .password("newPassword")
                .phoneNumber("+71234567891")
                .telegramId("newTelegramId")
                .firstName("John")
                .role(UserRoleDto.ADMIN)
                .build();
        mockMvc.perform(post("/auth/sing-up")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    void testSignUp_failure() throws Exception {
        var signUp = SingUpRequest.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .phoneNumber("+71234567892")
                .telegramId("telegramId")
                .firstName("John")
                .role(UserRoleDto.ADMIN)
                .build();
        mockMvc.perform(post("/auth/sing-up")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUp)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}