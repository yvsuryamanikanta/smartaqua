package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.AnimalbservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface AnimalObservationService {

	ResponseEntity<ResponseDTO> saveAnimalObservation(AnimalbservationDTO animalbservationDTO);

	ResponseEntity<ResponseDTO> findAnimalreportsByTankId(Long id);

}
