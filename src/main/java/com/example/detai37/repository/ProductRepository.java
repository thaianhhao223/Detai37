package com.example.detai37.repository;

import com.example.detai37.entity.Product;
import com.example.detai37.entity.SaleStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String > {

}
