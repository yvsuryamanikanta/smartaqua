package com.odos.smartaqua.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.DeviceDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.Device;
import com.odos.smartaqua.repository.DeviceRepository;
import com.odos.smartaqua.service.DeviceService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceRepository devicerepository;

	/*
	 * ----------------SAVE DEVICE DETAILS --------------------
	 */

	public ResponseEntity<ResponseDTO> save(DeviceDTO deviceDto) {
		String fcmID = findByUniqueId(deviceDto.getUniqueID());
		ResponseDTO responseDTO = new ResponseDTO();
		if (fcmID != null) {
			if (fcmID.equalsIgnoreCase(deviceDto.getNotificationid())) {
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED,
						AquaConstants.updated, AquaConstants.EMPTY);
			} else {
				try {
					updateFcmID(deviceDto.getUniqueID(), deviceDto.getNotificationid());
					responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED,
							AquaConstants.updated, AquaConstants.EMPTY);
				} catch (Exception e) {
					responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
							"Failed", AquaConstants.failed);
				}
			}

		} else {
			Device device = new Device();
			BeanUtils.copyProperties(deviceDto, device);
			try {
				devicerepository.save(device);
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
						AquaConstants.EMPTY);
			} catch (Exception e) {
				responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
						AquaConstants.failed, AquaConstants.failed);
			}
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * ----------------GET DEVICE UNIQUE ID --------------------
	 */
	public String findByUniqueId(String id) {
		return devicerepository.findByuniqueID(id);
	}

	/*
	 * ----------------UPDATE NOTIFICATION ID --------------------
	 */
	public Integer updateFcmID(String uniqueid, String notificationid) {
		return devicerepository.updateByUniqueID(uniqueid, notificationid);
	}

}
