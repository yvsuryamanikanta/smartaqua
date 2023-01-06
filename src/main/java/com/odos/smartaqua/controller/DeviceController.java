package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.DeviceDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.DeviceService;

@RequestMapping(value = "/api/device")
@RestController
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	private ResponseEntity<ResponseDTO> savDevice(@RequestBody DeviceDTO devicedto) {
		return deviceService.save(devicedto);
	}
}
