package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ResponseDTO;

public interface OtpService {

	ResponseEntity<ResponseDTO> resendOTP(String usernumber);

	ResponseEntity<ResponseDTO> verifyOTP(String otpcode, String usernumber);

}
