package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.VersionDTO;
import com.odos.smartaqua.service.VersionService;

@RequestMapping(value = "/api/version")
@RestController
public class VersionController {

	@Autowired
	private VersionService versionservice;

	@RequestMapping(value = "/list/{isactive}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> findByIsActive(@PathVariable("isactive") boolean isactive) {
		return versionservice.findByIsActive(isactive);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> savDevice(@RequestBody VersionDTO versiondto) {
		return versionservice.saveVersion(versiondto);
	}

}
