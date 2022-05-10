package com.example.detai37.controller;

import com.example.detai37.base.request.BasePageAndSortRequest;
import com.example.detai37.entity.Bill;
import com.example.detai37.entity.Product;
import com.example.detai37.entity.ProductBrand;
import com.example.detai37.request.bill.CreateBillRequest;
import com.example.detai37.request.bill.SolvePaymentBillRequest;
import com.example.detai37.request.bill.UpdateBillRequest;
import com.example.detai37.request.productBrand.CreateProductBrandRequest;
import com.example.detai37.service.BillService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("bill")
public class BillController {
    private BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<Bill>> getAllBill(){
        return ResponseEntity.ok(billService.getAllBill());
    }

    @RequestMapping(value = "/get-all-with-page", method = RequestMethod.GET)
    public ResponseEntity<Page<Bill>> getAllBillWithPage(@RequestBody BasePageAndSortRequest pageAndSortRequest){
        return ResponseEntity.ok(billService.getAllBillWithPage(pageAndSortRequest));
    }

    @RequestMapping(value = "/find-by-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Bill> findProductById(@PathVariable("id") String billId){
        return ResponseEntity.ok(billService.findBillById(billId));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Bill> saveBill(@RequestBody CreateBillRequest createBillRequest){
        return ResponseEntity.ok(billService.saveBill(createBillRequest));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Bill> updateBill(@RequestBody UpdateBillRequest updateBillRequest){
        return ResponseEntity.ok(billService.updateBill(updateBillRequest));
    }

    @RequestMapping(value = "/solve-payment", method = RequestMethod.POST)
    public ResponseEntity<Bill> solvePayment(@RequestBody SolvePaymentBillRequest solvePaymentBillRequest){
        return ResponseEntity.ok(billService.solvePayment(solvePaymentBillRequest));
    }
}
