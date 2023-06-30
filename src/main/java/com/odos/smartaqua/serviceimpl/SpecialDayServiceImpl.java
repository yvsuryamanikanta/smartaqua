package com.odos.smartaqua.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.SpecialDaysDTO;
import com.odos.smartaqua.entities.SpecialDays;
import com.odos.smartaqua.repository.SpecialDaysRepository;
import com.odos.smartaqua.service.SpecialDaysService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class SpecialDayServiceImpl implements SpecialDaysService {

	@Autowired
	private SpecialDaysRepository specialDaysRepository;

	/*
	 * ----------------Special days list--------------------
	 */
	public ResponseEntity<ResponseDTO> findDays() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED,
					specialDaysRepository.findAllSpecialDAys(), AquaConstants.EMPTY);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	public ResponseEntity<ResponseDTO> saveDay(SpecialDaysDTO specialDaysDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		SpecialDays specialDays = new SpecialDays();
		BeanUtils.copyProperties(specialDaysDTO, specialDays);
		try {
			specialDaysRepository.save(specialDays);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.EMPTY);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	public ResponseEntity<ResponseDTO> deleteDay(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			specialDaysRepository.deleteById(id);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.deleted,
					AquaConstants.EMPTY);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}
}
