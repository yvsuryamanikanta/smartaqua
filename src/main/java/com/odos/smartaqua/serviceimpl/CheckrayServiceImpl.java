package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ChecktrayDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.Checktray;
import com.odos.smartaqua.repository.ChecktrayRepository;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.service.ChecktrayService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class CheckrayServiceImpl implements ChecktrayService {

	@Autowired
	private ChecktrayRepository checktrayRepository;
	
	
	@Autowired
	private TankRepository tankRepository;
	
	/*
	 * -----------------SAVE CHECKTRAY -------------
	 */
	public ResponseEntity<ResponseDTO> saveChecktray(ChecktrayDTO checktrayDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Checktray checktray = new Checktray();
			BeanUtils.copyProperties(checktrayDTO, checktray);
			checktray.setTank(tankRepository.findById(checktrayDTO.getTankid()).get());
			checktrayRepository.save(checktray);
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
	 * -----------------GET CHECKTRAY LIST BY TANKID -------------
	 */
	public ResponseEntity<ResponseDTO> findChecktrayByTankId(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ChecktrayDTO> checktrayDtoList = new ArrayList<ChecktrayDTO>();
			List<Checktray> checktrayList = checktrayRepository.findChecktrayList(id);
			for (int i = 0; i < checktrayList.size(); i++) {
				Checktray checktray = (Checktray) checktrayList.get(i);
				ChecktrayDTO checktrayDTO = new ChecktrayDTO(checktray.getChecktrayid(), checktray.getTank().getTankid(), checktray.getChecktrayname(),
						""+checktray.getCreateddate(),""+checktray.getModifieddate());
				checktrayDtoList.add(checktrayDTO);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, checktrayDtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}



}
