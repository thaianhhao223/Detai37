package com.example.detai37.service;

import com.example.detai37.entity.Cart;
import com.example.detai37.entity.Product;
import com.example.detai37.repository.CartRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartRepository cartRepository;
    public List<Cart> getAllCart(){
        List<Cart> cartList = cartRepository.findAll();

        return cartList;
    }
}
