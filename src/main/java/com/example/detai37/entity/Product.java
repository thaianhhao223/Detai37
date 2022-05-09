package com.example.detai37.entity;

import lombok.*;

import javax.persistence.*;
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
    @Column(columnDefinition = "NVarchar(255)")
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

    private Boolean status; // true if it avaible
}
