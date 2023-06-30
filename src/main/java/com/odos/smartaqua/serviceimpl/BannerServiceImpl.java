package com.odos.smartaqua.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.BannerDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.Banner;
import com.odos.smartaqua.repository.BannerRepository;
import com.odos.smartaqua.service.BannerService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerRepository bannerRepository;

	/*
	 * ---------------- Banner list--------------------
	 */
	public ResponseEntity<ResponseDTO> findBaners() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, bannerRepository.findAllBanners(),
					AquaConstants.EMPTY);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	
	/*
	 * ---------------- Save Banner--------------------
	 */
	public ResponseEntity<ResponseDTO> saveBanner(BannerDTO bannerDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		Banner banner = new Banner();
		BeanUtils.copyProperties(bannerDTO, banner);
		try {
			bannerRepository.save(banner);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.EMPTY);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	
	/*
	 * ---------------- Delete Banner--------------------
	 */
	public ResponseEntity<ResponseDTO> deleteBanner(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			bannerRepository.deleteById(id);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.deleted,
					AquaConstants.EMPTY);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}
}
