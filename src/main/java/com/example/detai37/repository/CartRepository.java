package com.example.detai37.repository;

import com.example.detai37.entity.Cart;
import com.example.detai37.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String > {

}
