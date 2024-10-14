package net.akarmanov.projectplace.services.user;

import net.akarmanov.projectplace.domain.User;
import net.akarmanov.projectplace.domain.UserPhoto;
import net.akarmanov.projectplace.repos.UserPhotoRepository;
import net.akarmanov.projectplace.repos.UserRepository;
import net.akarmanov.projectplace.services.exceptions.PhotoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserPhotoServiceTest {

    @Mock
    private UserPhotoRepository userPhotoRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MultipartFile file;

    @InjectMocks
    private UserPhotoServiceImpl userPhotoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPhoto_throwsException_whenPhotoDoesNotExist() {
        UUID photoId = UUID.randomUUID();
        when(userPhotoRepository.findById(photoId)).thenReturn(Optional.empty());

        assertThrows(PhotoNotFoundException.class, () -> userPhotoService.getPhoto(photoId));
    }

    @Test
    void addPhotoToUser_addsPhoto_whenUserExists() throws Exception {
        UUID userId = UUID.randomUUID();
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(file.getInputStream()).thenReturn(mock(java.io.InputStream.class));

        userPhotoService.addPhotoToUser(userId, file);

        verify(userPhotoRepository, times(1)).save(any(UserPhoto.class));
    }

    @Test
    void addPhotoToUser_deletesPhoto_whenUserDoesNotExist() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        userPhotoService.addPhotoToUser(userId, file);

        verify(userPhotoRepository, times(1)).deleteByUserId(userId);
    }

    @Test
    void deletePhoto_deletesPhoto_whenPhotoExists() {
        UUID userId = UUID.randomUUID();
        UserPhoto userPhoto = new UserPhoto();
        when(userPhotoRepository.findByUserId(userId)).thenReturn(Optional.of(userPhoto));

        userPhotoService.deletePhoto(userId);

        verify(userPhotoRepository, times(1)).deleteByUserId(userId);
    }

    @Test
    void deletePhoto_doesNothing_whenPhotoDoesNotExist() {
        UUID userId = UUID.randomUUID();
        when(userPhotoRepository.findByUserId(userId)).thenReturn(Optional.empty());

        userPhotoService.deletePhoto(userId);

        verify(userPhotoRepository, never()).delete(any(UserPhoto.class));
    }

    @Test
    void getPhotoByUserId_throwsException_whenPhotoDoesNotExist() {
        UUID userId = UUID.randomUUID();
        when(userPhotoRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(PhotoNotFoundException.class, () -> userPhotoService.getPhotoByUserId(userId));
    }
}