package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Preparation;

public interface PreparationRepository extends JpaRepository<Preparation, Long> {

	@Query(nativeQuery = true, value = "select * from preparation where tankid =:tankid")
	List<Preparation> pondPreparation(@Param("tankid") String tankid);

}
