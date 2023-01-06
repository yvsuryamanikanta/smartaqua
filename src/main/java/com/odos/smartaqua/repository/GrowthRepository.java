package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.GrowthObservation;

public interface GrowthRepository extends JpaRepository<GrowthObservation, Long> {
	
	@Query(nativeQuery = true, value = "select * from growthobservation where tankid =:tankid")
	List<GrowthObservation> findGrowthList(@Param("tankid") Long tankid);

}
