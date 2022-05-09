package com.example.detai37.controller;

import com.example.detai37.entity.Product;
import com.example.detai37.entity.ProductType;
import com.example.detai37.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }
    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @RequestMapping(value = "/find-by-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findProductById(@PathVariable String productId){
        return ResponseEntity.ok(productService.findProductById(productId));
    }
}
