package com.example.detai37.repository;

import com.example.detai37.entity.Bill;
import com.example.detai37.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, String > {

}
