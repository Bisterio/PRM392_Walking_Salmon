package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.ProductEntity;
import com.prm.prmstoreapi.entity.StockEntity;
import com.prm.prmstoreapi.entity.StoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockModel {
    private Long id;

    StoreEntity store;

    ProductEntity product;

    private int quantity;

    public StockModel(StockEntity entity) {
        this.id = entity.getId();
        this.store = entity.getStore();
        this.product = entity.getProduct();
        this.quantity = entity.getQuantity();
    }
}
