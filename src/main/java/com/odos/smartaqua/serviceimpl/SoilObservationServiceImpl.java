package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.SoilObservationDTO;
import com.odos.smartaqua.entities.SoilObservation;
import com.odos.smartaqua.repository.SoilObservationRepository;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.repository.UserRepository;
import com.odos.smartaqua.service.SoilObservationService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class SoilObservationServiceImpl implements SoilObservationService {

	@Autowired
	private SoilObservationRepository soilObservationRepository;

	@Autowired
	private TankRepository tankRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * -----------------SAVE SOIL OBSERVATION -------------
	 */
	public ResponseEntity<ResponseDTO> saveSoilObservation(SoilObservationDTO soilObservationDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			SoilObservation soilObservation = new SoilObservation();
			BeanUtils.copyProperties(soilObservationDTO, soilObservation);
			soilObservation.setTank(tankRepository.findById(soilObservationDTO.getTankid()).get());
			soilObservation.setUser(userRepository.findById(soilObservationDTO.getUserid()).get());
			soilObservationRepository.save(soilObservation);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------GET SOIL OBSERVATION LIST BY TANKID -------------
	 */
	public ResponseEntity<ResponseDTO> findSoilreportsByTankId(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<SoilObservationDTO> soilObservationDTOsList = new ArrayList<>();
			List<SoilObservation> soilObservationList = soilObservationRepository.findSoilObservationList(id);
			for (int i = 0; i < soilObservationList.size(); i++) {
				SoilObservation soilObservation = (SoilObservation) soilObservationList.get(i);
				SoilObservationDTO soilObservationDTO = new SoilObservationDTO(soilObservation.getSoilobservationid(),
						soilObservation.getTank().getTankid(), soilObservation.getUser().getUserid(),
						soilObservation.getCulturecode(), soilObservation.getCultureloc(),
						soilObservation.getObsvdate(), soilObservation.getSoiltype(), soilObservation.getPhvalue(),
						soilObservation.getSalinity(), soilObservation.getTexture(), soilObservation.getSand(),
						soilObservation.getSlit(), soilObservation.getClay(), soilObservation.getOrganiccarbon(),
						soilObservation.getOrganicmatter(), soilObservation.getNitrogen(),
						soilObservation.getPhosphorous(), soilObservation.getPottasium(), soilObservation.getSulphur(),
						soilObservation.getCa(), soilObservation.getMg(), soilObservation.getIron(),
						soilObservation.getZinc(), soilObservation.getAmmonia(), soilObservation.getMicrobiology(),
						soilObservation.getComments(), "" + soilObservation.getCreateddate(),
						"" + soilObservation.getModifieddate(), soilObservation.getIsactive());
				soilObservationDTOsList.add(soilObservationDTO);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, soilObservationDTOsList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
