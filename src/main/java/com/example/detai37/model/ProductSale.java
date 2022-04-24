package com.example.detai37.model;

import com.example.detai37.entity.Product;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductSale {
    @ManyToOne
    private Product product;
    private Integer quantity;
}
