package com.example.detai37.service;

import com.example.detai37.base.request.BasePageAndSortRequest;
import com.example.detai37.entity.Product;
import com.example.detai37.entity.ProductBrand;
import com.example.detai37.entity.ProductType;
import com.example.detai37.repository.ProductRepository;
import com.example.detai37.request.product.CreateProductRequest;
import com.example.detai37.request.product.UpdateProductRequest;
import com.example.detai37.ultis.MappingUtils;
import com.example.detai37.ultis.PageableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductBrandService productBrandService;
    private ProductTypeService productTypeService;
    public ProductService(ProductRepository productRepository, ProductBrandService productBrandService, ProductTypeService productTypeService) {
        this.productRepository = productRepository;
        this.productBrandService = productBrandService;
        this.productTypeService = productTypeService;
    }

    public Page<Product> getAllProduct(BasePageAndSortRequest pageAndSortRequest){
        Pageable pageable = PageableUtils.convertPageableAndSort(pageAndSortRequest.getPageNumber(), pageAndSortRequest.getPageSize(), pageAndSortRequest.getSort());
        Page<Product> productList = productRepository.findAll(pageable);
        return productList;
    }

    public Product findProductById(String id){

        Optional<Product> productOptional = productRepository.findById(id);
        Product product  = new Product();
        if (productOptional != null){
            product = productOptional.get();
        }
        return product;
    }

    public Product saveProduct(CreateProductRequest createProductRequest){
        Product product = MappingUtils.mapObject(createProductRequest, Product.class);
        if(product.getStock() > 0){
            product.setStatus(true);
        }else {
            product.setStatus(false);
        }
        ProductBrand productBrand = productBrandService.findProductBrandById(createProductRequest.getBrandId());
        ProductType productType = productTypeService.findProductTypeById(createProductRequest.getTypeId());
        product.setBrand(productBrand);
        product.setType(productType);
        Product result = productRepository.save(product);
        return result;
//        return customer;
    }

    public Product updateProduct( UpdateProductRequest updateProductRequest){
        Product product = MappingUtils.mapObject(updateProductRequest, Product.class);
        if(product.getStock() > 0){
            product.setStatus(true);
        }else {
            product.setStatus(false);
        }
        ProductBrand productBrand = productBrandService.findProductBrandById(updateProductRequest.getBrandId());
        ProductType productType = productTypeService.findProductTypeById(updateProductRequest.getTypeId());
        product.setBrand(productBrand);
        product.setType(productType);
        Product result = productRepository.save(product);
        return result;
//        return customer;
    }

}
