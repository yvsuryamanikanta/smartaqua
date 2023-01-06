package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.QuantityCategoriesDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.QuantityCategories;
import com.odos.smartaqua.repository.QtyCategoryRepository;
import com.odos.smartaqua.service.QtyCategroyService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class QtyCategoryServiceImpl implements QtyCategroyService {

	@Autowired
	private QtyCategoryRepository qtyCategoryRepository;

	/*
	 * -----------------SAVE QUANTITY CATEGORY -------------
	 */

	public ResponseEntity<ResponseDTO> saveCategory(QuantityCategoriesDTO quantityCategoriesdto) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			QuantityCategories quantityCategories = new QuantityCategories();
			BeanUtils.copyProperties(quantityCategoriesdto, quantityCategories);
			qtyCategoryRepository.save(quantityCategories);
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
	 * -----------------QUANTITY CATEGORY LIST -------------
	 */
	public ResponseEntity<ResponseDTO> findCategories() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<QuantityCategoriesDTO> quantityCategoriesDtoList = new ArrayList<QuantityCategoriesDTO>();
			List<QuantityCategories> quantityCategoriesList = qtyCategoryRepository.findAll();
			for (int i = 0; i < quantityCategoriesList.size(); i++) {
				QuantityCategories quantitycategories = (QuantityCategories) quantityCategoriesList.get(i);
				QuantityCategoriesDTO quantityCategoriesdto = new QuantityCategoriesDTO(
						quantitycategories.getQuantitycategoryid(), quantitycategories.getQtycategory(),
						quantitycategories.getQtycategorycode(), quantitycategories.getCretaedby());
				quantityCategoriesDtoList.add(quantityCategoriesdto);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED,
					quantityCategoriesDtoList, AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
