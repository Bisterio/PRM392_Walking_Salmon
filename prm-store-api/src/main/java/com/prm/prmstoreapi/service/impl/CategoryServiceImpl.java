package com.prm.prmstoreapi.service.impl;

import com.prm.prmstoreapi.model.CategoryModel;
import com.prm.prmstoreapi.repository.CategoryRepository;
import com.prm.prmstoreapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    // Get all Categories
    public List<CategoryModel> getAllCategories(){
        log.info("CategoryServiceImpl: Start getAllCategories()");
        List<CategoryModel> categoryModelList = categoryRepository.findAll().stream().map(CategoryModel::new).toList();
        log.info("CategoryServiceImpl: End getAllCategories()");
        return categoryModelList;
    }

    //Get all Categories' count
    public long countAllCategories(){
        log.info("CategoryServiceImpl: Start countAllCategories()");
        long count = categoryRepository.count();
        log.info("CategoryServiceImpl: End countAllCategories()");
        return count;
    }
}
