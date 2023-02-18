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
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", columnDefinition = "varchar(255) not null")
    private String first_name;

    @Column(name = "last_name", columnDefinition = "varchar(255) not null")
    private String last_name;

    @Column(name = "email", columnDefinition = "varchar(255) not null")
    private String email;

    @Column(name = "phone", columnDefinition = "varchar(25)")
    private String phone;

    @Column(name = "street", columnDefinition = "varchar(255)")
    private String street;

    @Column(name = "city", columnDefinition = "varchar(50)")
    private String city;

    @Column(name = "state", columnDefinition = "varchar(25)")
    private String state;

    @Column(name = "zip_code", columnDefinition = "varchar(5)")
    private String zip_code;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated_at;
}
