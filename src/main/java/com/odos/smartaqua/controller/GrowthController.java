package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.GrowthObservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.GrowthObservationService;

@RequestMapping(value = "/api/growth/observation")
@RestController
public class GrowthController {

	@Autowired
	private GrowthObservationService growthObservationService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveChecktray(@RequestBody GrowthObservationDTO growthObservationDTO) {
		return growthObservationService.saveGrowthObservation(growthObservationDTO);
	}

	@RequestMapping(value = "/list/{tankid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getCultureByTank(@PathVariable("tankid") Long tankid) {
		return growthObservationService.findGrowthByTankId(tankid);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> getGrowthByGrowthDate(@RequestBody GrowthObservationDTO growthObservationDTO) {
		return growthObservationService.findGrowthByDateAndTank(growthObservationDTO.getTankid(),growthObservationDTO.getGrowthobservationdate());
	}
}
