package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ChecktrayDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface ChecktrayService {

	ResponseEntity<ResponseDTO> saveChecktray(ChecktrayDTO checktrayDTO);

	ResponseEntity<ResponseDTO> findChecktrayByTankId(Long id);

}
