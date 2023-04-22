package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.PreparationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.PreparationService;


@RequestMapping(value = "/api/tank/preparation")
@RestController
public class PreparationController {

	@Autowired
	private PreparationService preparationService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> resendOTP(@RequestBody PreparationDTO preparationDTO) {
		return preparationService.save(preparationDTO);
	}

	@RequestMapping(value = "/list/{tankid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> list(@PathVariable("tankid") String tankid) {
		return preparationService.pondPreparation(tankid);
	}

}
