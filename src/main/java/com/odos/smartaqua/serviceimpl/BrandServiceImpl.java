package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.BrandDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.Brand;
import com.odos.smartaqua.repository.BrandRepository;
import com.odos.smartaqua.service.BrandService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	
	/*
	 * -----------------SAVE BRAND -------------
	 */
	public ResponseEntity<ResponseDTO> saveBrand(BrandDTO branddto) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Brand brand = new Brand();
			BeanUtils.copyProperties(branddto, brand);
			brandRepository.save(brand);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	
	/*
	 * -----------------GET BRANDS -------------
	 */
	public ResponseEntity<ResponseDTO> findBrands() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<BrandDTO> brandDtoList = new ArrayList<BrandDTO>();
			List<Brand> brandList = brandRepository.findAll();
			for (int i = 0; i < brandList.size(); i++) {
				Brand brand = (Brand) brandList.get(i);
				BrandDTO branddto = new BrandDTO(brand.getBrandid(), brand.getBrandname(), brand.getBrandcode(),
						brand.getCretaedby());
				brandDtoList.add(branddto);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, brandDtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
