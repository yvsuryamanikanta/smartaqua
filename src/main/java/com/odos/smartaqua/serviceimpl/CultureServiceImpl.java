package com.odos.smartaqua.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odos.smartaqua.dto.CultureDTO;
import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.entities.Culture;
import com.odos.smartaqua.repository.CultureRepository;
import com.odos.smartaqua.repository.TankRepository;
import com.odos.smartaqua.repository.UserRepository;
import com.odos.smartaqua.service.CultureService;
import com.odos.smartaqua.utils.AquaConstants;
import com.odos.smartaqua.utils.StatusCodes;

@Service
public class CultureServiceImpl implements CultureService {

	@Autowired
	private CultureRepository cultureRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TankRepository tankRepository;

	/*
	 * -----------------SAVE CULTURE -------------
	 */
	public ResponseEntity<ResponseDTO> save(CultureDTO culturedto) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Culture> cultureList = cultureRepository.findCultureByTank(culturedto.getUserid(), culturedto.getTankid());
		if (cultureList.size() != 0) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED,
					AquaConstants.alreadyculture, AquaConstants.alreadyculture);
		} else {
			String cultureNumber = cultureRepository.findCultureNumber(culturedto.getUserid(), culturedto.getTankid());
			int cultureCount;
			if(cultureNumber != null) {
				cultureCount = Integer.parseInt(cultureNumber)+1;
			}else {
				cultureCount =1;
			}
			Culture culture = new Culture();
			BeanUtils.copyProperties(culturedto, culture);
			culture.setUser(userRepository.findById(culturedto.getUserid()).get());
			culture.setTank(tankRepository.findById(culturedto.getTankid()).get());
			culture.setCulturenumber(cultureCount);
			culture.setTankname(tankRepository.findById(culturedto.getTankid()).get().getTankname());
			try {
				cultureRepository.save(culture);
				responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.saved,
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
	 * -----------------FIND CULTURE BY TANK ID-------------
	 */
	public ResponseEntity<ResponseDTO> findCultureByTank(Long userid, Long tankid) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<CultureDTO> culturedtoList = new ArrayList<CultureDTO>();
			List<Culture> cultureList = cultureRepository.findCultureByTank(userid, tankid);
			if (cultureList != null) {
				for (int i = 0; i < cultureList.size(); i++) {
					Culture culture = (Culture) cultureList.get(i);
					CultureDTO responseCulture = new CultureDTO();
					BeanUtils.copyProperties(culture, responseCulture);
					responseCulture.setUserid(culture.getUser().getUserid());
					responseCulture.setTankid(culture.getTank().getTankid());
					responseCulture.setTankname(culture.getTank().getTankname());
					culturedtoList.add(responseCulture);
				}
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, culturedtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}

		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------FIND ALL CULTURES -------------
	 */

	public ResponseEntity<ResponseDTO> findAllCultures(Long userid) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<CultureDTO> culturedtoList = new ArrayList<CultureDTO>();
			List<Culture> cultureList = cultureRepository.findAllCultures(userid);
			if (cultureList != null) {
				for (int i = 0; i < cultureList.size(); i++) {
					Culture culture = (Culture) cultureList.get(i);
					CultureDTO responseCulture = new CultureDTO();
					BeanUtils.copyProperties(culture, responseCulture);
					responseCulture.setUserid(culture.getUser().getUserid());
					responseCulture.setTankid(culture.getTank().getTankid());
					responseCulture.setTankname(culture.getTank().getTankname());
					culturedtoList.add(responseCulture);
				}
			}
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, culturedtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed);
		}

		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------FIND ALL CULTURES BY WHO HAVE ACCESS-------------
	 */
	public ResponseEntity<ResponseDTO> findCultureByAccess(String cultureaccess) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<CultureDTO> culturedtoList = new ArrayList<CultureDTO>();
			List<Culture> cultureList = cultureRepository.findCultureByAccess(cultureaccess);

			if (cultureList != null) {
				for (int i = 0; i < cultureList.size(); i++) {
					Culture culture = (Culture) cultureList.get(i);
					CultureDTO responseCulture = new CultureDTO();
					BeanUtils.copyProperties(culture, responseCulture);
					responseCulture.setUserid(culture.getUser().getUserid());
					responseCulture.setTankid(culture.getTank().getTankid());
					responseCulture.setTankname(culture.getTank().getTankname());
					culturedtoList.add(responseCulture);
				}
			}

			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, culturedtoList,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed + e);
		}

		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

	/*
	 * -----------------GIVE ACCESS TO CULTURES -------------
	 */

	public ResponseEntity<ResponseDTO> updateCultureAccess(Long userid, Long cultureid, String cultureaccess) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			cultureRepository.updateByCultureID(userid, cultureid, cultureaccess);
			responseDTO = new ResponseDTO(AquaConstants.success, StatusCodes.CREATED, AquaConstants.updated,
					AquaConstants.success);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(AquaConstants.failed, StatusCodes.CREATED, AquaConstants.failed,
					AquaConstants.failed + e);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,
				HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode())));
	}

}
