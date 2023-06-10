package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.TreatmentsDTO;

public interface TreatmentService {

	ResponseEntity<ResponseDTO> saveTreatment(TreatmentsDTO treatmentsDTO);

	ResponseEntity<ResponseDTO> findTreatmentByTankId(Long id);

}
