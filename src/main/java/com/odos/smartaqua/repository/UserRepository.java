package com.odos.smartaqua.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(nativeQuery = true, value = "select * from user where usernumber =:usernumber")
	User findUserByNumber(@Param("usernumber") String usernumber);

	@Query(nativeQuery = true, value = "select * from user where usernumber =:usernumber and userid =:userid")
	User findUserByNumberAndId(@Param("usernumber") String usernumber, @Param("userid") Long userid);

	@Query(nativeQuery = true, value = "select * from user where roleid =:roleid and createdby =:createdby")
	List<User> findFeedBoys(@Param("roleid") Long roleid, @Param("createdby") String createdby);

	@Query(nativeQuery = true, value = "select * from user where userid =:userid")
	List<User> findUser(@Param("userid") Long userid);

	@Modifying()
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE user SET isactive =true where usernumber =:usernumber")
	void activateuser(@Param("usernumber") String usernumber);

	@Query(nativeQuery = true, value = "select * from user where usernumber =:usernumber and password =:password and isactive =true")
	User checkUser(@Param("usernumber") String usernumber, @Param("password") String password);

	@Modifying()
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE user SET password =:password where usernumber =:usernumber")
	void updatePassword(@Param("usernumber") String usernumber, @Param("password") String password);

}
