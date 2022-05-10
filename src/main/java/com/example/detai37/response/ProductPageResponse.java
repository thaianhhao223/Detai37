package com.example.detai37.response;

import com.example.detai37.entity.Product;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductPageResponse {
    private List<Product> productList;
}
