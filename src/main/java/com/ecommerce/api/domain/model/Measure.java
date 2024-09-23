package com.ecommerce.api.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Measure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMeasure;
    private String measure;
    private Integer quantity;

    @ManyToOne
    @JoinColumn
    @JsonBackReference // Evita a recursão cíclica ao serializar o Product
    private Product product;
}
