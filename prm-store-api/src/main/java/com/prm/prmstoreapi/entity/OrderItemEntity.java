package com.prm.prmstoreapi.entity;

import com.prm.prmstoreapi.model.OrderItemModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items", uniqueConstraints= @UniqueConstraint(columnNames={"item_id", "order_id"}))
public class OrderItemEntity {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = true)
    OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

    @Column(name = "quantity", columnDefinition = "int not null")
    private int quantity;

    @Column(name = "list_price", columnDefinition = "decimal (10, 2) not null")
    private float list_price;

    @Column(name = "discount", columnDefinition = "decimal (4, 2) not null default 0")
    private float discount;

    public OrderItemEntity(OrderItemModel model) {
        this.quantity = model.getQuantity();
        this.list_price = model.getList_price();
        this.discount = model.getDiscount();
    }
}
