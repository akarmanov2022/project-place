package net.akarmanov.projectplace.rest.api.account;

import net.akarmanov.projectplace.BaseApplicationTest;
import net.akarmanov.projectplace.domain.User;
import net.akarmanov.projectplace.models.UserRole;
import net.akarmanov.projectplace.repos.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = AccountRestControllerTest.TELEGRAM_ID, roles = "TRACKER")
class AccountRestControllerTest extends BaseApplicationTest {

    public static final String PASSWORD = "password";

    public static final String TELEGRAM_ID = "telegramId";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    void setUp() {
        user = userRepository.save(User.builder()
                .password(passwordEncoder.encode(PASSWORD))
                .telegramId(TELEGRAM_ID)
                .enabled(true)
                .firstName("John")
                .lastName("Doe")
                .middleName("Middle")
                .phoneNumber("+71234567890")
                .role(UserRole.TRACKER)
                .build());
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void getCurrentUserInfo_success() throws Exception {
        mockMvc.perform(get("/api/v1/users/current/info"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId().toString())));
    }

    @Test
    void updateUserInfo_success() throws Exception {
        mockMvc.perform(post("/api/v1/users/current/update")
                        .contentType("application/json")
                        .content("""
                                {
                                  "firstName": "Jane",
                                  "lastName": "Doe",
                                  "middleName": "Middle",
                                  "phoneNumber": "+71234567890",
                                  "telegramId": "telegramId"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Jane")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.middleName", is("Middle")))
                .andExpect(jsonPath("$.phoneNumber", is("+71234567890")))
                .andExpect(jsonPath("$.telegramId", is("telegramId")));
    }

    @Test
    void changePassword_success() throws Exception {
        var newPassword = "newPassword";
        mockMvc.perform(post("/api/v1/users/current/changePassword")
                        .contentType("application/json")
                        .param("oldPassword", PASSWORD)
                        .param("newPassword", newPassword))
                .andDo(print())
                .andExpect(status().isNoContent());

        user = userRepository.findById(user.getId()).orElseThrow();

        Assertions.assertTrue(passwordEncoder.matches(newPassword, user.getPassword()));
    }
}