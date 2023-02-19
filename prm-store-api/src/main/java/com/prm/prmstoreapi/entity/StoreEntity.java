package com.prm.prmstoreapi.entity;

import com.prm.prmstoreapi.model.StoreModel;
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

    @Column(name = "store_name", columnDefinition = "nvarchar(255) not null")
    private String name;

    @Column(name = "phone", columnDefinition = "nvarchar(25)")
    private String phone;

    @Column(name = "email", columnDefinition = "nvarchar(255)")
    private String email;

    @Column(name = "street", columnDefinition = "nvarchar(255)")
    private String street;

    @Column(name = "city", columnDefinition = "nvarchar(255)")
    private String city;

    @Column(name = "state", columnDefinition = "nvarchar(10)")
    private String state;

    @Column(name = "zip_code", columnDefinition = "nvarchar(5)")
    private String zip_code;

    @OneToMany(mappedBy = "store")
    private List<StockEntity> stockList;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated_at;

    public StoreEntity(StoreModel model) {
        this.name = model.getName();
        this.phone = model.getPhone();
        this.email = model.getEmail();
        this.street = model.getStreet();
        this.city = model.getCity();
        this.state = model.getState();
        this.zip_code = model.getZip_code();
    }
}
