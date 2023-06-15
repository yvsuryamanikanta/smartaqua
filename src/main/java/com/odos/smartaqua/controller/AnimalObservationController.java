package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.AnimalbservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.AnimalObservationService;

@RequestMapping(value = "/api/animal/observation")
@RestController
public class AnimalObservationController {

	@Autowired
	private AnimalObservationService animalObservationService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveAnimalObservation(@RequestBody AnimalbservationDTO animalbservationDTO) {
		return animalObservationService.saveAnimalObservation(animalbservationDTO);
	}

	@RequestMapping(value = "/list/{tankid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getAnimalByTank(@PathVariable("tankid") Long tankid) {
		return animalObservationService.findAnimalreportsByTankId(tankid);
	}
}
