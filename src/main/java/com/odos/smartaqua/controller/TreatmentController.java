package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.TreatmentsDTO;
import com.odos.smartaqua.service.TreatmentService;

@RequestMapping(value = "/api/treatment/")
@RestController
public class TreatmentController {

	@Autowired
	private TreatmentService treatmentService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveChecktray(@RequestBody TreatmentsDTO treatmentsDTO) {
		return treatmentService.saveTreatment(treatmentsDTO);
	}

	@RequestMapping(value = "/list/{tankid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getCultureByTank(@PathVariable("tankid") Long tankid) {
		return treatmentService.findTreatmentByTankId(tankid);
	}
}
