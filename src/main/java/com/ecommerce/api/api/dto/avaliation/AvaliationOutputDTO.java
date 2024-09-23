package com.ecommerce.api.api.dto.avaliation;

import com.ecommerce.api.api.dto.user.UserAvaliationOutputDTO;
import com.ecommerce.api.domain.model.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AvaliationOutputDTO {


    private Long idAvaliation;
    private Integer avaliation;
    private UserAvaliationOutputDTO user;
    private Product product;

}
