package com.example.detai37.controller;

import com.example.detai37.base.request.BasePageAndSortRequest;
import com.example.detai37.entity.Customer;
import com.example.detai37.request.customer.CreateCustomerRequest;
import com.example.detai37.request.customer.UpdateCustomerRequest;
import com.example.detai37.request.customer.UpdateStatusCustomerRequest;
import com.example.detai37.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ResponseEntity<Page<Customer>> getAllCustomer(@RequestBody BasePageAndSortRequest pageAndSortRequest){
        return ResponseEntity.ok(customerService.getAllCustommer(pageAndSortRequest));
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<Customer> saveANewCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        return ResponseEntity.ok(customerService.saveCustomer(createCustomerRequest));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> saveANewCustomer(){
        return ResponseEntity.ok(customerService.getAllCustommer());
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Customer updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest){
        Customer result = customerService.updateCustomer(updateCustomerRequest);
        return result;
    }


    @RequestMapping(value = "/customer/status", method = RequestMethod.POST)
    public Customer updateStatusCustomer(@RequestBody UpdateStatusCustomerRequest updateStatusCustomerRequest){
        Customer result = customerService.updateStatusCustomer(updateStatusCustomerRequest);
        return result;
    }
}
