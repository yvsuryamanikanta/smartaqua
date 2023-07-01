package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ChecktrayObservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.ChecktrayObservation;
import com.odos.smartaqua.repository.ChecktrayObservationRepository;
import com.odos.smartaqua.repository.ChecktrayRepository;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.service.ChecktrayObservationService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class CheckrayObservationServiceImpl implements ChecktrayObservationService {

	@Autowired
	private ChecktrayObservationRepository checktrayObservationRepository;

	@Autowired
	private ChecktrayRepository checktrayRepository;

	@Autowired
	private TankRepository tankRepository;

	/*
	 * -----------------SAVE CHECKTRAY OBSERVATION -------------
	 */
	public ResponseEntity<ResponseDTO> saveChecktrayObservation(ChecktrayObservationDTO checktrayObservationDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			ChecktrayObservation checktrayObservation = new ChecktrayObservation();
			BeanUtils.copyProperties(checktrayObservationDTO, checktrayObservation);
			checktrayObservation
					.setChecktray(checktrayRepository.findById(checktrayObservationDTO.getChecktrayid()).get());
			checktrayObservation.setTank(tankRepository.findById(checktrayObservationDTO.getTankid()).get());
			checktrayObservationRepository.save(checktrayObservation);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------GET CHECKTRAY OBSERVATION LIST BY CHECKTRAYID -------------
	 */
	public ResponseEntity<ResponseDTO> findChecktrayByChecktrayId(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ChecktrayObservationDTO> checktrayDtoList = new ArrayList<ChecktrayObservationDTO>();
			List<ChecktrayObservation> checktrayObservationList = checktrayObservationRepository
					.findChecktrayObservationList(id);
			for (int i = 0; i < checktrayObservationList.size(); i++) {
				ChecktrayObservation checktrayObservation = (ChecktrayObservation) checktrayObservationList.get(i);
				ChecktrayObservationDTO checktrayObservationDTO = new ChecktrayObservationDTO(
						checktrayObservation.getChecktrayobsvid(), checktrayObservation.getChecktray().getChecktrayid(),
						checktrayObservation.getTank().getTankid(), checktrayObservation.getChecktray().getChecktrayname(),checktrayObservation.getFeedstatus(),
						checktrayObservation.getWastagecolor(), checktrayObservation.getRedmortality(),
						checktrayObservation.getRedmortalitycount(), checktrayObservation.getWhitemortality(),
						checktrayObservation.getWhitemortalitycount(), checktrayObservation.getPotaciumdefeciency(),
						checktrayObservation.getMagniciumdefeciency(), checktrayObservation.getCalciumdefeciency(),
						checktrayObservation.getVibrieostatus(), checktrayObservation.getCrampstatus(),
						checktrayObservation.getChecktrayobsvdate(), "" + checktrayObservation.getCreateddate(),
						"" + checktrayObservation.getModifieddate());
				checktrayDtoList.add(checktrayObservationDTO);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, checktrayDtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
