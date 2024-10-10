package net.akarmanov.projectplace.configuration;

import net.akarmanov.projectplace.api.user.dto.UserCreateRequest;
import net.akarmanov.projectplace.persistence.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.createTypeMap(UserCreateRequest.class, User.class)
                .addMappings(mapper -> mapper.skip(User::setId));
        return modelMapper;
    }
}
