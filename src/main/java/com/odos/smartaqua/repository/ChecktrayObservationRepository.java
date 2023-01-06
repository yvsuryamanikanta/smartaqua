package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.ChecktrayObservation;

public interface ChecktrayObservationRepository extends JpaRepository<ChecktrayObservation, Long> {
	
	@Query(nativeQuery = true, value = "select * from checktrayobservation where checktrayid =:checktrayid")
	List<ChecktrayObservation> findChecktrayObservationList(@Param("checktrayid") Long checktrayid);
	
	@Query(nativeQuery = true, value = "select * from checktrayobservation where tankid =:tankid")
	List<ChecktrayObservation> findChecktrayObservationListByTank(@Param("tankid") Long tankid);

}
