package com.ecommerce.api.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentPreferenceDTO {

    // Dados do produto
    @NotBlank
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String pictureUrl;
    @NotBlank
    private String description;
    @NotBlank
    private String categoryId;
    @NotNull
    private Integer quantity;
    @NotNull
    private BigDecimal unitPrice;

    // Dados do comprador
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String email;
    @NotBlank
    private String phoneAreaCode;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @CPF
    private String cpf;
    @NotBlank
    private String streetName;
    @NotBlank
    private String streetNumber;
    @NotBlank
    private String zipCode;

}
