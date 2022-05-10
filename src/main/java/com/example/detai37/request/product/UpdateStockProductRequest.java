package com.example.detai37.request.product;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateStockProductRequest {
    @NotBlank
    @NotNull
    private String productId;
    @NotNull
    @Size(min = 1)
    private Integer stockHadSolve; //Số lượng tồn

}
