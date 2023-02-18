package com.prm.prmstoreapi.entity;

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
@Table(name = "products")
public class ProductEntity {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", columnDefinition = "varchar(255) not null")
    private String product_name;

    @Column(name = "image", columnDefinition = "varchar(255)")
    private String image;

    @Column(name = "description", columnDefinition = "varchar(2500)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    CategoryEntity category;

    @Column(name = "model_year", columnDefinition = "smallint not null")
    private int model_year;

    @Column(name = "list_price", columnDefinition = "decimal (10, 2) not null")
    private float list_price;

    @OneToMany(mappedBy = "product")
    private List<StockEntity> stockList;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(updatable = false)
    @UpdateTimestamp
    private LocalDateTime updated_at;
}
