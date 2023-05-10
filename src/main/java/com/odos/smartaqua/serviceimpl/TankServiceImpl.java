package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.PreparationDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.StockingDTO;
import com.odos.smartaqua.dto.TankDTO;
import com.odos.smartaqua.dto.TankInfoDTO;
import com.odos.smartaqua.entities.Preparation;
import com.odos.smartaqua.entities.Stocking;
import com.odos.smartaqua.entities.Tank;
import com.odos.smartaqua.repository.PreparationRepository;
import com.odos.smartaqua.repository.StockingRepository;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.repository.UserRepository;
import com.odos.smartaqua.service.TankService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class TankServiceImpl implements TankService {

	@Autowired
	private TankRepository tankRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PreparationRepository preparationRepository;

	@Autowired
	private StockingRepository stockingRepository;

	/*
	 * ----------------SAVE TANK --------------------
	 */

	public ResponseEntity<ResponseDTO> save(TankDTO tankdto) {
		ResponseDTO responseDTO = new ResponseDTO();

		String tankname = tankRepository.findTankByName(tankdto.getTankname(), tankdto.getUserid().toString());
		if (tankname != null) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.alreadytank);
		} else {
			Tank tank = new Tank();
			BeanUtils.copyProperties(tankdto, tank);
			tank.setUser(userRepository.findById(tankdto.getUserid()).get());
			try {
				tankRepository.save(tank);
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.success,
						AquaConstants.success);
			} catch (Exception e) {
				responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
						AquaConstants.failed);
			}
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * ----------------FIND TANK BY USER ID--------------------
	 */
	public ResponseEntity<ResponseDTO> findTankByUser(String userid) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<TankDTO> tankdtoList = new ArrayList<TankDTO>();
			List<Tank> tankList = tankRepository.findTankByUser(userid);
			if (tankList != null) {
				for (int i = 0; i < tankList.size(); i++) {
					Tank tank = (Tank) tankList.get(i);
					TankDTO responseTank = new TankDTO();
					BeanUtils.copyProperties(tank, responseTank);
					responseTank.setUserid(tank.getUser().getUserid());
					tankdtoList.add(responseTank);
				}
			}

			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, tankdtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * ----------------TANK INFO BY TANK ID--------------------
	 */
	@Override
	public ResponseEntity<ResponseDTO> findTankInfo(String tankid) {

		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<Tank> tankList = tankRepository.findTankByTankId(tankid);
			Tank tank = (Tank) tankList.get(0);
			List<StockingDTO> stockingDtoList = new ArrayList<>();
			List<Stocking> stockingList = stockingRepository.getStocking(tankid);
			List<PreparationDTO> preparationDtoList = new ArrayList<>();
			List<Preparation> preparationList = preparationRepository.pondPreparation(tankid);
			if (stockingList != null) {
				stockingDtoList = new ArrayList<>();
				for (int i = 0; i < stockingList.size(); i++) {
					Stocking stocking = (Stocking) stockingList.get(i);
					StockingDTO stockingDTO = new StockingDTO();
					BeanUtils.copyProperties(stocking, stockingDTO);
					stockingDTO.setTankid(stocking.getTank().getTankid());
					stockingDtoList.add(stockingDTO);
				}
			}

			if (preparationList != null) {
				preparationDtoList = new ArrayList<>();
				for (int i = 0; i < preparationList.size(); i++) {
					Preparation preparation = (Preparation) preparationList.get(i);
					PreparationDTO preparationDTO = new PreparationDTO();
					BeanUtils.copyProperties(preparation, preparationDTO);
					preparationDTO.setTankid(preparation.getTank().getTankid());
					preparationDtoList.add(preparationDTO);
				}
			}
			TankInfoDTO tankInfoDTO = new TankInfoDTO(tankid, tank.getTankname(), tank.getTanklocation(),
					tank.getTankimage(), tank.getTankSize(), tank.getTankSizeType(), tank.getLatitude(),
					tank.getLongitude(), preparationDtoList,stockingDtoList);

			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, tankInfoDTO,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	};

}
