package com.ecommerce.api.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    @Size(max = 500) // máximo de 500 caracteres
    private String comment;

    @ManyToOne
    @JoinColumn
    @JsonBackReference // Evita a recursão cíclica ao serializar o Product
    private User user;
}
