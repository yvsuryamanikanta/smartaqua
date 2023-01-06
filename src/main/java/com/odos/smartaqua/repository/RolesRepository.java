package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.odos.smartaqua.entities.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {

	//@Query(nativeQuery = true, value = "select * from roles where rolecode<> 'L'")
	@Query(nativeQuery = true, value = "select * from roles")
	List<Roles> findAllRoles();

}
