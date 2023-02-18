package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.StockEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockModel {
    private Long id;

    StoreModel store;

    ProductModel product;

    private int quantity;

    public StockModel(StockEntity entity) {
        this.id = entity.getId();
        this.quantity = entity.getQuantity();
    }
}
