package com.example.detai37.request.cart;

import com.example.detai37.model.ProductSaleId;
import lombok.*;

import javax.persistence.ElementCollection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateCartRequest {
    private String customerId;
    private List<ProductSaleId> productSale;
}
