package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.UserDTO;
import com.odos.smartaqua.entities.Device;
import com.odos.smartaqua.entities.OTP;
import com.odos.smartaqua.entities.Roles;
import com.odos.smartaqua.entities.User;
import com.odos.smartaqua.repository.DeviceRepository;
import com.odos.smartaqua.repository.OtpRepository;
import com.odos.smartaqua.repository.RolesRepository;
import com.odos.smartaqua.repository.UserRepository;
import com.odos.smartaqua.service.UserService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.Helper;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private OtpRepository otpRepository;

	/*
	 * ----------------save user--------------------
	 */
	public ResponseEntity<ResponseDTO> saveUser(UserDTO userdto) {
		ResponseDTO responseDTO = new ResponseDTO();

		User userData = userRepository.findUserByNumber(userdto.getUsernumber());
		if (userData != null) {
			if (userData.getIsactive() == true) {
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED,
						AquaConstants.alreadyuser, AquaConstants.EMPTY);
			} else {
				long rndNumber = Helper.createRandomInteger(111, 579026);
				if (Helper.sendOTP(userdto.getUsernumber(), rndNumber, AquaConstants.AK_VALUE,
						AquaConstants.SECRET_VALUE, AquaConstants.STAGE, AquaConstants.SENDER_ID,
						AquaConstants.otpMessage)) {
					OTP otp = new OTP();
					otp.setOtpcode(String.valueOf(rndNumber));
					otp.setUsernumber(userdto.getUsernumber());
					otp.setDaycount(1);
					String otpdata = otpRepository.findOTPByNumber(userdto.getUsernumber());
					if (otpdata != null) {
						otpRepository.updateOTP(userdto.getUsernumber(), String.valueOf(rndNumber));
					} else {
						otpRepository.save(otp);
					}
					UserDTO userDto = new UserDTO();
					BeanUtils.copyProperties(userData, userDto);
					userDto.setRoleid(userData.getRoles().getRoleid());
					userDto.setUniqueid(userData.getDevice().getUniqueID());
					userDto.setNotificationid(userData.getDevice().getNotificationid());
					userDto.setRolecode(userData.getRoles().getRolecode());
					responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, userDto,
							AquaConstants.EMPTY);
				} else {
					responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
							AquaConstants.failed, AquaConstants.failed);
				}
			}

		} else {
			User user = new User();
			BeanUtils.copyProperties(userdto, user);
			if (userdto.getRoleid() != null) {
				Roles role = rolesRepository.findById(userdto.getRoleid()).get();
				user.setRoles(role);
				Device device = deviceRepository.findDeviceByuniqueID(userdto.getUniqueid());
				if (device != null) {
					user.setDevice(device);
				}
				try {
					User saveduser = userRepository.save(user);
					long rndNumber = Helper.createRandomInteger(111, 579026);
					if (Helper.sendOTP(userdto.getUsernumber(), rndNumber, AquaConstants.AK_VALUE,
							AquaConstants.SECRET_VALUE, AquaConstants.STAGE, AquaConstants.SENDER_ID,
							AquaConstants.otpMessage)) {
						OTP otp = new OTP();
						otp.setOtpcode(String.valueOf(rndNumber));
						otp.setUsernumber(userdto.getUsernumber());
						otp.setDaycount(1);
						String otpdata = otpRepository.findOTPByNumber(userdto.getUsernumber());
						if (otpdata != null) {
							otpRepository.updateOTP(userdto.getUsernumber(), String.valueOf(rndNumber));
						} else {
							otpRepository.save(otp);
						}
						UserDTO userDto = new UserDTO();
						BeanUtils.copyProperties(saveduser, userDto);
						userDto.setRoleid(saveduser.getRoles().getRoleid());
						userDto.setUniqueid(saveduser.getDevice().getUniqueID());
						userDto.setNotificationid(saveduser.getDevice().getNotificationid());
						userDto.setRolecode(saveduser.getRoles().getRolecode());
						responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, userDto,
								AquaConstants.EMPTY);
					} else {
						responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
								AquaConstants.failed, AquaConstants.failed);
					}
				} catch (Exception e) {
					responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
							AquaConstants.failed, e.toString()+"hello");
				}
			} else {
				responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
						AquaConstants.invalidrole, AquaConstants.invalidrole);
			}
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	public ResponseEntity<ResponseDTO> getFeedBoysList(Long roleid, String createdby) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<User> usersList = userRepository.findFeedBoys(roleid, createdby);
			List<UserDTO> userDtoList = new ArrayList<UserDTO>();
			for (User saveduser : usersList) {

				UserDTO userDto = new UserDTO();
				BeanUtils.copyProperties(saveduser, userDto);
				userDto.setRoleid(saveduser.getRoles().getRoleid());
				userDto.setUniqueid(saveduser.getDevice().getUniqueID());
				userDto.setNotificationid(saveduser.getDevice().getNotificationid());
				userDto.setRolecode(saveduser.getRoles().getRolecode());
				userDtoList.add(userDto);
			}

			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, userDtoList,
					AquaConstants.EMPTY);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
					AquaConstants.failed, AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	public ResponseEntity<ResponseDTO> authenticateUser(UserDTO userdto) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			User userDetails = userRepository.checkUser(userdto.getUsernumber(), userdto.getPassword());
			if (userDetails != null) {
				UserDTO returnDto = new UserDTO();
				BeanUtils.copyProperties(userDetails, returnDto);
				returnDto.setRoleid(userDetails.getRoles().getRoleid());
				returnDto.setUniqueid(userDetails.getDevice().getUniqueID());
				returnDto.setNotificationid(userDetails.getDevice().getNotificationid());
				returnDto.setRolecode(userDetails.getRoles().getRolecode());
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, returnDto,
						AquaConstants.EMPTY);
			} else {
				responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
						AquaConstants.invalidcredentials, AquaConstants.invalidcredentials);
			}

		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
					AquaConstants.failed, AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	@Override
	public ResponseEntity<ResponseDTO> updatePassword(UserDTO userdto) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			User userDetails = userRepository.findUserByNumber(userdto.getUsernumber());
			if (userDetails != null) {
				userRepository.updatePassword(userdto.getUsernumber(), userdto.getPassword());
				UserDTO returnDto = new UserDTO();
				BeanUtils.copyProperties(userDetails, returnDto);
				returnDto.setRoleid(userDetails.getRoles().getRoleid());
				returnDto.setUniqueid(userDetails.getDevice().getUniqueID());
				returnDto.setNotificationid(userDetails.getDevice().getNotificationid());
				returnDto.setRolecode(userDetails.getRoles().getRolecode());
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, returnDto,
						AquaConstants.EMPTY);
			} else {
				responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
						AquaConstants.invalidcredentials, AquaConstants.invalidcredentials);
			}

		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
					AquaConstants.failed, AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
