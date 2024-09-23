package com.ecommerce.api.api.mapper;

import com.ecommerce.api.api.dto.login.LoginInputDTO;
import com.ecommerce.api.api.dto.login.LoginInputGoogleDTO;
import com.ecommerce.api.api.dto.login.LoginOutputDTO;
import com.ecommerce.api.api.mapper.config.ModelMapperConfig;
import com.ecommerce.api.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    @Autowired
    private ModelMapperConfig modelMapperConfig;

    public User mapperLoginInputDTOToUser(LoginInputDTO loginInputDTO) {
        return modelMapperConfig.modelMapper().map(loginInputDTO, User.class);
    }

    public User mapperLoginInputGoogleDTOToUser(LoginInputGoogleDTO loginInputGoogleDTO) {
        return modelMapperConfig.modelMapper().map(loginInputGoogleDTO, User.class);
    }

    public LoginOutputDTO mapperUserToLoginOutputDTO(User user) {
        return modelMapperConfig.modelMapper().map(user, LoginOutputDTO.class);
    }
}
