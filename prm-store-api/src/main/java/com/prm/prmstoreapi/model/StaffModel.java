package com.prm.prmstoreapi.model;

import com.prm.prmstoreapi.entity.StaffEntity;
import com.prm.prmstoreapi.entity.StoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffModel {
    private Long id;

    private String first_name;

    private String last_name;

    private String email;

    private String phone;

    private boolean active;

    StoreEntity store;

    private String created_at;

    private String updated_at;

    private Long manager_id;

    public StaffModel(StaffEntity entity) {
        this.id = entity.getId();
        this.first_name = entity.getFirst_name();
        this.last_name = entity.getLast_name();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.active = entity.isActive();
        this.store = entity.getStore();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss");
        this.created_at = entity.getCreated_at().format(formatter);
        this.updated_at = entity.getUpdated_at().format(formatter);
        this.manager_id = entity.getManager().getId();
    }
}
