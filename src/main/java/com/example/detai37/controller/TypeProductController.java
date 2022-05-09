package com.example.detai37.controller;

import com.example.detai37.entity.ProductBrand;
import com.example.detai37.entity.ProductType;
import com.example.detai37.request.productBrand.CreateProductBrandRequest;
import com.example.detai37.request.productType.CreateProductTypeRequest;
import com.example.detai37.service.ProductTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("type-product")
public class TypeProductController {
    private ProductTypeService productTypeService;

    public TypeProductController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<ProductType>> getAllProductType(){
        return ResponseEntity.ok(productTypeService.getAllProductType());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ProductType> saveANewCustomer(@RequestBody CreateProductTypeRequest createProductTypeRequest){
        return ResponseEntity.ok(productTypeService.saveProductType(createProductTypeRequest));
    }
}

