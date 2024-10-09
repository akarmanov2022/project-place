package net.akarmanov.projectplace.configuration;

import net.akarmanov.projectplace.api.user.UserDTO;
import net.akarmanov.projectplace.models.UserRole;
import net.akarmanov.projectplace.persistence.entities.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    Converter<String, UserRole> userRoleConverter =
            mappingContext -> UserRole.valueOf(mappingContext.getSource().toUpperCase());

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.createTypeMap(UserDTO.class, User.class)
                .addMappings(mapper -> mapper.using(userRoleConverter).map(UserDTO::getRole, User::setRole)
                );
        return modelMapper;
    }
}
