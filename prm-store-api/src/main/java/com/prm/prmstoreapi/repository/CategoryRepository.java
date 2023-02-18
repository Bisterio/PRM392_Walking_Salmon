package com.prm.prmstoreapi.repository;

import com.prm.prmstoreapi.entity.BrandEntity;
import com.prm.prmstoreapi.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
