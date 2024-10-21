package net.akarmanov.projectplace.mapping;

import net.akarmanov.projectplace.domain.User;
import net.akarmanov.projectplace.rest.api.dto.UserDTO;
import net.akarmanov.projectplace.rest.api.dto.UserUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {UserPhotoMapper.class})
public interface UserMapper {


    UserDTO mapUserToDto(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userTeamCards", ignore = true)
    @Mapping(target = "streamsUserStreams", ignore = true)
    User mapDtoToUser(UserDTO userDTO);

    void updateFromDto(UserUpdateDTO userDTO, @MappingTarget User user);
}
