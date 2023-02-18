package com.prm.prmstoreapi.repository;

import com.prm.prmstoreapi.entity.BrandEntity;
import com.prm.prmstoreapi.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
