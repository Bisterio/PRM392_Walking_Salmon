package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.CustomerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    private Long id;

    private String first_name;

    private String last_name;

    private String email;

    private String phone;

    private String street;

    private String city;

    private String state;

    private String zip_code;

    private String created_at;

    private String updated_at;

    public CustomerModel(CustomerEntity entity) {
        this.id = entity.getId();
        this.first_name = entity.getFirst_name();
        this.last_name = entity.getLast_name();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.street = entity.getStreet();
        this.city = entity.getCity();
        this.state = entity.getState();
        this.zip_code = entity.getZip_code();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss");
        this.created_at = entity.getCreated_at().format(formatter);
        this.updated_at = entity.getUpdated_at().format(formatter);
    }
}
