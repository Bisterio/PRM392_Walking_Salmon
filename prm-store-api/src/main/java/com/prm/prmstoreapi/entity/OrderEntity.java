package com.prm.prmstoreapi.entity;

import com.prm.prmstoreapi.model.OrderModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;

    @Column(name = "order_status", columnDefinition = "tinyint not null")
    private short order_status;

    @Column(name = "order_date", columnDefinition = "DATE NOT NULL")
    private LocalDateTime order_date;

    @Column(name = "required_date", columnDefinition = "DATE NOT NULL")
    private LocalDateTime required_date;

    @Column(name = "shipped_date", columnDefinition = "DATE")
    private LocalDateTime shipped_date;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "store_id")
    StoreEntity store;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    StaffEntity staff;

    public OrderEntity(OrderModel model) {
        this.order_status = model.getOrder_status();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss");
        this.order_date = LocalDateTime.parse(model.getOrder_date(), formatter);
        this.required_date = LocalDateTime.parse(model.getRequired_date(), formatter);
        this.shipped_date = LocalDateTime.parse(model.getShipped_date(), formatter);
    }
}
