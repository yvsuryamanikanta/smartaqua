package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.PreparationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.Preparation;
import com.odos.smartaqua.repository.PreparationRepository;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.service.PreparationService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class PreparationServiceImpl implements PreparationService {

	@Autowired
	private PreparationRepository preparationRepository;

	@Autowired
	private TankRepository tankRepository;

	/*
	 * ----------------SAVE Preparation --------------------
	 */

	public ResponseEntity<ResponseDTO> save(PreparationDTO preparationDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		Preparation preparation = new Preparation();
		BeanUtils.copyProperties(preparationDTO, preparation);
		preparation.setTank(tankRepository.findById(preparationDTO.getTankid()).get());
		try {
			preparationRepository.save(preparation);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.success,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * ----------------FIND Preparation BY Tank ID--------------------
	 */
	@Override
	public ResponseEntity<ResponseDTO> pondPreparation(String tankId) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<PreparationDTO> preparationDTOsList = new ArrayList<>();
			List<Preparation> preparationsList = preparationRepository.pondPreparation(tankId);
			if (preparationsList != null) {
				for (int i = 0; i < preparationsList.size(); i++) {
					Preparation preparation = (Preparation) preparationsList.get(i);
					PreparationDTO preparationDTO = new PreparationDTO();
					BeanUtils.copyProperties(preparation, preparationDTO);
					preparationDTO.setTankid(preparation.getTank().getTankid());
					preparationDTOsList.add(preparationDTO);
				}
			}

			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, preparationDTOsList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}

		return new ResponseEntity<>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
