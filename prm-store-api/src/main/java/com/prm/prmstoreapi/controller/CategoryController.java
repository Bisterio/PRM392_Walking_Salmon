package com.prm.prmstoreapi.controller;

import com.prm.prmstoreapi.common.UrlConstant;
import com.prm.prmstoreapi.model.CategoryModel;
import com.prm.prmstoreapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(UrlConstant.CATEGORIES)
public class CategoryController {
    private final CategoryService categoryService;

    // Get all Categories
    @GetMapping()
    public List<CategoryModel> getAllCategories(){
        log.info("CategoryController: Start getAllCategories()");
        List<CategoryModel> categoryModelList = categoryService.getAllCategories();
        log.info("CategoryController: End getAllCategories()");
        return  categoryModelList;
    }

    // Get all Categories' count
    @GetMapping(UrlConstant.COUNT)
    public long countAllCategories(){
        log.info("CategoryController: Start countAllCategories()");
        long count = categoryService.countAllCategories();
        log.info("CategoryController: End countAllCategories()");
        return count;
    }
}
