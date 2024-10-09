package net.akarmanov.projectplace.persistence.jpa;

import jakarta.validation.constraints.NotNull;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.persistence.entities.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserPhotoRepository extends JpaRepository<UserPhoto, UUID> {

    Optional<UserPhoto> findByUser(@NotNull User user);
}
