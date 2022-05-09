package com.example.detai37.request.product;

import com.example.detai37.entity.ProductBrand;
import com.example.detai37.entity.ProductType;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateProductRequest {
    private String name;
    private Integer size;
    private Integer stock; //Số lượng tồn
    private Long price;
    private Float percentDiscount;
    private String typeId;
    private String brandId;
    private String urlImageThumnail;
    private List<String> listImageDetail;

//    private Boolean status;
}
