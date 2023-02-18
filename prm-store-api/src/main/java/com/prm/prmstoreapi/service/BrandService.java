package com.prm.prmstoreapi.service;

import com.prm.prmstoreapi.model.BrandModel;

import java.util.List;

public interface BrandService {
    List<BrandModel> getAllBrands();
    long countAllBrands();
}
