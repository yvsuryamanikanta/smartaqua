package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.QuantityCategoriesDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface QtyCategroyService {

	ResponseEntity<ResponseDTO> saveCategory(QuantityCategoriesDTO quantityCategoriesdto);

	ResponseEntity<ResponseDTO> findCategories();

}
