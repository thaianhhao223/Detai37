package com.example.detai37.controller;

import com.example.detai37.entity.Customer;
import com.example.detai37.entity.ProductBrand;
import com.example.detai37.request.customer.CreateCustomerRequest;
import com.example.detai37.request.productBrand.CreateProductBrandRequest;
import com.example.detai37.service.ProductBrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("brand-product")
public class BrandProductController {
    private ProductBrandService productBrandService;

    public BrandProductController(ProductBrandService productBrandService) {
        this.productBrandService = productBrandService;
    }


    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<ProductBrand>> getAllProductBrand(){
        return ResponseEntity.ok(productBrandService.getAllProductBrand());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ProductBrand> saveANewCustomer(@RequestBody CreateProductBrandRequest createProductBrandRequest){
        return ResponseEntity.ok(productBrandService.saveProductBrand(createProductBrandRequest));
    }
}
