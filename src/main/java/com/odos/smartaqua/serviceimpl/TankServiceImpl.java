package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.TankDTO;
import com.odos.smartaqua.entities.Tank;
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

	/*
	 * ----------------SAVE TANK --------------------
	 */

	public ResponseEntity<ResponseDTO> save(TankDTO tankdto) {
		ResponseDTO responseDTO = new ResponseDTO();

		String tankname = tankRepository.findTankByName(tankdto.getTankname(),tankdto.getUserid().toString());
		if (tankname != null) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.alreadytank);
		} else {
			Tank tank = new Tank();
			BeanUtils.copyProperties(tankdto, tank);
			tank.setUser(userRepository.findById(tankdto.getUserid()).get());
			try {
				tankRepository.save(tank);
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED,
						AquaConstants.success, AquaConstants.success);
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

		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	};

}
