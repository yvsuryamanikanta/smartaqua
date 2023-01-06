package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.LabObservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface LabObservationService {

	ResponseEntity<ResponseDTO> saveLabObservation(LabObservationDTO labObservationDTO);

	ResponseEntity<ResponseDTO> findLabreportsByTankId(Long id);

}
