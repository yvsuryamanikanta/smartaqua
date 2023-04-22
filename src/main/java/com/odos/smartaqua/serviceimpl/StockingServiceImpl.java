package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.StockingDTO;
import com.odos.smartaqua.entities.Stocking;
import com.odos.smartaqua.repository.StockingRepository;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.service.StockingService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class StockingServiceImpl implements StockingService {

	@Autowired
	private StockingRepository stockingRepository;

	@Autowired
	private TankRepository tankRepository;

	/*
	 * ----------------SAVE STOCKING --------------------
	 */

	public ResponseEntity<ResponseDTO> save(StockingDTO stockingDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		Stocking stocking = new Stocking();
		BeanUtils.copyProperties(stockingDTO, stocking);
		stocking.setTank(tankRepository.findById(stockingDTO.getTankid()).get());
		try {
			stockingRepository.save(stocking);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.success,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * ----------------FIND STOCKING BY Tank ID--------------------
	 */
	@Override
	public ResponseEntity<ResponseDTO> getStocking(String tankId) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<StockingDTO> stockingDTOsList = new ArrayList<>();
			List<Stocking> stockingList = stockingRepository.getStocking(tankId);
			if (stockingList != null) {
				for (int i = 0; i < stockingList.size(); i++) {
					Stocking stocking = (Stocking) stockingList.get(i);
					StockingDTO stockingDTO = new StockingDTO();
					BeanUtils.copyProperties(stocking, stockingDTO);
					stockingDTO.setTankid(stocking.getTank().getTankid());
					stockingDTOsList.add(stockingDTO);
				}
			}

			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, stockingDTOsList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
