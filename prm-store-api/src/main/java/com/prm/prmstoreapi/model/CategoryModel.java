package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.BrandEntity;
import com.prm.prmstoreapi.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {
    private Long id;

    private String category_name;

    public CategoryModel(CategoryEntity entity) {
        this.id = entity.getId();
        this.category_name = entity.getCategory_name();
    }
}
