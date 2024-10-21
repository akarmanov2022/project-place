package net.akarmanov.projectplace.rest.api.teamcard;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.rest.api.teamcard.dto.TeamCardCreateOrUpdateDto;
import net.akarmanov.projectplace.rest.api.teamcard.dto.TeamCardDto;
import net.akarmanov.projectplace.services.teamcard.TeamCardsService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TeamCardsRestControllerImpl implements TeamCardsRestController {

    private final TeamCardsService teamCardsService;

    @Override
    public ResponseEntity<TeamCardDto> createTeamCard(TeamCardCreateOrUpdateDto teamCardDto) {
        var teamCard = teamCardsService.createTeamCard(teamCardDto);
        return ResponseEntity.ok(teamCard);
    }

    @Override
    public ResponseEntity<TeamCardDto> updateTeamCard(UUID teamCardId, TeamCardCreateOrUpdateDto teamCardDto) {
        var teamCard = teamCardsService.updateTeamCard(teamCardId, teamCardDto);
        return ResponseEntity.ok(teamCard);
    }

    @Override
    public ResponseEntity<PagedModel<TeamCardDto>> getTeamCards(String name, String status, Pageable pageable) {
        var page = teamCardsService.getTeamCards(name, status, pageable);
        return ResponseEntity.ok(new PagedModel<>(page));
    }

    @Override
    public ResponseEntity<TeamCardDto> getTeamCard(UUID id) {
        var teamCard = teamCardsService.getTeamCard(id);
        return ResponseEntity.ok(teamCard);
    }

    @Override
    public ResponseEntity<Void> deleteTeamCard(UUID id) {
        teamCardsService.deleteTeamCard(id);
        return ResponseEntity.noContent().build();
    }
}
