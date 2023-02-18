package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel implements Serializable {
    private Long id;

    CustomerModel customer;

    private short order_status;

    private String order_date;

    private String required_date;

    private String shipped_date;

    private String created_at;

    private String updated_at;

    StoreModel store;

    StaffModel staff;

    public OrderModel(OrderEntity entity) {
        this.id = entity.getId();
        this.order_status = entity.getOrder_status();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss");
        this.order_date = entity.getOrder_date().format(formatter);
        this.required_date = entity.getRequired_date().format(formatter);
        this.shipped_date = entity.getShipped_date().format(formatter);
        this.created_at = entity.getCreated_at().format(formatter);
        this.updated_at = entity.getUpdated_at().format(formatter);
    }
}
