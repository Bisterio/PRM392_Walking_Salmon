package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel implements Serializable {
    private Long id;

    private String category_name;

    public CategoryModel(CategoryEntity entity) {
        this.id = entity.getId();
        this.category_name = entity.getCategory_name();
    }
}
