package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.SoilObservationDTO;
import com.odos.smartaqua.service.SoilObservationService;

@RequestMapping(value = "/api/soil/observation")
@RestController
public class SoilObservationController {

	@Autowired
	private SoilObservationService soilObservationService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveSoilObservation(@RequestBody SoilObservationDTO soilObservationDTO) {
		return soilObservationService.saveSoilObservation(soilObservationDTO);
	}

	@RequestMapping(value = "/list/{tankid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getSoilReportByTank(@PathVariable("tankid") Long tankid) {
		return soilObservationService.findSoilreportsByTankId(tankid);
	}
}
