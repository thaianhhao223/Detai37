package com.example.detai37.repository;

import com.example.detai37.entity.Customer;
import com.example.detai37.entity.SaleStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleStaffRepository extends JpaRepository<SaleStaff, String > {

}
