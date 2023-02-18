package com.prm.prmstoreapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @Column(updatable = false)
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "store_id")
    StoreEntity store;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    StaffEntity staff;
}
