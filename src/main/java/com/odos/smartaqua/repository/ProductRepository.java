package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value = "select * from product")
	List<Product> getProductist();

	@Query(nativeQuery = true, value = "select * from product where productcatgeoryid =:productcatgeoryid")
	List<Product> getProductsByCategory(@Param("productcatgeoryid") Long productcatgeoryid);

	@Query(nativeQuery = true, value = "select * from product where brandid =:brandid")
	List<Product> getProductsByBrand(@Param("brandid") Long brandid);

	@Query(nativeQuery = true, value = "select * from product where brandid =:brandid and productcatgeoryid =:productcatgeoryid")
	List<Product> getCategoryBrands(@Param("brandid") Long brandid,
			@Param("productcatgeoryid") Long productcatgeoryid);

}
