package net.akarmanov.projectplace.api.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserPhotoDto {
    private UUID id;
    private String fileName;
}
