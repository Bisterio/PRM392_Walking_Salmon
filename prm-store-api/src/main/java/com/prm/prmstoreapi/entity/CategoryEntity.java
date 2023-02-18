package com.prm.prmstoreapi.entity;

import com.prm.prmstoreapi.model.CategoryModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", columnDefinition = "varchar(255) not null")
    private String category_name;

    public CategoryEntity(CategoryModel model) {
        this.category_name = model.getCategory_name();
    }
}
