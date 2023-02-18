package com.prm.prmstoreapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stores")
public class StoreEntity {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_name", columnDefinition = "varchar(255) not null")
    private String name;

    @Column(name = "phone", columnDefinition = "varchar(25)")
    private String phone;

    @Column(name = "email", columnDefinition = "varchar(255)")
    private String email;

    @Column(name = "street", columnDefinition = "varchar(255)")
    private String street;

    @Column(name = "city", columnDefinition = "varchar(255)")
    private String city;

    @Column(name = "state", columnDefinition = "varchar(10)")
    private String state;

    @Column(name = "zip_code", columnDefinition = "varchar(5)")
    private String zip_code;

    @OneToMany(mappedBy = "store")
    private List<StockEntity> stockList;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(updatable = false)
    @UpdateTimestamp
    private LocalDateTime updated_at;
}
