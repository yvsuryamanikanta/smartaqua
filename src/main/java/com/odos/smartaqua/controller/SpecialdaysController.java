package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.SpecialDaysDTO;
import com.odos.smartaqua.service.SpecialDaysService;

@RequestMapping(value = "/api/special/days")
@RestController
public class SpecialdaysController {

	@Autowired
	private SpecialDaysService specialDaysService;

	@GetMapping(value = "/list")
	public ResponseEntity<ResponseDTO> findDays() {
		return specialDaysService.findDays();
	}

	@PostMapping(value = "/save")
	public ResponseEntity<ResponseDTO> saveRole(@RequestBody SpecialDaysDTO specialDaysDTO) {
		return specialDaysService.saveDay(specialDaysDTO);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteDay(@PathVariable("id") Long id) {
		return specialDaysService.deleteDay(id);
	}

}
