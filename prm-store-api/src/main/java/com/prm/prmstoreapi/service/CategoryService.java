package com.prm.prmstoreapi.service;

import com.prm.prmstoreapi.model.CategoryModel;

import java.util.List;

public interface CategoryService {
    List<CategoryModel> getAllCategories();
    long countAllCategories();
}
