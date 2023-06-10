package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.GrowthObservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.GrowthObservation;
import com.odos.smartaqua.repository.GrowthRepository;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.service.GrowthObservationService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class GrowthObservationServiceImpl implements GrowthObservationService {

	@Autowired
	private GrowthRepository growthRepository;

	@Autowired
	private TankRepository tankRepository;

	/*
	 * -----------------SAVE GROWTH -------------
	 */
	public ResponseEntity<ResponseDTO> saveGrowthObservation(GrowthObservationDTO growthObservationDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			GrowthObservation growthObservation = new GrowthObservation();
			BeanUtils.copyProperties(growthObservationDTO, growthObservation);
			growthObservation.setTank(tankRepository.findById(growthObservationDTO.getTankid()).get());
			growthRepository.save(growthObservation);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------GET GROWTH LIST BY TANKID -------------
	 */
	public ResponseEntity<ResponseDTO> findGrowthByTankId(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<GrowthObservationDTO> growthObservationDTOsList = new ArrayList<>();
			List<GrowthObservation> growthObservationList = growthRepository.findGrowthList(id);
			for (int i = 0; i < growthObservationList.size(); i++) {
				GrowthObservation growthObservation = (GrowthObservation) growthObservationList.get(i);
				GrowthObservationDTO growthObservationDTO = new GrowthObservationDTO(
						growthObservation.getGrowthobsvid(), growthObservation.getTank().getTankid(),
						growthObservation.getCount(), growthObservation.getGrowthobservationdate(),
						"" + growthObservation.getCreateddate(), "" + growthObservation.getModifieddate());
				growthObservationDTOsList.add(growthObservationDTO);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, growthObservationDTOsList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	
	/*
	 * -----------------GET GROWTH LIST BY TANK and GROWTH DATE -------------
	 */
	public ResponseEntity<ResponseDTO> findGrowthByDateAndTank(Long id,String growthobservationdate) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<GrowthObservationDTO> growthObservationDTOsList = new ArrayList<>();
			List<GrowthObservation> growthObservationList = growthRepository.findGrowthListByDate(id,growthobservationdate);
			for (int i = 0; i < growthObservationList.size(); i++) {
				GrowthObservation growthObservation = (GrowthObservation) growthObservationList.get(i);
				GrowthObservationDTO growthObservationDTO = new GrowthObservationDTO(
						growthObservation.getGrowthobsvid(), growthObservation.getTank().getTankid(),
						growthObservation.getCount(), growthObservation.getGrowthobservationdate(),
						"" + growthObservation.getCreateddate(), "" + growthObservation.getModifieddate());
				growthObservationDTOsList.add(growthObservationDTO);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, growthObservationDTOsList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
