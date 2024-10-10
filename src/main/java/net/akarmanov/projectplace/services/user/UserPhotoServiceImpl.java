package net.akarmanov.projectplace.services.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.persistence.entities.UserPhoto;
import net.akarmanov.projectplace.persistence.jpa.UserPhotoRepository;
import net.akarmanov.projectplace.persistence.jpa.UserRepository;
import net.akarmanov.projectplace.services.exceptions.PhotoNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserPhotoServiceImpl implements UserPhotoService {

    private final UserPhotoRepository userPhotoRepository;

    private final UserRepository userRepository;

    @Override
    public UserPhoto getPhoto(UUID photoId) {
        return userPhotoRepository.findById(photoId)
                .orElseThrow(() -> new PhotoNotFoundException(photoId));
    }

    @SneakyThrows
    @Override
    @Transactional
    public void addPhotoToUser(UUID userId, MultipartFile file) {

        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            deletePhoto(userId);
        } else {
            addPhoto(user.get(), file);
        }
    }

    @Override
    @Transactional
    public void deletePhoto(UUID userId) {
        userPhotoRepository.deleteByUserId(userId);
    }

    @Override
    @Transactional
    public UserPhoto getPhotoByUserId(UUID userId) {
        return userPhotoRepository.findByUserId(userId)
                .orElseThrow(() -> new PhotoNotFoundException(userId));
    }

    @SneakyThrows
    private void addPhoto(User user, MultipartFile file) {
        try (var is = file.getInputStream()) {
            var photo = UserPhoto.builder()
                    .user(user)
                    .photo(is.readAllBytes())
                    .fileName(file.getOriginalFilename())
                    .build();
            userPhotoRepository.save(photo);
        }
    }
}
