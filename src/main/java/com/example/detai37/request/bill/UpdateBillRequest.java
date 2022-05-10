package com.example.detai37.request.bill;

import com.example.detai37.model.ProductSaleId;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateBillRequest {

    private String billId;
    private String customerId;
    private String saleStaffId;
    private List<ProductSaleId> productSaleIdList;
    private Date dateDelivery;
    private Float percentDiscount;
    private String paymentType;
}
