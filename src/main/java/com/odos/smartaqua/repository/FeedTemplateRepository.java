package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.FeedTemplate;

public interface FeedTemplateRepository extends JpaRepository<FeedTemplate, Long> {

	@Query(nativeQuery = true, value = "select * from feedtemplate where userid =:userid and feedgroupid =:groupid")
	List<FeedTemplate> findTemplatesByUser(@Param("userid") Long userid, @Param("groupid") Long groupid);
	
	@Query(nativeQuery = true, value = "select * from feedtemplate where feedgroupid =:groupid and productcatgeoryid =:productcatgeoryid")
	List<FeedTemplate> findTemplatesByProductCatg(@Param("groupid") Long groupid,@Param("productcatgeoryid") Long productcatgeoryid);


}
