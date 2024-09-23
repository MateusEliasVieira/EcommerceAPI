package com.ecommerce.api.api.dto.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private String picture;
    @NotNull
    private Date dateOfBirth;
    @NotBlank
    @Column(unique = true)
    private String phoneAreaCode;  // Código de área do telefone
    @NotBlank
    @Column(unique = true)
    private String phoneNumber;    // Número de telefone
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    @CPF
    private String cpf;      // Número do documento

    @NotBlank
    private String city;
    @NotBlank
    private String district;  // Bairro
    @NotBlank
    private String streetName;    // Nome da rua
    @NotBlank
    private String streetNumber;  // Número da rua
    @NotBlank
    private String zipCode;       // CEP alterado para zipCode

}
