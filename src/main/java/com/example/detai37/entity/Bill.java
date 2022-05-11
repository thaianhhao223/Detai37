package com.example.detai37.entity;

import com.example.detai37.model.ProductSale;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @ManyToOne
    private Customer customer;
//    @ManyToOne
//    private SaleStaff saleStaff;
    @ElementCollection
    private List<ProductSale> productList;
    private Date dateSale;
    private Date dateDelivery;
    private Double totalPrice;
    private Float percentDiscount;
    @Column(columnDefinition = "NVarchar(50)")
    private String paymentType;
    @Column(columnDefinition = "NVarchar(50)")
    private String status;
}
