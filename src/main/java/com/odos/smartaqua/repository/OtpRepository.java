package com.odos.smartaqua.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.odos.smartaqua.entities.OTP;


public interface OtpRepository extends JpaRepository<OTP, Long> {

	@Query(nativeQuery = true, value = "select otpcode from otp where usernumber =:usernumber")
	String findOTPByNumber(@Param("usernumber") String usernumber);

	@Modifying()
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE otp SET otpcode =:otpcode where usernumber =:usernumber")
	void updateOTP(@Param("usernumber") String usernumber, @Param("otpcode") String otpcode);

}
