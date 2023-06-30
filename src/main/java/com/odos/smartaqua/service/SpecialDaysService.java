package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.SpecialDaysDTO;

public interface SpecialDaysService {

	ResponseEntity<ResponseDTO> findDays();

	ResponseEntity<ResponseDTO> saveDay(SpecialDaysDTO specialDaysDTO);

	ResponseEntity<ResponseDTO> deleteDay(Long id);

}
