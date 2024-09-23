package com.ecommerce.api.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDTO {

    private Long idUser;
    private String name;
    private String surname;
    private String picture;
    private Date dateOfBirth;
    private String phoneAreaCode;  // Código de área do telefone
    private String phoneNumber;    // Número de telefone
    private String email;
    private String cpf;      // Número do documento

    private String city;
    private String district;  // Bairro
    private String streetName;    // Nome da rua
    private String streetNumber;  // Número da rua
    private String zipCode;       // CEP alterado para zipCode

}
