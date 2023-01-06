package com.odos.smartaqua.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Culture;

public interface CultureRepository extends JpaRepository<Culture, Long> {

	@Query(nativeQuery = true, value = "select * from culture where userid =:userid and tankid =:tankid and culturestatus='start'")
	List<Culture> findCultureByTank(@Param("userid") Long userid, @Param("tankid") Long tankid);
	
	@Query(nativeQuery = true, value = "select culturenumber from culture where userid =:userid and tankid =:tankid order by cultureid DESC limit 1")
	String findCultureNumber(@Param("userid") Long userid, @Param("tankid") Long tankid);

	@Query(nativeQuery = true, value = "select * from culture where userid =:userid")
	List<Culture> findAllCultures(@Param("userid") Long userid);

	@Query(nativeQuery = true, value = "select * from culture where cultureaccess =:cultureaccess")
	List<Culture> findCultureByAccess(@Param("cultureaccess") String cultureaccess);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE culture c set c.cultureaccess =:cultureaccess where c.userid = :userid "
			+ "and c.cultureid =:cultureid and c.culturestatus='start'")
	int updateByCultureID(@Param("userid") Long userid, @Param("cultureid") Long cultureid,
			@Param("cultureaccess") String cultureaccess);
}
