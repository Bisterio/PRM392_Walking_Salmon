package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.OrderEntity;
import com.prm.prmstoreapi.entity.OrderItemEntity;
import com.prm.prmstoreapi.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemModel {
    private Long id;

    OrderEntity order;

    ProductEntity product;

    private int quantity;

    private float list_price;

    private float discount;

    public OrderItemModel(OrderItemEntity entity) {
        this.id = entity.getId();
        this.order = entity.getOrder();
        this.product = entity.getProduct();
        this.quantity = entity.getQuantity();
        this.list_price = entity.getList_price();
        this.discount = entity.getDiscount();
    }
}
