package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ProductCategoryDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface ProductCategoryService {

	ResponseEntity<ResponseDTO> saveCategory(ProductCategoryDTO productCategorydto);

	ResponseEntity<ResponseDTO> findCategories();

}
