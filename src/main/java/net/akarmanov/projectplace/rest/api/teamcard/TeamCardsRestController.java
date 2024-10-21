package net.akarmanov.projectplace.rest.api.teamcard;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.akarmanov.projectplace.rest.api.teamcard.dto.TeamCardCreateOrUpdateDto;
import net.akarmanov.projectplace.rest.api.teamcard.dto.TeamCardDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Team Cards API", description = "API для работы с карточками команд")
@RequestMapping("/api/v1/team-cards")
public interface TeamCardsRestController {
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Создание карточки команды")
    ResponseEntity<TeamCardDto> createTeamCard(@Valid @RequestBody TeamCardCreateOrUpdateDto teamCardDto);

    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Обновление карточки команды")
    ResponseEntity<TeamCardDto> updateTeamCard(@RequestParam UUID teamCardId,
                                               @Valid @RequestBody TeamCardCreateOrUpdateDto teamCardDto);

    @GetMapping(produces = "application/json")
    @Operation(summary = "Получение списка карточек команд")
    ResponseEntity<PagedModel<TeamCardDto>> getTeamCards(@RequestParam String name, @RequestParam String status,
                                                         @RequestParam @ParameterObject Pageable pageable);

    @GetMapping(value = "/get", produces = "application/json")
    @Operation(summary = "Получение карточки команды")
    ResponseEntity<TeamCardDto> getTeamCard(
            @Parameter(description = "Идентификатор карточки команды", example = "123e4567-e89b-12d3-a456-426614174000")
            @RequestParam
            UUID id);
}
