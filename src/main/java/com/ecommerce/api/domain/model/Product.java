package com.ecommerce.api.domain.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer quantityTotal; // quantidade total somando com a quantidade de cada medida

    private Boolean onSale; // se for verdadeiro, devo mostrar esse novo preço
    private BigDecimal newPrice;
    private Integer discount; // porcentagem de desconto caso exista

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String picture1;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String picture2;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String picture3;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<Measure> measure; // medida

    @OneToMany
    @JsonManagedReference
    private List<Avaliation> avaliation;

    @OneToMany
    @JsonManagedReference
    private List<Comment> comment;

}
