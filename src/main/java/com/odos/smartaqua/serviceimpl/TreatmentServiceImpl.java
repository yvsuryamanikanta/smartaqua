package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.TreatmentsDTO;
import com.odos.smartaqua.entities.Treatments;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.repository.TreatmentRepository;
import com.odos.smartaqua.service.TreatmentService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class TreatmentServiceImpl implements TreatmentService {

	@Autowired
	private TreatmentRepository treatmentRepository;

	@Autowired
	private TankRepository tankRepository;

	/*
	 * -----------------SAVE GROWTH -------------
	 */
	public ResponseEntity<ResponseDTO> saveTreatment(TreatmentsDTO treatmentsDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Treatments treatments = new Treatments();
			BeanUtils.copyProperties(treatmentsDTO, treatments);
			treatments.setTank(tankRepository.findById(treatmentsDTO.getTankid()).get());
			treatmentRepository.save(treatments);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------GET GROWTH LIST BY TANKID -------------
	 */
	public ResponseEntity<ResponseDTO> findTreatmentByTankId(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<TreatmentsDTO> treatmentsDTOsList = new ArrayList<>();
			List<Treatments> treatmentsList = treatmentRepository.findTreatmentsListByTankId(id);
			for (int i = 0; i < treatmentsList.size(); i++) {
				Treatments treatments = (Treatments) treatmentsList.get(i);
				TreatmentsDTO treatmentsDTO = new TreatmentsDTO(treatments.getTreatmentsid(),
						treatments.getTank().getTankid(), treatments.getDecease(), treatments.getSolution1(),
						treatments.getSolution2(), treatments.getSolution3(), treatments.getTraetmentdate(),
						"" + treatments.getCreateddate(), "" + treatments.getModifieddate());
				treatmentsDTOsList.add(treatmentsDTO);
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, treatmentsDTOsList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR, "Failed",
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
