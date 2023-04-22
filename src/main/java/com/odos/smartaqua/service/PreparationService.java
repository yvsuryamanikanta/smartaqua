package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.PreparationDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface PreparationService {

	ResponseEntity<ResponseDTO> save(PreparationDTO preparationDTO);

	ResponseEntity<ResponseDTO> pondPreparation(String tankid);

}
