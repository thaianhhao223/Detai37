package com.example.detai37.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(columnDefinition = "NVarchar(255)")
    private String name;
    @Column(columnDefinition = "ntext")
    private String description;
    private Integer size;
    private Integer stock; //Số lượng tồn
    private Double price;
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
