package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.PCRObservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.PcrObservationService;

@RequestMapping(value = "/api/pcr/observation")
@RestController
public class PCRObservationController {

	@Autowired
	private PcrObservationService pcrObservationService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveChecktrayObservation(@RequestBody PCRObservationDTO pcrObservationDTO) {
		return pcrObservationService.savePcrObservation(pcrObservationDTO);
	}

	@RequestMapping(value = "/list/{tankid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getPCRByTank(@PathVariable("tankid") Long tankid) {
		return pcrObservationService.findPCRreportsByTankId(tankid);
	}
}
