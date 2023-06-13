package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.PCRObservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface PcrObservationService {

	ResponseEntity<ResponseDTO> savePcrObservation(PCRObservationDTO pcrObservationDTO);

	ResponseEntity<ResponseDTO> findPCRreportsByTankId(Long id);

}
