package com.example.detai37.service;

import com.example.detai37.entity.ProductBrand;
import com.example.detai37.repository.ProductBrandRepository;
import com.example.detai37.request.productBrand.CreateProductBrandRequest;
import com.example.detai37.ultis.MappingUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductBrandService {
    private ProductBrandRepository productBrandRepository;

    public ProductBrandService(ProductBrandRepository productBrandRepository) {
        this.productBrandRepository = productBrandRepository;
    }

    public List<ProductBrand> getAllProductBrand(){
        List<ProductBrand> productBrandList = productBrandRepository.findAll();
        return productBrandList;
    }

    public ProductBrand findProductBrandById(String id){
        Optional<ProductBrand> productBrandOptional = productBrandRepository.findById(id);
        ProductBrand productBrand  = null;
        if (productBrandOptional != null){
            productBrand = productBrandOptional.get();
        }
        return productBrand;
    }

    public ProductBrand saveProductBrand(CreateProductBrandRequest createProductBrandRequest){
        ProductBrand productBrand = MappingUtils.mapObject(createProductBrandRequest, ProductBrand.class);
        ProductBrand result = productBrandRepository.save(productBrand);
        return result;
//        return customer;
    }
}
