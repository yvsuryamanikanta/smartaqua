package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ChecktrayObservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.ChecktrayObservationService;


@RequestMapping(value = "/api/checktray/observation")
@RestController
public class ChecktrayObservationController {

	@Autowired
	private ChecktrayObservationService checktrayObservationService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveChecktrayObservation(@RequestBody ChecktrayObservationDTO checktrayObservationDTO) {
		return checktrayObservationService.saveChecktrayObservation(checktrayObservationDTO);
	}
	
	@RequestMapping(value = "/list/{checktrayid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getCultureByTank(@PathVariable("checktrayid") Long checktrayid) {
		return checktrayObservationService.findChecktrayByChecktrayId(checktrayid);
	}
}
