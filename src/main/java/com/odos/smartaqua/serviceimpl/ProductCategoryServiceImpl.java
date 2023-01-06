package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ProductCategoryDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.ProductCategory;
import com.odos.smartaqua.repository.ProductCategoryRepository;
import com.odos.smartaqua.service.ProductCategoryService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	/*
	 * -----------------SAVE PRODUCT CATEGORY -------------
	 */

	public ResponseEntity<ResponseDTO> saveCategory(ProductCategoryDTO productCategorydto) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			ProductCategory producttype = new ProductCategory();
			BeanUtils.copyProperties(productCategorydto, producttype);
			productCategoryRepository.save(producttype);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------PRODUCT CATEGORY LIST -------------
	 */

	public ResponseEntity<ResponseDTO> findCategories() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ProductCategoryDTO> productCategorydtolist = new ArrayList<ProductCategoryDTO>();
			List<ProductCategory> producttype = productCategoryRepository.findAll();

			for (int i = 0; i < producttype.size(); i++) {
				ProductCategory productCategory = (ProductCategory) producttype.get(i);
				ProductCategoryDTO productcategortdto = new ProductCategoryDTO(productCategory.getProductcatgeoryid(),
						productCategory.getName(), productCategory.getCode(), productCategory.getCretaedby());
				productCategorydtolist.add(productcategortdto);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, productCategorydtolist,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
