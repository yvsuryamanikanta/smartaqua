package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.LabObservation;

public interface LabObservationRepository extends JpaRepository<LabObservation, Long> {
	
	@Query(nativeQuery = true, value = "select * from lab_observation where tankid =:tankid")
	List<LabObservation> findLabObservationList(@Param("tankid") Long tankid);

}
