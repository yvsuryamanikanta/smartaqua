package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ProductCategoryDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.ProductCategoryService;

@RequestMapping(value = "/api/product")
@RestController
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/categories/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveCrop(@RequestBody ProductCategoryDTO productCategorydto) {
		return productCategoryService.saveCategory(productCategorydto);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getCropList() {
		return productCategoryService.findCategories();
	}
}
