package com.odos.smartaqua.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.RolesDTO;
import com.odos.smartaqua.entities.Roles;
import com.odos.smartaqua.repository.RolesRepository;
import com.odos.smartaqua.service.RolesService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	private RolesRepository rolesRepository;

	/*
	 * ----------------start roles list--------------------
	 */
	public ResponseEntity<ResponseDTO> findRoles() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED,
					rolesRepository.findAllRoles(), AquaConstants.EMPTY);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
					AquaConstants.failed, AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	public ResponseEntity<ResponseDTO> saveRole(RolesDTO rolesdto) {
		ResponseDTO responseDTO = new ResponseDTO();
		Roles roles = new Roles();
		BeanUtils.copyProperties(rolesdto, roles);
		try {
			rolesRepository.save(roles);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.EMPTY);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
					AquaConstants.failed, AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	public ResponseEntity<ResponseDTO> deleteRole(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			rolesRepository.deleteById(id);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.deleted,
					AquaConstants.EMPTY);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
					AquaConstants.failed, AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}
}
