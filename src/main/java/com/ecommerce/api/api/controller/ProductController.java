package com.ecommerce.api.api.controller;

import com.ecommerce.api.api.dto.product.ProductOutputDTO;
import com.ecommerce.api.api.mapper.ProductMapper;
import com.ecommerce.api.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/list")
    public ResponseEntity<?> listProducts() {
        return new ResponseEntity<List<ProductOutputDTO>>(
                productMapper.convertListProductToListProductOutputDTO(service.listProducts()), HttpStatus.OK);
    }

}
