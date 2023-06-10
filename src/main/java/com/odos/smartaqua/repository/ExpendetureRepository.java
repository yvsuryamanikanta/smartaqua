package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Expendeture;

public interface ExpendetureRepository extends JpaRepository<Expendeture, Long> {
	
	@Query(nativeQuery = true, value = "select * from expendeture where tankid =:tankid")
	List<Expendeture> findExpendsList(@Param("tankid") Long tankid);
	
	@Query(nativeQuery = true, value = "select * from expendeture where tankid =:tankid and expendsdate =:expendsdate")
	List<Expendeture> findExpendetureListByDate(@Param("tankid") Long tankid, @Param("expendsdate") String growthobservationdate);


}
