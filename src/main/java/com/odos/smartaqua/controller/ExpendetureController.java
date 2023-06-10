package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ExpendetureDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.ExpendetureService;

@RequestMapping(value = "/api/expends/observation")
@RestController
public class ExpendetureController {

	@Autowired
	private ExpendetureService expendetureService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveChecktray(@RequestBody ExpendetureDTO expendetureDTO) {
		return expendetureService.saveExpends(expendetureDTO);
	}

	@RequestMapping(value = "/list/{tankid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getCultureByTank(@PathVariable("tankid") Long tankid) {
		return expendetureService.findExpendsByTankId(tankid);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> getGrowthByGrowthDate(@RequestBody ExpendetureDTO expendetureDTO) {
		return expendetureService.findExpendsByTankIdAndDate(expendetureDTO.getTankid(),
				expendetureDTO.getExpendsdate());
	}
}
