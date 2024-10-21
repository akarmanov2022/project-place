package net.akarmanov.projectplace.mapping;

import net.akarmanov.projectplace.domain.TeamCard;
import net.akarmanov.projectplace.rest.api.teamcard.dto.TeamCardCreateOrUpdateDto;
import net.akarmanov.projectplace.rest.api.teamcard.dto.TeamCardDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class})
public interface TeamCardMapper {
    TeamCard mapToEntity(TeamCardDto dto);

    TeamCard mapToEntity(TeamCardCreateOrUpdateDto dto);

    TeamCardDto mapToDto(TeamCard entity);

    void updateFromDto(TeamCardCreateOrUpdateDto dto, @MappingTarget TeamCard entity);
}
