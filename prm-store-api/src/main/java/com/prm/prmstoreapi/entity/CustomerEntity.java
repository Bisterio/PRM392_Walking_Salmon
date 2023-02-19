package com.prm.prmstoreapi.entity;

import com.prm.prmstoreapi.model.CustomerModel;
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

    @Column(name = "first_name", columnDefinition = "nvarchar(255) not null")
    private String first_name;

    @Column(name = "last_name", columnDefinition = "nvarchar(255) not null")
    private String last_name;

    @Column(name = "email", columnDefinition = "nvarchar(255) not null")
    private String email;

    @Column(name = "phone", columnDefinition = "nvarchar(25)")
    private String phone;

    @Column(name = "street", columnDefinition = "nvarchar(255)")
    private String street;

    @Column(name = "city", columnDefinition = "nvarchar(50)")
    private String city;

    @Column(name = "state", columnDefinition = "nvarchar(25)")
    private String state;

    @Column(name = "zip_code", columnDefinition = "nvarchar(5)")
    private String zip_code;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated_at;

    public CustomerEntity(CustomerModel model) {
        this.first_name = model.getFirst_name();
        this.last_name = model.getLast_name();
        this.email = model.getEmail();
        this.phone = model.getPhone();
        this.street = model.getStreet();
        this.city = model.getCity();
        this.state = model.getState();
        this.zip_code = model.getZip_code();
    }
}
