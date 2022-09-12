package com.odos.smartaqua.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.VersionDTO;
import com.odos.smartaqua.entities.Version;
import com.odos.smartaqua.repository.VersionRepository;
import com.odos.smartaqua.service.VersionService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class VersionServiceImpl implements VersionService {

	@Autowired
	private VersionRepository versionRepository;

	/*
	 * ----------------SAVE VERSION --------------------
	 */
	public ResponseEntity<ResponseDTO> saveVersion(VersionDTO versiondto) {
		ResponseDTO responseDTO = new ResponseDTO();
		Version version = new Version();
		BeanUtils.copyProperties(versiondto, version);
		try {
			versionRepository.save(version);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
					AquaConstants.saved);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR,
					AquaConstants.failed, AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * ----------------GET VERSION DATA --------------------
	 */
	public ResponseEntity<ResponseDTO> findByIsActive(boolean value) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Version version = versionRepository.findByIsActive(value);
			if (version != null) {
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, version,
						AquaConstants.success);
			} else {
				responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.BADREQUEST,
						AquaConstants.failed, AquaConstants.failed);
			}

		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.INTERNALERROR,
					AquaConstants.failed, AquaConstants.failed);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	};

}
