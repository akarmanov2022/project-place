package net.akarmanov.projectplace.rest.api.teamcard;

import net.akarmanov.projectplace.BaseApplicationTest;
import net.akarmanov.projectplace.domain.TeamCard;
import net.akarmanov.projectplace.domain.User;
import net.akarmanov.projectplace.models.TeamCardStatus;
import net.akarmanov.projectplace.models.UserRole;
import net.akarmanov.projectplace.repos.TeamCardsRepository;
import net.akarmanov.projectplace.repos.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = TeamCardsRestControllerImplTest.USERNAME, roles = {"TRACKER"})
class TeamCardsRestControllerImplTest extends BaseApplicationTest {

    public static final String USERNAME = "TRACKER";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamCardsRepository teamCardsRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = userRepository.save(User.builder()
                .enabled(true)
                .firstName("Иван")
                .lastName("Иванов")
                .telegramId(USERNAME)
                .password("123456")
                .role(UserRole.TRACKER)
                .build()
        );
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void createTeamCard_success() throws Exception {
        mockMvc.perform(post("/api/v1/team-cards/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Test",
                                  "description": "Test description"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name", is("Test")))
                .andExpect(jsonPath("$.user.id", is(user.getId().toString())));
    }

    @Test
    void createTeamCard_validationError() throws Exception {
        mockMvc.perform(post("/api/v1/team-cards/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateTeamCard_success() throws Exception {
        var teamCard = teamCardsRepository.save(TeamCard.builder()
                .user(user)
                .status(TeamCardStatus.OK)
                .name("Team card1")
                .build());

        mockMvc.perform(post("/api/v1/team-cards/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("teamCardId", teamCard.getId().toString())
                        .content("""
                                {
                                  "name": "Updated name",
                                  "description": "Updated description"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(teamCard.getId().toString())))
                .andExpect(jsonPath("$.name", is("Updated name")))
                .andExpect(jsonPath("$.user.id", is(user.getId().toString())));
    }
}