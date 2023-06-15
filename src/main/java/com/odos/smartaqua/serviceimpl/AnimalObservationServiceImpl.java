package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.AnimalbservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.AnimalObservation;
import com.odos.smartaqua.repository.AnimalObservationRepository;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.repository.UserRepository;
import com.odos.smartaqua.service.AnimalObservationService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class AnimalObservationServiceImpl implements AnimalObservationService {

	@Autowired
	private AnimalObservationRepository animalObservationRepository;

	@Autowired
	private TankRepository tankRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * -----------------SAVE ANIMAL OBSERVATION -------------
	 */
	public ResponseEntity<ResponseDTO> saveAnimalObservation(AnimalbservationDTO animalbservationDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			AnimalObservation animalObservation = new AnimalObservation();
			BeanUtils.copyProperties(animalbservationDTO, animalObservation);
			animalObservation.setTank(tankRepository.findById(animalbservationDTO.getTankid()).get());
			animalObservation.setUser(userRepository.findById(animalbservationDTO.getUserid()).get());
			animalObservationRepository.save(animalObservation);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed+e);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------GET ANIMAL OBSERVATION LIST BY TANKID -------------
	 */
	public ResponseEntity<ResponseDTO> findAnimalreportsByTankId(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<AnimalbservationDTO> animalbservationDTOsList = new ArrayList<>();
			List<AnimalObservation> animalObservationsList = animalObservationRepository.findAnimalObservationList(id);
			for (int i = 0; i < animalObservationsList.size(); i++) {
				AnimalObservation animalObservation = (AnimalObservation) animalObservationsList.get(i);
				AnimalbservationDTO animalbservationDTO = new AnimalbservationDTO(
						animalObservation.getAnimalobservationid(), animalObservation.getTank().getTankid(),
						animalObservation.getUser().getUserid(), animalObservation.getCulturecode(),
						animalObservation.getCultureloc(), animalObservation.getObsvdate(),
						animalObservation.getGreencolony(), animalObservation.getYellowcolony(),
						animalObservation.getComments(), "" + animalObservation.getCreateddate(),
						"" + animalObservation.getModifieddate(), animalObservation.getIsactive());
				animalbservationDTOsList.add(animalbservationDTO);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, animalbservationDTOsList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
