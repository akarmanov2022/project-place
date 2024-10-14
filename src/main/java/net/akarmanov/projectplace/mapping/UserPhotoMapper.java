package net.akarmanov.projectplace.mapping;

import net.akarmanov.projectplace.domain.UserPhoto;
import net.akarmanov.projectplace.services.user.UserPhotoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPhotoMapper {
    UserPhotoMapper INSTANCE = Mappers.getMapper(UserPhotoMapper.class);

    UserPhotoDto toModel(UserPhoto userPhoto);

    UserPhoto toEntity(UserPhotoDto userPhotoDto);
}
