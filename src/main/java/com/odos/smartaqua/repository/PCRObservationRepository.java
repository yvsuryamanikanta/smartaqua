package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.PcrObservation;

public interface PCRObservationRepository extends JpaRepository<PcrObservation, Long> {
	
	@Query(nativeQuery = true, value = "select * from pcr_observation where tankid =:tankid")
	List<PcrObservation> findPCRObservationList(@Param("tankid") Long tankid);

}
