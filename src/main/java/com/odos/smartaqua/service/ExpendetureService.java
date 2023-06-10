package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ExpendetureDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface ExpendetureService {

	ResponseEntity<ResponseDTO> saveExpends(ExpendetureDTO expendetureDTO);

	ResponseEntity<ResponseDTO> findExpendsByTankId(Long id);
	
	ResponseEntity<ResponseDTO> findExpendsByTankIdAndDate(Long id,String expendDate);

}
