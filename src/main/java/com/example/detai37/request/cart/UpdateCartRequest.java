package com.example.detai37.request.cart;

import com.example.detai37.model.ProductSaleId;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateCartRequest {
    @NotBlank
    @NotNull
    private String cartId;
    @NotBlank
    @NotNull
    private String customerId;
    @NotNull
    private List<ProductSaleId> productSale;
}
