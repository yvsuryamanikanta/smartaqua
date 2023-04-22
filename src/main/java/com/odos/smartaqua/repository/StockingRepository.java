package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Stocking;

public interface StockingRepository extends JpaRepository<Stocking, Long> {

	@Query(nativeQuery = true, value = "select * from stocking where tankid =:tankid")
	List<Stocking> getStocking(@Param("tankid") String tankid);

}
