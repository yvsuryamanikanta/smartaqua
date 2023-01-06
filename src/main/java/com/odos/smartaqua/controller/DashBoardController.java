package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.DashBoardService;

@RequestMapping(value = "/api/dashboard")
@RestController
public class DashBoardController {

	@Autowired
	private DashBoardService dashBoardService;

	@RequestMapping(value = "/{userid}/{cultureid}/{tankid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> homeDataList(@PathVariable("userid") Long userid,@PathVariable("cultureid") Long cultureid
			,@PathVariable("tankid") Long tankid) {
		return dashBoardService.findAllGroups(userid,cultureid,tankid);
	}
	
}
