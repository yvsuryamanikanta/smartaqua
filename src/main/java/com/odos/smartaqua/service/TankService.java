package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.TankDTO;

public interface TankService {

	ResponseEntity<ResponseDTO> save(TankDTO tankdto);

	ResponseEntity<ResponseDTO> findTankByUser(String userid);

	ResponseEntity<ResponseDTO> findTankInfo(String tankId);
	
	ResponseEntity<ResponseDTO> update(TankDTO tankdto);

}
