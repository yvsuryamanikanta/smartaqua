package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ChecktrayObservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface ChecktrayObservationService {

	ResponseEntity<ResponseDTO> saveChecktrayObservation(ChecktrayObservationDTO checktrayObservationDTO);

	ResponseEntity<ResponseDTO> findChecktrayByChecktrayId(Long id);

}
