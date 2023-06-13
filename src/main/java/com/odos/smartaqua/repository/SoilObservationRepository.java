package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.SoilObservation;

public interface SoilObservationRepository extends JpaRepository<SoilObservation, Long> {
	
	@Query(nativeQuery = true, value = "select * from soil_observation where tankid =:tankid")
	List<SoilObservation> findSoilObservationList(@Param("tankid") Long tankid);

}
