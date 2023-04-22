package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.StockingDTO;

public interface StockingService {

	ResponseEntity<ResponseDTO> save(StockingDTO stockingDTO);

	ResponseEntity<ResponseDTO> getStocking(String tankid);

}
