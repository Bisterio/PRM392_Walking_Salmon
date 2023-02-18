package com.prm.prmstoreapi.entity;

import com.prm.prmstoreapi.model.StockModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stocks", uniqueConstraints= @UniqueConstraint(columnNames={"store_id", "product_id"}))
public class StockEntity {
    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    StoreEntity store;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

    @Column(name = "quantity", columnDefinition = "int")
    private int quantity;

    public StockEntity(StockModel model) {
        this.quantity = model.getQuantity();
    }
}
