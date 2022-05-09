package com.example.detai37.controller;

import com.example.detai37.base.request.BasePageAndSortRequest;
import com.example.detai37.entity.Cart;
import com.example.detai37.entity.Product;
import com.example.detai37.request.cart.CreateCartRequest;
import com.example.detai37.request.cart.UpdateCartRequest;
import com.example.detai37.request.product.CreateProductRequest;
import com.example.detai37.request.product.UpdateProductRequest;
import com.example.detai37.service.CartService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("cart")
public class CartController {
    private CartService cartService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<Page<Cart>> getAllCart(@RequestBody BasePageAndSortRequest pageAndSortRequest){
        return ResponseEntity.ok(cartService.getAllCart(pageAndSortRequest));
    }

    @RequestMapping(value = "/find-by-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cart> findCartById(@PathVariable String cartId){
        return ResponseEntity.ok(cartService.findCartById(cartId));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Cart> saveProduct(@RequestBody CreateCartRequest createCartRequest){
        return ResponseEntity.ok(cartService.saveCart(createCartRequest));
    }

    @RequestMapping(value = "/update-cart", method = RequestMethod.POST)
    public ResponseEntity<Cart> updateProduct(@RequestBody UpdateCartRequest updateCartRequest){
        return ResponseEntity.ok(cartService.updateCart(updateCartRequest));
    }

}
