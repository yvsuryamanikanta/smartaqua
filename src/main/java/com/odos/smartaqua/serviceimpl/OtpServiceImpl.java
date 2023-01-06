package com.odos.smartaqua.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.repository.OtpRepository;
import com.odos.smartaqua.repository.UserRepository;
import com.odos.smartaqua.service.OtpService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.Helper;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class OtpServiceImpl implements OtpService {

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * ----------------RESEND OTP--------------------
	 */

	public ResponseEntity<ResponseDTO> resendOTP(String usernumber) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			String otp = otpRepository.findOTPByNumber(usernumber);
			if (otp != null) {
				long rndNumber = Helper.createRandomInteger(55, 579026);
				if (Helper.sendOTP(usernumber, rndNumber, AquaConstants.AK_VALUE, AquaConstants.SECRET_VALUE,
						AquaConstants.STAGE, AquaConstants.SENDER_ID, AquaConstants.otpMessage)) {
					otpRepository.updateOTP(usernumber, String.valueOf(rndNumber));
					responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, rndNumber,
							AquaConstants.EMPTY);
				} else {
					responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
							AquaConstants.failed, AquaConstants.failed);
				}

			} else {
				responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
						AquaConstants.failed, AquaConstants.failed);
			}

		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
					AquaConstants.failed, AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * ----------------VERIFY OTP--------------------
	 */

	public ResponseEntity<ResponseDTO> verifyOTP(String otpcode, String usernumber) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			String db_otpcode = otpRepository.findOTPByNumber(usernumber);
			if (db_otpcode.equalsIgnoreCase(otpcode)) {
				userRepository.activateuser(usernumber);
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED,
						AquaConstants.success, AquaConstants.EMPTY);
			} else {
				responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
						AquaConstants.failed, AquaConstants.invalidotp);
			}
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
					AquaConstants.failed, AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
