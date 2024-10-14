package net.akarmanov.projectplace.mapping;

import net.akarmanov.projectplace.domain.User;
import net.akarmanov.projectplace.rest.api.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);
}
