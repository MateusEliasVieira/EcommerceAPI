package com.ecommerce.api.api.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginInputGoogleDTO {

    @NotBlank(message = "Campo nome é obrigatório!")
    @Size(min = 4, message = "Campo nome é obrigatório")
    private String name;

    @NotBlank(message = "Campo nome de usuário é obrigatório!")
    private String username;

    @NotBlank(message = "Campo de senha é obrigatório!")
    @Size(min = 6, message = "A senha deve conter pelo menos 6 caracteres")
    private String password;

    @NotBlank(message = "Campo nome é obrigatório")
    private String about;

    @NotBlank(message = "Campo nome é obrigatório")
    @Email(message = "Campo nome é obrigatório")
    private String email;

    @NotBlank(message = "Campo nome é obrigatório")
    private String image;

}
