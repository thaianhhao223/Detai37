package com.example.detai37.controller;

import com.example.detai37.base.request.BasePageAndSortRequest;
import com.example.detai37.entity.Customer;
import com.example.detai37.entity.SaleStaff;
import com.example.detai37.request.customer.CreateCustomerRequest;
import com.example.detai37.request.customer.UpdateCustomerRequest;
import com.example.detai37.request.customer.UpdateStatusCustomerRequest;
import com.example.detai37.request.salestaff.CreateSaleStaffRequest;
import com.example.detai37.request.salestaff.UpdateSaleStaffRequest;
import com.example.detai37.request.salestaff.UpdateStatusSaleStaffRequest;
import com.example.detai37.service.CustomerService;
import com.example.detai37.service.SaleStaffService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("salestaffs")
public class SaleStaffController {

    private SaleStaffService saleStaffService;
    public SaleStaffController(SaleStaffService saleStaffService ) {
        this.saleStaffService = saleStaffService;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<Page<SaleStaff>> getAllSaleStaff(@RequestBody BasePageAndSortRequest pageAndSortRequest){
        return ResponseEntity.ok(saleStaffService.getAllSaleStaff(pageAndSortRequest));
    }

    @RequestMapping(value = "/find-by-id/{id}", method = RequestMethod.POST)
    public ResponseEntity<SaleStaff> saveSaleStaff(@PathVariable("id") String saleStaffId){
        return ResponseEntity.ok(saleStaffService.findSaleStaffById(saleStaffId));
    }

    @RequestMapping(value = "/save-sale-staff", method = RequestMethod.POST)
    public ResponseEntity<SaleStaff> saveSaleStaff(@RequestBody CreateSaleStaffRequest createSaleStaffRequest){
        return ResponseEntity.ok(saleStaffService.saveSaleStaff(createSaleStaffRequest));
    }

    @RequestMapping(value = "/update-sale-staff", method = RequestMethod.POST)
    public ResponseEntity<SaleStaff> updateSaleStaff(@RequestBody UpdateSaleStaffRequest updateSaleStaffRequest){
        return ResponseEntity.ok(saleStaffService.updateSaleStaff(updateSaleStaffRequest));
    }


    @RequestMapping(value = "/update-sale-staff-status", method = RequestMethod.PUT)
    public ResponseEntity<SaleStaff> updateStatusSaleStaff(@RequestBody UpdateStatusSaleStaffRequest updateStatusSaleStaffRequest){
        return ResponseEntity.ok(saleStaffService.updateStatusSaleStaff(updateStatusSaleStaffRequest));
    }
}
