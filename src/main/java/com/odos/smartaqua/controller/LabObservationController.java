package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.LabObservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.LabObservationService;

@RequestMapping(value = "/api/lab/observation")
@RestController
public class LabObservationController {

	@Autowired
	private LabObservationService labObservationService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveChecktrayObservation(@RequestBody LabObservationDTO labObservationDTO) {
		return labObservationService.saveLabObservation(labObservationDTO);
	}

	@RequestMapping(value = "/list/{tankid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getCultureByTank(@PathVariable("tankid") Long tankid) {
		return labObservationService.findLabreportsByTankId(tankid);
	}
}
