package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.CustomerEntity;
import com.prm.prmstoreapi.entity.OrderEntity;
import com.prm.prmstoreapi.entity.StaffEntity;
import com.prm.prmstoreapi.entity.StoreEntity;
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
public class OrderModel {
    private Long id;

    CustomerEntity customer;

    private short order_status;

    private String order_date;

    private String required_date;

    private String shipped_date;

    private String created_at;

    private String updated_at;

    StoreEntity store;

    StaffEntity staff;

    public OrderModel(OrderEntity entity) {
        this.id = entity.getId();
        this.customer = entity.getCustomer();
        this.order_status = entity.getOrder_status();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss");
        this.order_date = entity.getOrder_date().format(formatter);
        this.required_date = entity.getRequired_date().format(formatter);
        this.shipped_date = entity.getShipped_date().format(formatter);
        this.created_at = entity.getCreated_at().format(formatter);
        this.updated_at = entity.getUpdated_at().format(formatter);
        this.store = entity.getStore();
        this.staff = entity.getStaff();
    }
}
