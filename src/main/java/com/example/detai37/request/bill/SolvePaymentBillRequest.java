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
public class SolvePaymentBillRequest {
    private String billId;
    private String paymentType;
    private Boolean isSolvePayment;
}
