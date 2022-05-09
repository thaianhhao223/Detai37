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
public class Bill {
    @Id
    private String id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private SaleStaff saleStaff;
    @ElementCollection
    private List<ProductSale> productList;
    private Date dateSale;
    private Date dateDelivery;
    private Long totalPrice;
    private Float percentDiscount;

    private String paymentType;
    private String status;
}
