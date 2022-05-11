package com.example.detai37.repository;

import com.example.detai37.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String > {
    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);
}
