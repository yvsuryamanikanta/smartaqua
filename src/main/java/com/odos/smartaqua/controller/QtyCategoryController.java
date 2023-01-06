package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.QuantityCategoriesDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.QtyCategroyService;

@RequestMapping(value = "/api/quantity")
@RestController
public class QtyCategoryController {

	@Autowired
	private QtyCategroyService qtyCategroyService;

	@RequestMapping(value = "/categories/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveCrop(@RequestBody QuantityCategoriesDTO quantityCategoriesdto) {
		return qtyCategroyService.saveCategory(quantityCategoriesdto);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getCropList() {
		return qtyCategroyService.findCategories();
	}
}
