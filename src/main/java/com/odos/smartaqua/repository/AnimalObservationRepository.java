package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.AnimalObservation;

public interface AnimalObservationRepository extends JpaRepository<AnimalObservation, Long> {

	@Query(nativeQuery = true, value = "select * from animal_observation where tankid =:tankid")
	List<AnimalObservation> findAnimalObservationList(@Param("tankid") Long tankid);

}
