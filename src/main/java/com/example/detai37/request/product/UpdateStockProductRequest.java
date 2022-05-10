package com.example.detai37.request.product;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateStockProductRequest {
    private String productId;
    private Integer stockHadSolve; //Số lượng tồn

}
