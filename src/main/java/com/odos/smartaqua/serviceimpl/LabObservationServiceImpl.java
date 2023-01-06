package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.LabObservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.LabObservation;
import com.odos.smartaqua.repository.LabObservationRepository;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.repository.UserRepository;
import com.odos.smartaqua.service.LabObservationService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class LabObservationServiceImpl implements LabObservationService {

	@Autowired
	private LabObservationRepository labObservationRepository;
	
	@Autowired
	private TankRepository tankRepository;
	
	@Autowired
	private UserRepository userRepository;

	/*
	 * -----------------SAVE LAB OBSERVATION -------------
	 */
	public ResponseEntity<ResponseDTO> saveLabObservation(LabObservationDTO labObservationDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			LabObservation labObservation = new LabObservation();
			BeanUtils.copyProperties(labObservationDTO, labObservation);
			labObservation.setTank(tankRepository.findById(labObservationDTO.getTankid()).get());
			labObservation.setUser(userRepository.findById(labObservationDTO.getUserid()).get());
			labObservationRepository.save(labObservation);
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
	 * -----------------GET LAB OBSERVATION LIST BY TANKID -------------
	 */
	public ResponseEntity<ResponseDTO> findLabreportsByTankId(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<LabObservationDTO> labObservationDTOsList = new ArrayList<LabObservationDTO>();
			List<LabObservation> labObservationList = labObservationRepository.findLabObservationList(id);
			for (int i = 0; i < labObservationList.size(); i++) {
				LabObservation labObservation = (LabObservation) labObservationList.get(i);
				LabObservationDTO labObservationDTO = new LabObservationDTO(labObservation.getLabobservationid(),
						labObservation.getTank().getTankid(), labObservation.getUser().getUserid(),
						labObservation.getPhvalue(), labObservation.getSalinity(), labObservation.getCo3(),
						labObservation.getHco3(), labObservation.getCahardness(), labObservation.getMghardness(),
						labObservation.getCalcium(), labObservation.getMagnesium(), labObservation.getPotassium(),
						labObservation.getSodium(), labObservation.getIron(), labObservation.getIonizedammonia(),
						labObservation.getUnionizedammonia(), labObservation.getNitrate(),
						labObservation.getHydrogensulphide(), labObservation.getLabdo(), labObservation.getCo2(),
						labObservation.getGreenalgae(), labObservation.getDiatom(), labObservation.getBluegreenalgae(),
						labObservation.getDinoflegellates(), labObservation.getZooplankton(),
						labObservation.getDafloc(), labObservation.getVibriogreencolonies(),
						labObservation.getVibrioyellowcolonies(),labObservation.getLabobsvdate() ,"" + labObservation.getCreateddate(),
						"" + labObservation.getModifieddate());
				labObservationDTOsList.add(labObservationDTO);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, labObservationDTOsList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
