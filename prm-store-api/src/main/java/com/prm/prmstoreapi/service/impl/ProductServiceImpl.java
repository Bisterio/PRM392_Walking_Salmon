package com.prm.prmstoreapi.service.impl;

import com.prm.prmstoreapi.model.ProductModel;
import com.prm.prmstoreapi.repository.ProductRepository;
import com.prm.prmstoreapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    // Get all Products
    public List<ProductModel> getAllProducts(){
        log.info("ProductServiceImpl: Start getAllProducts()");
        List<ProductModel> productModelList = productRepository.findAll().stream().map(ProductModel::new).toList();
        log.info("ProductServiceImpl: End getAllProducts()");
        return productModelList;
    }

    //Get all Products count
    public long countAllProducts(){
        log.info("ProductServiceImpl: Start countAllProducts()");
        long count = productRepository.count();
        log.info("ProductServiceImpl: End countAllProducts()");
        return count;
    }
}
