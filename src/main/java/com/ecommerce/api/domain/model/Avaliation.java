package com.ecommerce.api.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Avaliation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvaliation;
    @Min(0) @Max(5)
    private Integer avaliation;

    @ManyToOne
    @JoinColumn
    @JsonBackReference // Evita a recursão cíclica ao serializar o Product
    private User user;

}
