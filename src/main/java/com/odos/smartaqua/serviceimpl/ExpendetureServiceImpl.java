package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ExpendetureDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.Expendeture;
import com.odos.smartaqua.repository.ExpendetureRepository;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.service.ExpendetureService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class ExpendetureServiceImpl implements ExpendetureService {

	@Autowired
	private ExpendetureRepository expendetureRepository;

	@Autowired
	private TankRepository tankRepository;

	/*
	 * -----------------SAVE EXPENDETURE -------------
	 */
	public ResponseEntity<ResponseDTO> saveExpends(ExpendetureDTO expendetureDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Expendeture expendeture = new Expendeture();
			BeanUtils.copyProperties(expendetureDTO, expendeture);
			expendeture.setTank(tankRepository.findById(expendetureDTO.getTankid()).get());
			expendetureRepository.save(expendeture);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------GET EXPENDETURE LIST BY TANKID -------------
	 */
	public ResponseEntity<ResponseDTO> findExpendsByTankId(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ExpendetureDTO> expendetureDTOList = new ArrayList<>();
			List<Expendeture> expendetureLits = expendetureRepository.findExpendsList(id);
			for (int i = 0; i < expendetureLits.size(); i++) {
				Expendeture expendeture = (Expendeture) expendetureLits.get(i);
				ExpendetureDTO expendetureDTO = new ExpendetureDTO(expendeture.getExpendsid(),
						expendeture.getTank().getTankid(), expendeture.getAmount(), expendeture.getExpendsdate(),
						expendeture.getReason(), "" + expendeture.getCreateddate(), "" + expendeture.getModifieddate());
				expendetureDTOList.add(expendetureDTO);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, expendetureDTOList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------GET EXPENDETURE LIST BY TANK and EXPENDETURE DATE
	 * -------------
	 */
	public ResponseEntity<ResponseDTO> findExpendsByTankIdAndDate(Long id, String expenddate) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ExpendetureDTO> expendetureDTOList = new ArrayList<>();
			List<Expendeture> expendetureList = expendetureRepository.findExpendetureListByDate(id, expenddate);
			for (int i = 0; i < expendetureList.size(); i++) {
				Expendeture expendeture = (Expendeture) expendetureList.get(i);
				ExpendetureDTO expendetureDTO = new ExpendetureDTO(expendeture.getExpendsid(),
						expendeture.getTank().getTankid(), expendeture.getAmount(), expendeture.getExpendsdate(),
						expendeture.getReason(), "" + expendeture.getCreateddate(), "" + expendeture.getModifieddate());
				expendetureDTOList.add(expendetureDTO);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, expendetureDTOList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
