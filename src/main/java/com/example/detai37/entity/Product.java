package com.example.detai37.entity;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Product {
    @Id
    private String id;
    private String name;
    private Integer size;
    private Integer stock; //Số lượng tồn
    private Long price;
    private Float percentDiscount;
    @ManyToOne
    private ProductType type;
    @ManyToOne
    private ProductBrand brand;
    private String urlImageThumnail;
    @ElementCollection
    private List<String> listImageDetail;

    private Boolean status;
}
