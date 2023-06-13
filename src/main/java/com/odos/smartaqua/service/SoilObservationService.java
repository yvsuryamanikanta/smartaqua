package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.SoilObservationDTO;

public interface SoilObservationService {

	ResponseEntity<ResponseDTO> saveSoilObservation(SoilObservationDTO soilObservationDTO);

	ResponseEntity<ResponseDTO> findSoilreportsByTankId(Long id);

}
