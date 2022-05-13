package com.example.detai37.controller;

import com.example.detai37.base.request.BasePageAndSortRequest;
import com.example.detai37.entity.Product;
import com.example.detai37.request.product.CreateProductRequest;
import com.example.detai37.request.product.UpdateProductRequest;
import com.example.detai37.response.ProductListResponse;
import com.example.detai37.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public ResponseEntity<ProductListResponse> getAllProduct(@RequestBody BasePageAndSortRequest pageAndSortRequest){
        Page<Product> productPage = productService.getAllProduct(pageAndSortRequest);
        ProductListResponse response = ProductListResponse.builder()
                .productList(productPage.getContent())
                .number(productPage.getNumber())
                .totalPages(productPage.getTotalPages())
                .build();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/find-by-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findProductById(@PathVariable("id") String productId){
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Product> saveProduct(@RequestBody CreateProductRequest createProductRequest){
        return ResponseEntity.ok(productService.saveProduct(createProductRequest));
    }

    @RequestMapping(value = "/update-product", method = RequestMethod.POST)
    public ResponseEntity<Product> updateProduct(@RequestBody UpdateProductRequest UpdateProductRequest){
        return ResponseEntity.ok(productService.updateProduct(UpdateProductRequest));
    }
}
