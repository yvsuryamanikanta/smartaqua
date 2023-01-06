package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.BrandDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface BrandService {

	ResponseEntity<ResponseDTO> saveBrand(BrandDTO branddto);

	ResponseEntity<ResponseDTO> findBrands();

}
