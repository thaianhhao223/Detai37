package com.example.detai37.repository;

import com.example.detai37.entity.Account;
import com.example.detai37.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String > {

}
