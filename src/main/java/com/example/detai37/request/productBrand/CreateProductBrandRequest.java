package com.example.detai37.request.productBrand;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateProductBrandRequest {
    @NotBlank
    @NotNull
    private String name;
}
