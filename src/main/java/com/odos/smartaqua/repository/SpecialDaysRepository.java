package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.odos.smartaqua.entities.SpecialDays;

public interface SpecialDaysRepository extends JpaRepository<SpecialDays, Long> {

	@Query(nativeQuery = true, value = "select * from special_days")
	List<SpecialDays> findAllSpecialDAys();

}
