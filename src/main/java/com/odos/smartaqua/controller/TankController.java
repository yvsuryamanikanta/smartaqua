package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.TankDTO;
import com.odos.smartaqua.service.TankService;


@RequestMapping(value = "/api/tank")
@RestController
public class TankController {

	@Autowired
	private TankService tankService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> resendOTP(@RequestBody TankDTO tankdto) {
		return tankService.save(tankdto);
	}

	@RequestMapping(value = "/list/{userid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> verifyOTP(@PathVariable("userid") String userid) {
		return tankService.findTankByUser(userid);
	}

}
