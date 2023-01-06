package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Tank;

public interface TankRepository extends JpaRepository<Tank, Long> {

	@Query(nativeQuery = true, value = "select tankname from tank where tankname =:tankname and userid =:userid")
	String findTankByName(@Param("tankname") String tankname, @Param("userid") String userid);

	@Query(nativeQuery = true, value = "select * from tank where userid =:userid")
	List<Tank> findTankByUser(@Param("userid") String userid);

}
