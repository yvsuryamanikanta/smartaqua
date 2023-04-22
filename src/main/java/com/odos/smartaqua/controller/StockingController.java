package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.StockingDTO;
import com.odos.smartaqua.service.StockingService;

@RequestMapping(value = "/api/tank/stocking")
@RestController
public class StockingController {

	@Autowired
	private StockingService stockingService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> resendOTP(@RequestBody StockingDTO stockingDTO) {
		return stockingService.save(stockingDTO);
	}

	@RequestMapping(value = "/list/{tankid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> list(@PathVariable("tankid") String tankid) {
		return stockingService.getStocking(tankid);
	}

}
