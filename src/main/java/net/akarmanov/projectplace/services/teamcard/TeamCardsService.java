package net.akarmanov.projectplace.services.teamcard;

import net.akarmanov.projectplace.rest.api.teamcard.dto.TeamCardCreateOrUpdateDto;
import net.akarmanov.projectplace.rest.api.teamcard.dto.TeamCardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TeamCardsService {
    TeamCardDto createTeamCard(TeamCardCreateOrUpdateDto teamCardDto);

    TeamCardDto updateTeamCard(UUID teamCardId, TeamCardCreateOrUpdateDto teamCardDto);

    Page<TeamCardDto> getTeamCards(String name, String status, Pageable pageable);

    TeamCardDto getTeamCard(UUID id);

    void deleteTeamCard(UUID id);

    TeamCardDto createTeamCard(TeamCardCreateOrUpdateDto teamCardDto, UUID userId);

    TeamCardDto updateTeamCard(UUID teamCardId, TeamCardCreateOrUpdateDto teamCardDto, UUID userId);

    Page<TeamCardDto> findAll(String name, String status, Pageable pageable);

    TeamCardDto getTeamCard(UUID id, UUID userId);

    void deleteTeamCard(UUID id, UUID userId);
}
