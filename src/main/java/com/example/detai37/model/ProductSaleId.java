package com.example.detai37.model;

import com.example.detai37.entity.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductSaleId {
    private String productId;
    private Integer quantity;
}
