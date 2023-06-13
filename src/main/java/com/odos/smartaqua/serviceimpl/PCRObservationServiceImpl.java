package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.PCRObservationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.PcrObservation;
import com.odos.smartaqua.repository.PCRObservationRepository;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.repository.UserRepository;
import com.odos.smartaqua.service.PcrObservationService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class PCRObservationServiceImpl implements PcrObservationService {

	@Autowired
	private PCRObservationRepository pcrObservationRepository;

	@Autowired
	private TankRepository tankRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * -----------------SAVE PCR OBSERVATION -------------
	 */
	public ResponseEntity<ResponseDTO> savePcrObservation(PCRObservationDTO pcrObservationDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			PcrObservation pcrObservation = new PcrObservation();
			BeanUtils.copyProperties(pcrObservationDTO, pcrObservation);
			pcrObservation.setTank(tankRepository.findById(pcrObservationDTO.getTankid()).get());
			pcrObservation.setUser(userRepository.findById(pcrObservationDTO.getUserid()).get());
			pcrObservationRepository.save(pcrObservation);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------GET PCR OBSERVATION LIST BY TANKID -------------
	 */
	public ResponseEntity<ResponseDTO> findPCRreportsByTankId(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<PCRObservationDTO> pcrObservationDTOsList = new ArrayList<>();
			List<PcrObservation> pcrObservationList = pcrObservationRepository.findPCRObservationList(id);
			for (int i = 0; i < pcrObservationList.size(); i++) {
				PcrObservation pcrObservation = (PcrObservation) pcrObservationList.get(i);
				PCRObservationDTO pcrObservationDTO = new PCRObservationDTO(pcrObservation.getPcrobservationid(),
						pcrObservation.getTank().getTankid(), pcrObservation.getUser().getUserid(),
						pcrObservation.getCulturecode(), pcrObservation.getCultureloc(), pcrObservation.getObsvdate(),
						pcrObservation.getPhysicalActivity(), pcrObservation.getMeanBodyLeangth(),
						pcrObservation.getDorsalSpinesCount(), pcrObservation.getEstimatedPlAge(),
						pcrObservation.getCoefficientOfSizeVariation(), pcrObservation.getSampleSalinity(),
						pcrObservation.getSalinitySressSurvival(), pcrObservation.getFormalinSressSurvival(),
						pcrObservation.getGillDevStatus(), pcrObservation.getMuscleGutRation(),
						pcrObservation.getEctoparasiteattachments(), pcrObservation.getEndoParasite(),
						pcrObservation.getNecrosis(), pcrObservation.getStructuralDeformities(),
						pcrObservation.getHepathopancreasLipid(), pcrObservation.getMbvOcclusionBodies(),
						pcrObservation.getHypherTropiedNucleiInHp(), pcrObservation.getPcrResultWssv(),
						pcrObservation.getWssvCtValueSeviority(), pcrObservation.getPcrResultEhp(),
						pcrObservation.getEhpCtValueSeviority(), pcrObservation.getPcrResultIhhnv(),
						pcrObservation.getIhhnvCtValueSeviority(), "" + pcrObservation.getPcrResultEms(),
						pcrObservation.getEmsCtValueSeviority(), pcrObservation.getComments(),
						"" + pcrObservation.getCreateddate(), "" + pcrObservation.getModifieddate(),
						pcrObservation.getIsactive());
				pcrObservationDTOsList.add(pcrObservationDTO);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, pcrObservationDTOsList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
