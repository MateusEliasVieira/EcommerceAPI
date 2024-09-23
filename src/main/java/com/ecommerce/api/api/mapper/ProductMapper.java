package com.ecommerce.api.api.mapper;

import com.ecommerce.api.api.dto.product.ProductOutputDTO;
import com.ecommerce.api.api.mapper.config.ModelMapperConfig;
import com.ecommerce.api.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapperConfig modelMapperConfig;

    public ProductOutputDTO convertProductToProductOutputDTO(Product product) {
        return modelMapperConfig.modelMapper().map(product, ProductOutputDTO.class);
    }

    public List<ProductOutputDTO> convertListProductToListProductOutputDTO(List<Product> products) {
        List<ProductOutputDTO> listProductOutputDTO = new ArrayList<>();
        products.forEach(product -> {
            listProductOutputDTO.add(modelMapperConfig.modelMapper().map(product, ProductOutputDTO.class));
        });
        return listProductOutputDTO;
    }


}
