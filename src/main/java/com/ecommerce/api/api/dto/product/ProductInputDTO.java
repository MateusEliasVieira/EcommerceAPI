package com.ecommerce.api.api.dto.product;


import com.ecommerce.api.domain.model.Category;
import com.ecommerce.api.domain.model.Measure;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductInputDTO {

    private Long idProduct;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Integer quantity;
    @NotNull
    private BigDecimal price;

    private Boolean onSale; // se for verdadeiro, devo mostrar esse novo preço
    private BigDecimal newPrice;
    private Integer discount; // porcentagem de desconto caso exista

    @NotBlank
    private String picture1;
    @NotBlank
    private String picture2;
    @NotBlank
    private String picture3;

    @NotNull
    private Category category;
    @NotNull
    private List<Measure> measure;

}
