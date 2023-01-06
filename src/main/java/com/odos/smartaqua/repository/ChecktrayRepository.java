package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Checktray;

public interface ChecktrayRepository extends JpaRepository<Checktray, Long> {
	
	@Query(nativeQuery = true, value = "select * from checktray where tankid =:tankid")
	List<Checktray> findChecktrayList(@Param("tankid") Long tankid);

}
