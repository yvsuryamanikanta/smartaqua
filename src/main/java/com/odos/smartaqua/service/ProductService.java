package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ProductDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface ProductService {

	ResponseEntity<ResponseDTO> save(ProductDTO productsDTO);

	ResponseEntity<ResponseDTO> findAllProducts();

	ResponseEntity<ResponseDTO> findProductsByBrand(Long brandid);

	ResponseEntity<ResponseDTO> findProductsByCategory(Long productcatgeoryid);

	ResponseEntity<ResponseDTO> getCategoryBrands(Long brandid, Long productcatgeoryid);
}
