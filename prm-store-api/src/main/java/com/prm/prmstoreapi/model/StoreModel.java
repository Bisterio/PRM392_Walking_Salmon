package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreModel {
    private Long id;

    private String name;

    private String phone;

    private String email;

    private String street;

    private String city;

    private String state;

    private String zip_code;

    private List<StockModel> stockList;

    private String created_at;

    private String updated_at;

    public StoreModel(StoreEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.phone = entity.getPhone();
        this.email = entity.getEmail();
        this.street = entity.getStreet();
        this.city = entity.getCity();
        this.state = entity.getState();
        this.zip_code = entity.getZip_code();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss");
        this.created_at = entity.getCreated_at().format(formatter);
        this.updated_at = entity.getUpdated_at().format(formatter);
    }
}
