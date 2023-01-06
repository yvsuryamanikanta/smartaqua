package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ProductDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.service.ProductService;

@RequestMapping(value = "/api/product")
@RestController
public class ProductsController {

	@Autowired
	private ProductService productsService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveProduct(@RequestBody ProductDTO productsDTO) {
		return productsService.save(productsDTO);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getProductList() {
		return productsService.findAllProducts();
	}

	@RequestMapping(value = "/list/{brandid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getProductListByBrand(@PathVariable("brandid") Long brandid) {
		return productsService.findProductsByBrand(brandid);
	}

	@RequestMapping(value = "/list/category/{productcatgeoryid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getProductListByCategory(
			@PathVariable("productcatgeoryid") Long productcatgeoryid) {
		return productsService.findProductsByCategory(productcatgeoryid);
	}

	@RequestMapping(value = "/list/{brandid}/{productcatgeoryid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getCategoryBrands(@PathVariable("brandid") Long brandid,
			@PathVariable("productcatgeoryid") Long productcatgeoryid) {
		return productsService.getCategoryBrands(brandid, productcatgeoryid);
	}

}
