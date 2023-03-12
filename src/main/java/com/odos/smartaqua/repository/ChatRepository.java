package com.odos.smartaqua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

	@Query(nativeQuery = true, value = "select * from chat where userid =:userid")
	List<Chat> findListByUser(@Param("userid") String userid);

}
