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

import com.odos.smartaqua.dto.BannerDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.BannerService;

@RequestMapping(value = "/api/banner")
@RestController
public class BannersController {

	@Autowired
	private BannerService bannerService;

	@GetMapping(value = "/list")
	public ResponseEntity<ResponseDTO> findBanners() {
		return bannerService.findBaners();
	}

	@PostMapping(value = "/save")
	public ResponseEntity<ResponseDTO> saveRole(@RequestBody BannerDTO bannerDTO) {
		return bannerService.saveBanner(bannerDTO);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteBanner(@PathVariable("id") Long id) {
		return bannerService.deleteBanner(id);
	}

}
