package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ProductDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.Product;
import com.odos.smartaqua.repository.BrandRepository;
import com.odos.smartaqua.repository.ProductCategoryRepository;
import com.odos.smartaqua.repository.ProductRepository;
import com.odos.smartaqua.repository.QtyCategoryRepository;
import com.odos.smartaqua.service.ProductService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class ProductsServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private QtyCategoryRepository quantityTypeRepository;

	@Autowired
	private ProductCategoryRepository productTypeRepository;

	@Autowired
	private BrandRepository brandRepository;


	/*
	 * -----------------SAVE PRODUCT -------------
	 */
	public ResponseEntity<ResponseDTO> save(ProductDTO productsDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		Product products = new Product();
		BeanUtils.copyProperties(productsDTO, products);
		products.setQuantitycategories(quantityTypeRepository.findById(productsDTO.getQuantitycategoryid()).get());
		products.setProductcategory(productTypeRepository.findById(productsDTO.getProductcatgeoryid()).get());
		products.setBrand(brandRepository.findById(productsDTO.getBrandid()).get());
		try {
			productRepository.save(products);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.saved);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					e.getMessage());
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}


	/*
	 * -----------------GET PRODUCT -------------
	 */
	public ResponseEntity<ResponseDTO> findAllProducts() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ProductDTO> productDtoList = new ArrayList<ProductDTO>();
			List<Product> productsList = productRepository.getProductist();
			for (int i = 0; i < productsList.size(); i++) {
				Product products = (Product) productsList.get(i);
				ProductDTO productsdto = new ProductDTO(products.getProductid(),
						products.getProductcategory().getProductcatgeoryid(),
						products.getQuantitycategories().getQuantitycategoryid(), products.getBrand().getBrandid(),
						products.getProductname(), products.getCostperqty(),products.getQuantitycategories().getQtycategory());
				productDtoList.add(productsdto);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, productDtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					AquaConstants.failed, AquaConstants.failed);
		}

		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}


	/*
	 * -----------------GET PRODUCT BY BRAND ID -------------
	 */
	public ResponseEntity<ResponseDTO> findProductsByBrand(Long brandid) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ProductDTO> productDtoList = new ArrayList<ProductDTO>();
			List<Product> productsList = productRepository.getProductsByBrand(brandid);
			for (int i = 0; i < productsList.size(); i++) {
				Product products = (Product) productsList.get(i);
				ProductDTO productsdto = new ProductDTO(products.getProductid(),
						products.getProductcategory().getProductcatgeoryid(),
						products.getQuantitycategories().getQuantitycategoryid(), products.getBrand().getBrandid(),
						products.getProductname(), products.getCostperqty(),products.getQuantitycategories().getQtycategory());
				productDtoList.add(productsdto);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, productDtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					e.getMessage());
		}

		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	

	/*
	 * -----------------FINF PRODUCT BY CATEGORY ID -------------
	 */
	public ResponseEntity<ResponseDTO> findProductsByCategory(Long productcatgeoryid) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ProductDTO> productDtoList = new ArrayList<ProductDTO>();
			List<Product> productsList = productRepository.getProductsByCategory(productcatgeoryid);
			for (int i = 0; i < productsList.size(); i++) {
				Product products = (Product) productsList.get(i);
				ProductDTO productsdto = new ProductDTO(products.getProductid(),
						products.getProductcategory().getProductcatgeoryid(),
						products.getQuantitycategories().getQuantitycategoryid(), products.getBrand().getBrandid(),
						products.getProductname(), products.getCostperqty(),products.getQuantitycategories().getQtycategory());
				productDtoList.add(productsdto);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, productDtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					e.getMessage());
		}

		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	

	/*
	 * -----------------GET PRODUCTS WITH BRAND AND CATEGORY -------------
	 */
	public ResponseEntity<ResponseDTO> getCategoryBrands(Long brandid, Long productcatgeoryid) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ProductDTO> productDtoList = new ArrayList<ProductDTO>();
			List<Product> productsList = productRepository.getCategoryBrands(brandid, productcatgeoryid);
			for (int i = 0; i < productsList.size(); i++) {
				Product products = (Product) productsList.get(i);
				ProductDTO productsdto = new ProductDTO(products.getProductid(),
						products.getProductcategory().getProductcatgeoryid(),
						products.getQuantitycategories().getQuantitycategoryid(), products.getBrand().getBrandid(),
						products.getProductname(), products.getCostperqty(),products.getQuantitycategories().getQtycategory());
				productDtoList.add(productsdto);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, productDtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					e.getMessage());
		}

		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
