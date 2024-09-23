package com.ecommerce.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Buy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBuy;
    private OffsetDateTime dateBuy = OffsetDateTime.now();
    private BigDecimal total;

    @ManyToOne
    @JoinColumn
    private User user;


}
