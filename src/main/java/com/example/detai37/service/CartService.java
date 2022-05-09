package com.example.detai37.service;

import com.example.detai37.base.request.BasePageAndSortRequest;
import com.example.detai37.entity.*;
import com.example.detai37.model.ProductSale;
import com.example.detai37.model.ProductSaleId;
import com.example.detai37.repository.CartRepository;
import com.example.detai37.request.cart.CreateCartRequest;
import com.example.detai37.request.cart.UpdateCartRequest;
import com.example.detai37.ultis.PageableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private CartRepository cartRepository;
    private ProductService productService;
    private CustomerService customerService;

    public CartService(CartRepository cartRepository, ProductService productService, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    public Page<Cart> getAllCart(BasePageAndSortRequest pageAndSortRequest){
        Pageable pageable = PageableUtils.convertPageableAndSort(pageAndSortRequest.getPageNumber(), pageAndSortRequest.getPageSize(), pageAndSortRequest.getSort());
        Page<Cart> cartList = cartRepository.findAll(pageable);
        return cartList;
    }

    public Cart findCartById(String id){

        Optional<Cart> cartOptional = cartRepository.findById(id);
        Cart cart  = new Cart();
        if (cartOptional != null){
            cart = cartOptional.get();
        }
        return cart;
    }

    public Cart saveCart(CreateCartRequest createCartRequest){
//        Cart cart = MappingUtils.mapObject(createCartRequest, Cart.class);
        Cart cart = new Cart();
        Customer customer = customerService.findCustomerById(createCartRequest.getCustomerId());
        List<ProductSale> productList = new ArrayList<>();
        for(ProductSaleId productId: createCartRequest.getProductSale()){
            Product product = productService.findProductById(productId.getProductId());
            ProductSale productSale = ProductSale.builder()
                    .product(product)
                    .quantity(productId.getQuantity())
                    .build();
            productList.add(productSale);
        }
        cart.setCustomer(customer);
        cart.setProductList(productList);
        Double price = chargeTotal(cart);
        cart.setTotalPrice(price);
        Cart result = cartRepository.save(cart);
        return result;
    }

    public Cart updateCart(UpdateCartRequest updateCartRequest){
//        Cart cart = MappingUtils.mapObject(createCartRequest, Cart.class);
        Cart cart = new Cart();
        Customer customer = customerService.findCustomerById(updateCartRequest.getCustomerId());
        List<ProductSale> productList = new ArrayList<>();
        for(ProductSaleId productId: updateCartRequest.getProductSale()){
            Product product = productService.findProductById(productId.getProductId());
            ProductSale productSale = ProductSale.builder()
                    .product(product)
                    .quantity(productId.getQuantity())
                    .build();
            productList.add(productSale);
        }
        cart.setCustomer(customer);
        cart.setProductList(productList);
        Double price = chargeTotal(cart);
        cart.setTotalPrice(price);
        Cart result = cartRepository.save(cart);
        return result;
    }

    public Double chargeTotal(Cart cart){
        Double total = 0d;
        for(ProductSale product: cart.getProductList()){
            Double sum = (product.getProduct().getPrice() * product.getQuantity());
            Double discount =  sum * product.getProduct().getPercentDiscount();
            total += sum - discount;
        }
        return total;
    }
}
