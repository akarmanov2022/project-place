package net.akarmanov.projectplace.services.user;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.persistence.entities.UserPhoto;
import net.akarmanov.projectplace.persistence.jpa.UserPhotoRepository;
import net.akarmanov.projectplace.services.exceptions.PhotoNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPhotoServiceImpl implements UserPhotoService {

    private final UserPhotoRepository userPhotoRepository;

    @SneakyThrows
    @Override
    public void addPhoto(User user, MultipartFile file) {
        try (var inputStream = file.getInputStream()) {
            var userPhoto = new UserPhoto();
            userPhoto.setUser(user);
            userPhoto.setFileName(file.getOriginalFilename());
            userPhoto.setPhoto(inputStream.readAllBytes());
            userPhotoRepository.save(userPhoto);
        }
    }

    @Override
    public UserPhoto getPhoto(UUID photoId) {
        return userPhotoRepository.findById(photoId)
                .orElseThrow(() -> new PhotoNotFoundException(photoId));
    }
}
