package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.BrandEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandModel {

    private Long id;

    private String brand_name;

    public BrandModel(BrandEntity entity) {
        this.id = entity.getId();
        this.brand_name = entity.getBrand_name();
    }
}
