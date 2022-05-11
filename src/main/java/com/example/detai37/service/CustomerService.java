package com.example.detai37.service;

import com.example.detai37.base.request.BasePageAndSortRequest;
import com.example.detai37.base.request.BaseSort;
import com.example.detai37.common.CustomerStatus;
import com.example.detai37.entity.Customer;
import com.example.detai37.repository.CustomerRepository;
import com.example.detai37.request.customer.CreateCustomerRequest;
import com.example.detai37.request.customer.UpdateCustomerRequest;
import com.example.detai37.request.customer.UpdateStatusCustomerRequest;
import com.example.detai37.ultis.MappingUtils;
import com.example.detai37.ultis.PageableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<Customer> getAllCustommer(BasePageAndSortRequest pageAndSortRequest){
        Pageable pageable = PageableUtils.convertPageableAndSort(pageAndSortRequest.getPageNumber(), pageAndSortRequest.getPageSize(), pageAndSortRequest.getSort());
        Page<Customer> customerList = customerRepository.findAll(pageable);
//        List<Customer> customerList = customerRepository.findAll();
        return customerList;
    }

    public Customer findCustomerById(String id){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        Customer customer = null;
        if (customerOptional.isPresent()){
            customer = customerOptional.get();
        }
        return customer;
    }

    public Customer findCustomerByPhoneNumber(String phoneNumber){
        Optional<Customer> customerOptional = customerRepository.findCustomerByPhoneNumber(phoneNumber);
        Customer customer = null;
        if (customerOptional.isPresent()){
            customer = customerOptional.get();
        }
        return customer;
    }

    public Customer saveCustomer(CreateCustomerRequest createCustomerRequest){
        Customer customer = MappingUtils.mapObject(createCustomerRequest, Customer.class);
        customer.setStatus(CustomerStatus.NEW_CUSTOMER);
        Customer result = customerRepository.save(customer);
        return result;
//        return customer;
    }

    public Customer updateCustomer(UpdateCustomerRequest updateCustomerRequest){
        Customer customer = MappingUtils.mapObject(updateCustomerRequest, Customer.class);
        Customer oldCustomer = customerRepository.getById(updateCustomerRequest.getId());
        if(oldCustomer!=null){
            customer.setStatus(oldCustomer.getStatus());
        }
//        else {
//            throw new Exception();
//        }
        Customer result = customerRepository.save(customer);
        return result;
    }

    public Customer updateStatusCustomer(UpdateStatusCustomerRequest updateStatusCustomerRequest){
        Customer oldCustomer = customerRepository.getById(updateStatusCustomerRequest.getId());
        if(oldCustomer!=null){
            oldCustomer.setStatus(updateStatusCustomerRequest.getStatus());
        }
//        Customer customer = MappingUtils.mapObject(updateCustomerRequest, Customer.class);
        Customer result = customerRepository.save(oldCustomer);
        return result;
    }
}
