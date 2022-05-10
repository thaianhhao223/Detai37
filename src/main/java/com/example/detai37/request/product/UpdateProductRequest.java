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
public class UpdateProductRequest {
    @NotBlank
    @NotNull
    private String name;
    @NotNull
    @Size(min = 0)
    private Integer size;
    @NotNull
    @Size(min = 0)
    private Integer stock; //Số lượng tồn
    @NotNull
    @Size(min = 0)
    private Double price;
    @NotNull
    @Size(min = 0)
    private Float percentDiscount;
    @NotBlank
    @NotNull
    private String typeId;
    @NotBlank
    @NotNull
    private String brandId;
    private String urlImageThumnail;
    private List<String> listImageDetail;

//    private Boolean status;
}
