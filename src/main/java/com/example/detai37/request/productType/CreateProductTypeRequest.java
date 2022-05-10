package com.example.detai37.request.productType;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateProductTypeRequest {
    @NotBlank
    @NotNull
    private String name;
}
