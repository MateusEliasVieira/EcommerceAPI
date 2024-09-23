package com.ecommerce.api.api.dto.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginInputDTO {

    @NotBlank(message = "Nome de usuário obrigatório!")
    private String username;

    @NotBlank(message = "Senha é obrigatória!")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres!")
    private String password;

}
