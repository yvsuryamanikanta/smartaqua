package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.BrandDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.BrandService;

@RequestMapping(value = "/api/brand")
@RestController
public class BrandController {

	@Autowired
	private BrandService brandService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveCrop(@RequestBody BrandDTO branddto) {
		return brandService.saveBrand(branddto);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getCropList() {
		return brandService.findBrands();
	}
}
