package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.odos.smartaqua.entities.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long> {

	@Query(nativeQuery = true, value = "select * from banner")
	List<Banner> findAllBanners();

}
