package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemModel implements Serializable {
    private Long id;

    OrderModel order;

    ProductModel product;

    private int quantity;

    private float list_price;

    private float discount;

    public OrderItemModel(OrderItemEntity entity) {
        this.id = entity.getId();
        this.quantity = entity.getQuantity();
        this.list_price = entity.getList_price();
        this.discount = entity.getDiscount();
    }
}
