package com.example.detai37.service;

import com.example.detai37.entity.ProductType;
import com.example.detai37.repository.ProductTypeRepository;
import com.example.detai37.request.productType.CreateProductTypeRequest;
import com.example.detai37.ultis.MappingUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {
    private ProductTypeRepository productTypeRepository;

    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public List<ProductType> getAllProductType(){
        List<ProductType> productTypeList = productTypeRepository.findAll();
        return productTypeList;
    }

    public ProductType findProductTypeById(String id){
        Optional<ProductType> productTypeOptional = productTypeRepository.findById(id);
        ProductType productType = null;
        if (productTypeOptional != null){
            productType = productTypeOptional.get();
        }
        return productType;
    }

    public ProductType saveProductType(CreateProductTypeRequest createProductTypeRequest){
        ProductType productType = MappingUtils.mapObject(createProductTypeRequest, ProductType.class);
        System.out.println(productType.toString());
        ProductType result = productTypeRepository.save(productType);
        return result;
//        return customer;
    }
}
