package net.akarmanov.projectplace.services;

import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.persistence.entities.UserPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UserPhotoService {
    void addPhoto(User user, MultipartFile file);

    UserPhoto getPhoto(UUID photoId);
}
