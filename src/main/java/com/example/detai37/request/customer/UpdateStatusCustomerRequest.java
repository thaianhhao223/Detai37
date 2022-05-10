package com.example.detai37.request.customer;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateStatusCustomerRequest {
    @NotBlank
    @NotNull
    private String id;
    @NotNull
    private Integer status;
}
