package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.OtpService;

@RequestMapping(value = "/api/otp")
@RestController
public class OTPController {

	@Autowired
	private OtpService otpService;

	@RequestMapping(value = "/resend/{usernumber}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> resendOTP(@PathVariable("usernumber") String usernumber) {
		return otpService.resendOTP(usernumber);
	}

	@RequestMapping(value = "/verify/{otpcode}/{usernumber}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> verifyOTP(@PathVariable("otpcode") String otpcode,
			@PathVariable("usernumber") String usernumber) {
		return otpService.verifyOTP(otpcode, usernumber);
	}

}
