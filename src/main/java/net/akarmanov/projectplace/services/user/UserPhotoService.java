package net.akarmanov.projectplace.services.user;

import net.akarmanov.projectplace.persistence.entities.UserPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UserPhotoService {
    UserPhoto getPhoto(UUID photoId);

    void addPhotoToUser(UUID userId, MultipartFile file);

    void deletePhoto(UUID userId);

    UserPhoto getPhotoByUserId(UUID userId);
}
