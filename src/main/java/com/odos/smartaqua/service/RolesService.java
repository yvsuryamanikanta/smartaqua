package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.RolesDTO;

public interface RolesService {

	ResponseEntity<ResponseDTO> findRoles();

	ResponseEntity<ResponseDTO> saveRole(RolesDTO rolesdto);

	ResponseEntity<ResponseDTO> deleteRole(Long id);

}
