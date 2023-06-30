package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.FeedTemplate;

public interface FeedTemplateRepository extends JpaRepository<FeedTemplate, Long> {

	@Query(nativeQuery = true, value = "select * from feedtemplate where feedgroupid =:groupid")
	List<FeedTemplate> findfeedgroupById(@Param("groupid") Long groupid);
	
	@Query(nativeQuery = true, value = "select * from feedtemplate where feedgroupid =:groupid and productcatgeoryid =:productcatgeoryid")
	List<FeedTemplate> findListByProductCatgory(@Param("groupid") Long groupid,@Param("productcatgeoryid") Long productcatgeoryid);


}
