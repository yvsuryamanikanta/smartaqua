package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.GrowthObservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface GrowthObservationService {

	ResponseEntity<ResponseDTO> saveGrowthObservation(GrowthObservationDTO growthObservationDTO);

	ResponseEntity<ResponseDTO> findGrowthByTankId(Long id);
	
	ResponseEntity<ResponseDTO> findGrowthByDateAndTank(Long id,String growthobservationdate);
	
	

}
