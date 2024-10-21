package net.akarmanov.projectplace.services.teamcard;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.domain.TeamCard;
import net.akarmanov.projectplace.mapping.TeamCardMapper;
import net.akarmanov.projectplace.models.TeamCardStatus;
import net.akarmanov.projectplace.repos.TeamCardsRepository;
import net.akarmanov.projectplace.rest.api.teamcard.dto.TeamCardCreateOrUpdateDto;
import net.akarmanov.projectplace.rest.api.teamcard.dto.TeamCardDto;
import net.akarmanov.projectplace.services.exceptions.TeamCardNotFoundException;
import net.akarmanov.projectplace.services.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static net.akarmanov.projectplace.domain.spec.TeamCardSpecification.nameEquals;
import static net.akarmanov.projectplace.domain.spec.TeamCardSpecification.statusEquals;

@Service
@RequiredArgsConstructor
public class TeamCardsServiceImpl implements TeamCardsService {

    private final TeamCardsRepository teamCardsRepository;

    private final TeamCardMapper teamCardMapper;

    private final UserService userService;

    @Override
    @Transactional
    public TeamCardDto createTeamCard(TeamCardCreateOrUpdateDto teamCardDto) {
        var teamCard = teamCardMapper.mapToEntity(teamCardDto);
        teamCard.setUser(userService.getCurrentUser());
        teamCard.setStatus(TeamCardStatus.OK);
        teamCard = teamCardsRepository.save(teamCard);
        return teamCardMapper.mapToDto(teamCard);
    }

    @Override
    @Transactional
    public TeamCardDto updateTeamCard(UUID teamCardId, TeamCardCreateOrUpdateDto teamCardDto) {
        var teamCard = get(teamCardId);
        teamCardMapper.updateFromDto(teamCardDto, teamCard);
        teamCard = teamCardsRepository.save(teamCard);
        return teamCardMapper.mapToDto(teamCard);
    }

    private TeamCard get(UUID teamCardId) {
        return teamCardsRepository.findById(teamCardId)
                .orElseThrow(() -> new TeamCardNotFoundException(teamCardId));
    }

    @Override
    public Page<TeamCardDto> getTeamCards(String name, String status, Pageable pageable) {
        var page = teamCardsRepository.findAll(
                nameEquals(name).or(statusEquals(status)), pageable);
        return page.map(teamCardMapper::mapToDto);
    }

    @Override
    public TeamCardDto getTeamCard(UUID id) {
        var teamCard = get(id);
        return teamCardMapper.mapToDto(teamCard);
    }
}
