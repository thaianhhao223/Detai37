package com.example.detai37.controller;

import com.example.detai37.entity.Customer;
import com.example.detai37.request.customer.CreateCustomerRequest;
import com.example.detai37.request.customer.UpdateCustomerRequest;
import com.example.detai37.request.customer.UpdateStatusCustomerRequest;
import com.example.detai37.service.CustomerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<Customer> getAllCustomer(){
        List<Customer> result = customerService.getAllCustommer();
        return result;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public Customer saveANewCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        Customer result = customerService.saveCustomer(createCustomerRequest);
        return result;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public Customer updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest){
        Customer result = customerService.updateCustomer(updateCustomerRequest);
        return result;
    }


    @RequestMapping(value = "/customer/status", method = RequestMethod.PUT)
    public Customer updateStatusCustomer(@RequestBody UpdateStatusCustomerRequest updateStatusCustomerRequest){
        Customer result = customerService.updateStatusCustomer(updateStatusCustomerRequest);
        return result;
    }
}
