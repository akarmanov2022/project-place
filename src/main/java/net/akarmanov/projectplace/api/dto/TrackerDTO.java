package net.akarmanov.projectplace.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.akarmanov.projectplace.persistence.entities.TrackerStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Трекер.")
public class TrackerDTO {
    @Schema(description = "Пользователь.")
    private UserDTO user;

    @Schema(description = "Дата создания.")
    private LocalDateTime createDate;

    @Schema(description = "Статус трекера.")
    private TrackerStatus status;
}
