package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Treatments;

public interface TreatmentRepository extends JpaRepository<Treatments, Long> {

	@Query(nativeQuery = true, value = "select * from treatments where tankid =:tankid")
	List<Treatments> findTreatmentsListByTankId(@Param("tankid") Long tankid);

}
