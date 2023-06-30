package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.BannerDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface BannerService {

	ResponseEntity<ResponseDTO> findBaners();

	ResponseEntity<ResponseDTO> saveBanner(BannerDTO bannerDTO);

	ResponseEntity<ResponseDTO> deleteBanner(Long id);

}
