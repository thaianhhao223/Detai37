package com.example.detai37.request.product;

import com.example.detai37.entity.ProductBrand;
import com.example.detai37.entity.ProductType;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateProductRequest {
    @NotBlank
    @NotNull
    private String name;
    @NotNull
    @Size(min = 0)
    private Integer size;
    private String description;
    @NotNull
    @Size(min = 0)
    private Integer stock; //Số lượng tồn
    @NotNull
    @Size(min = 0)
    private Double price;
    @NotNull
    @Size(min = 0)
    private Float percentDiscount;
    @NotNull
    private String typeId;
    @NotNull
    private String brandId;
    private String urlImageThumnail;
    private List<String> listImageDetail;

//    private Boolean status;
}
