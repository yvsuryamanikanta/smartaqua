package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ChecktrayDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.ChecktrayService;


@RequestMapping(value = "/api/checktray")
@RestController
public class ChecktrayController {

	@Autowired
	private ChecktrayService checktrayService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveChecktray(@RequestBody ChecktrayDTO checktrayDTO) {
		return checktrayService.saveChecktray(checktrayDTO);
	}
	
	@RequestMapping(value = "/list/{tankid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getCultureByTank(@PathVariable("tankid") Long tankid) {
		return checktrayService.findChecktrayByTankId(tankid);
	}
}
