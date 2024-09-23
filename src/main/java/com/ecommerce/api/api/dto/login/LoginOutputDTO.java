package com.ecommerce.api.api.dto.login;

import com.ecommerce.api.domain.enumerateds.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginOutputDTO {

    @NotNull
    private Long idUser;
    @NotBlank
    private String token;
    @NotNull
    private Role role;

}
