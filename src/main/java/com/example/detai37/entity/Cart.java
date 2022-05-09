package com.example.detai37.entity;

import com.example.detai37.model.ProductSale;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Cart {
    @Id
    private String id;
    @ManyToOne
    private Customer customer;
    @ElementCollection
    private List<ProductSale> productList;
    private Double totalPrice;
    private Float percentDiscount;
}
