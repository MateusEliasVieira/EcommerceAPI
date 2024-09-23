package com.ecommerce.api.api.dto.product;


import com.ecommerce.api.api.dto.avaliation.AvaliationOutputDTO;
import com.ecommerce.api.api.dto.comment.CommentOutputDTO;
import com.ecommerce.api.domain.model.Category;
import com.ecommerce.api.domain.model.Measure;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductOutputDTO {

    private Long idProduct;
    private String title;
    private String description;
    private Integer quantityTotal;
    private BigDecimal price;

    private Boolean onSale; // se for verdadeiro, devo mostrar esse novo preço
    private BigDecimal newPrice;
    private Integer discount; // porcentagem de desconto caso exista

    private String picture1;
    private String picture2;
    private String picture3;

    private Category category;
    private List<Measure> measure;

    private List<CommentOutputDTO> comment;
    private List<AvaliationOutputDTO> avaliation;


}
