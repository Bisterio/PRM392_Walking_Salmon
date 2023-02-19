package com.prm.prmstoreapi.controller;

import com.prm.prmstoreapi.common.UrlConstant;
import com.prm.prmstoreapi.model.ProductModel;
import com.prm.prmstoreapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(UrlConstant.PRODUCTS)
public class ProductController {
    private final ProductService productService;

    // Get all Products
    @GetMapping()
    public List<ProductModel> getAllProducts(){
        log.info("ProductController: Start getAllProducts()");
        List<ProductModel> productModelList = productService.getAllProducts();
        log.info("ProductController: End getAllProducts()");
        return  productModelList;
    }

    // Get all Products count
    @GetMapping(UrlConstant.COUNT)
    public long countAllProducts(){
        log.info("ProductController: Start countAllProducts()");
        long count = productService.countAllProducts();
        log.info("ProductController: End countAllProducts()");
        return count;
    }
}
