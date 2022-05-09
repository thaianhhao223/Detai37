package com.example.detai37.controller;

import com.example.detai37.entity.Product;
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
    public List<Product> getAllProduct(){
        List<Product> result = productService.getAllProduct();
        return result;
    }

    @RequestMapping(value = "/find-by-id", method = RequestMethod.GET)
    public ResponseEntity<Product>  getAllProduct(@PathVariable String productId){
        Product result = productService.findProductById(productId);
        return ResponseEntity.ok(result);
    }
}
