package com.example.detai37.request.bill;

import com.example.detai37.model.ProductSaleId;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateBillRequest {
    @NotBlank
    @NotNull
    private String billId;
    @NotBlank
    @NotNull
    private String customerId;
//    @NotBlank
//    @NotNull
//    private String saleStaffId;

    @NotNull
    private List<ProductSaleId> productSaleIdList;
    private Date dateDelivery;
    private Float percentDiscount;
    private String paymentType;
}
