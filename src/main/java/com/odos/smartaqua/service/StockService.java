package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.StockDTO;

public interface StockService {

	ResponseEntity<ResponseDTO> save(StockDTO stockdto);

	ResponseEntity<ResponseDTO> findStock();
	
	ResponseEntity<ResponseDTO> findStockbyUser(Long userid);
	
	ResponseEntity<ResponseDTO> findStockbyProduct(Long userid,Long productid);


}
