package com.ecommerce.api.api.mapper;

import com.ecommerce.api.api.dto.user.UserInputDTO;
import com.ecommerce.api.api.dto.user.UserOutputDTO;
import com.ecommerce.api.api.mapper.config.ModelMapperConfig;
import com.ecommerce.api.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapperConfig modelMapperConfig;

    public UserOutputDTO mapperUserToUserOutputDTO(User user) {

        return modelMapperConfig.modelMapper().map(user, UserOutputDTO.class);
    }

    public User mapperUserInputDTOToUser(UserInputDTO userInputDTO) {
        return modelMapperConfig.modelMapper().map(userInputDTO, User.class);
    }

}
