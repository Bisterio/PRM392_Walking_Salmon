package com.prm.prmstoreapi.service;

import com.prm.prmstoreapi.model.ProductModel;

import java.util.List;

public interface ProductService {
    List<ProductModel> getAllProducts();
    long countAllProducts();
}
