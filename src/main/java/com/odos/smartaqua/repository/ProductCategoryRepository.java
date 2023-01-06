package com.odos.smartaqua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.odos.smartaqua.entities.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
