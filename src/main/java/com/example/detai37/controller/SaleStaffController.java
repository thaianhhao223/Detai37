package com.example.detai37.controller;

import com.example.detai37.entity.Customer;
import com.example.detai37.request.customer.CreateCustomerRequest;
import com.example.detai37.service.CustomerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("salestaffs")
public class SaleStaffController {

    private CustomerService customerService;

    public SaleStaffController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/salestaff", method = RequestMethod.GET)
    public List<Customer> getAllCustomer(){
        List<Customer> result = customerService.getAllCustommer();
        return result;
    }

    @RequestMapping(value = "/salestaff", method = RequestMethod.POST)
    public Customer saveANewCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        Customer result = customerService.saveCustomer(createCustomerRequest);
        return result;
    }
}
