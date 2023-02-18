package com.prm.prmstoreapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staffs")
public class StaffEntity {
    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", columnDefinition = "varchar(50) not null")
    private String first_name;

    @Column(name = "last_name", columnDefinition = "varchar(50) not null")
    private String last_name;

    @Column(name = "email", columnDefinition = "varchar(255) not null unique")
    private String email;

    @Column(name = "phone", columnDefinition = "varchar(25)")
    private String phone;

    @Column(name = "active", columnDefinition = "tinyint not null")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "store_id")
    StoreEntity store;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(updatable = false)
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @NotFound(action= NotFoundAction.IGNORE)
    StaffEntity manager;
}
