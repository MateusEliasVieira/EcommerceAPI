package com.ecommerce.api.domain.model;

import com.ecommerce.api.domain.enumerateds.CategoryEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    private CategoryEnum category;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Product> product;

}
