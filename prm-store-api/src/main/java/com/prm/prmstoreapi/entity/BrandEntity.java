package com.prm.prmstoreapi.entity;

import com.prm.prmstoreapi.model.BrandModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brands")
public class BrandEntity {
    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name", columnDefinition = "nvarchar(255) not null")
    private String brand_name;

    public BrandEntity(BrandModel model) {
        this.brand_name = model.getBrand_name();
    }
}
