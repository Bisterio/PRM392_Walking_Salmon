package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.BrandEntity;
import com.prm.prmstoreapi.entity.CategoryEntity;
import com.prm.prmstoreapi.entity.ProductEntity;
import com.prm.prmstoreapi.entity.StockEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    private Long id;

    private String product_name;

    private String image;

    private String description;

    BrandEntity brand;

    CategoryEntity category;

    private int model_year;

    private float list_price;

    private List<StockModel> stockList;

    private String created_at;

    private String updated_at;

    public ProductModel(ProductEntity entity) {
        this.id = entity.getId();
        this.product_name = entity.getProduct_name();
        this.image = entity.getImage();
        this.description = entity.getDescription();
        this.brand = entity.getBrand();
        this.category = entity.getCategory();
        this.model_year = entity.getModel_year();
        this.list_price = entity.getList_price();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss");
        this.created_at = entity.getCreated_at().format(formatter);
        this.updated_at = entity.getUpdated_at().format(formatter);
    }
}
