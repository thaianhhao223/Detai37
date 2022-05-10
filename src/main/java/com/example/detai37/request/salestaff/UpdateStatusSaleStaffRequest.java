package com.example.detai37.request.salestaff;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateStatusSaleStaffRequest {
    private String id;
    @NotNull
    private Integer status;
}
