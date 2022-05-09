package com.example.detai37.service;

import com.example.detai37.base.request.BasePageAndSortRequest;
import com.example.detai37.entity.Cart;
import com.example.detai37.entity.Product;
import com.example.detai37.entity.ProductBrand;
import com.example.detai37.entity.ProductType;
import com.example.detai37.repository.CartRepository;
import com.example.detai37.request.product.CreateProductRequest;
import com.example.detai37.request.product.UpdateProductRequest;
import com.example.detai37.ultis.MappingUtils;
import com.example.detai37.ultis.PageableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Page<Cart> getAllCart(BasePageAndSortRequest pageAndSortRequest){
        Pageable pageable = PageableUtils.convertPageableAndSort(pageAndSortRequest.getPageNumber(), pageAndSortRequest.getPageSize(), pageAndSortRequest.getSort());
        Page<Cart> cartList = cartRepository.findAll(pageable);
        return cartList;
    }

    public Cart findCartById(String id){

        Optional<Cart> cartOptional = cartRepository.findById(id);
        Cart cart  = new Cart();
        if (cartOptional != null){
            cart = cartOptional.get();
        }
        return cart;
    }

//    public Product saveProduct(CreateProductRequest createProductRequest){
//        Product product = MappingUtils.mapObject(createProductRequest, Product.class);
//        if(product.getStock() > 0){
//            product.setStatus(true);
//        }else {
//            product.setStatus(false);
//        }
//        ProductBrand productBrand = productBrandService.findProductBrandById(createProductRequest.getBrandId());
//        ProductType productType = productTypeService.findProductTypeById(createProductRequest.getTypeId());
//        product.setBrand(productBrand);
//        product.setType(productType);
//        Product result = productRepository.save(product);
//        return result;
////        return customer;
//    }
//
//    public Product updateProduct( UpdateProductRequest updateProductRequest){
//        Product product = MappingUtils.mapObject(updateProductRequest, Product.class);
//        if(product.getStock() > 0){
//            product.setStatus(true);
//        }else {
//            product.setStatus(false);
//        }
//        ProductBrand productBrand = productBrandService.findProductBrandById(updateProductRequest.getBrandId());
//        ProductType productType = productTypeService.findProductTypeById(updateProductRequest.getTypeId());
//        product.setBrand(productBrand);
//        product.setType(productType);
//        Product result = productRepository.save(product);
//        return result;
////        return customer;
//    }
}
