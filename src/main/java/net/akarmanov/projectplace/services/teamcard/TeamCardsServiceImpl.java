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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static net.akarmanov.projectplace.domain.spec.TeamCardSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

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
        var user = userService.getCurrentUser();
        var page = teamCardsRepository.findAll(
                where(nameLike(name).or(statusEquals(status)))
                        .and(userEquals(user.getId())), pageable);
        return page.map(teamCardMapper::mapToDto);
    }

    @Override
    public TeamCardDto getTeamCard(UUID id) {
        var teamCard = get(id);
        return teamCardMapper.mapToDto(teamCard);
    }

    @Override
    @Transactional
    public void deleteTeamCard(UUID id) {
        var user = userService.getCurrentUser();
        var teamCard = teamCardsRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new TeamCardNotFoundException(id));
        teamCardsRepository.delete(teamCard);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public TeamCardDto createTeamCard(TeamCardCreateOrUpdateDto teamCardDto, UUID userId) {
        var teamCard = teamCardMapper.mapToEntity(teamCardDto);
        teamCard.setUser(userService.getUser(userId));
        teamCard.setStatus(TeamCardStatus.OK);
        teamCard = teamCardsRepository.save(teamCard);
        return teamCardMapper.mapToDto(teamCard);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public TeamCardDto updateTeamCard(UUID teamCardId, TeamCardCreateOrUpdateDto teamCardDto, UUID userId) {
        var teamCard = get(teamCardId, userId);
        teamCardMapper.updateFromDto(teamCardDto, teamCard);
        teamCard.setUser(userService.getUser(userId));
        teamCard = teamCardsRepository.save(teamCard);
        return teamCardMapper.mapToDto(teamCard);
    }

    @Override
    public Page<TeamCardDto> findAll(String name, String status, Pageable pageable) {
        var page = teamCardsRepository.findAll(
                where(nameLike(name).or(statusEquals(status))), pageable);
        return page.map(teamCardMapper::mapToDto);
    }

    @Override
    public TeamCardDto getTeamCard(UUID id, UUID userId) {
        var teamCard = get(id, userId);
        return teamCardMapper.mapToDto(teamCard);
    }

    @Override
    @Transactional
    public void deleteTeamCard(UUID id, UUID userId) {
        teamCardsRepository.deleteByIdAndUserId(id, userId);
    }

    private TeamCard get(UUID teamCardId, UUID userId) {
        return teamCardsRepository.findByIdAndUserId(teamCardId, userId)
                .orElseThrow(() -> new TeamCardNotFoundException(teamCardId));
    }
}
