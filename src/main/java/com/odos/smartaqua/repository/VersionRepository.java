package com.odos.smartaqua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Version;

public interface VersionRepository extends JpaRepository<Version, Long> {

	@Query(nativeQuery = true, value = "select * from version v where v.isactive =:isactive ORDER BY versionid DESC LIMIT 1")
	Version findByIsActive(@Param("isactive") boolean isactive);

}
