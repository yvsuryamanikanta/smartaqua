package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.FeedGroup;

public interface FeedGroupRepository extends JpaRepository<FeedGroup, Long> {

	@Query(nativeQuery = true, value = "select groupname from feedgroup where groupname =:groupname")
	String findGroupByName(@Param("groupname") String groupname);
	
	@Query(nativeQuery = true, value = "select * from feedgroup where userid =:userid")
	List<FeedGroup> findGroupsByUID(@Param("userid") Long userid);
	
	@Query(nativeQuery = true, value = "select * from feedgroup where userid =:userid and cultureid =:cultureid")
	List<FeedGroup> findGroupByCultureId(@Param("userid") Long userid, @Param("cultureid") Long cultureid);
	
	@Query(nativeQuery = true, value = "select * from feedgroup where userid =:userid and cultureid =:cultureid and access =:access")
	List<FeedGroup> findGroups(@Param("userid") Long userid, @Param("cultureid") Long cultureid,
			@Param("access") String access);
	
	@Query(nativeQuery = true, value = "select * from feedgroup where userid =:userid and cultureid =:cultureid and feeddate =:feeddate")
	List<FeedGroup> findGroupsByCultureFeeddate(@Param("userid") Long userid, @Param("cultureid") Long cultureid,
			@Param("feeddate") String feeddate);
	
}
