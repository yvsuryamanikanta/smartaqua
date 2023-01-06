package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.CultureDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface CultureService {

	ResponseEntity<ResponseDTO> save(CultureDTO culturedto);

	ResponseEntity<ResponseDTO> findCultureByTank(Long userid, Long tankid);

	ResponseEntity<ResponseDTO> findAllCultures(Long userid);

	ResponseEntity<ResponseDTO> findCultureByAccess(String cultureaccess);

	ResponseEntity<ResponseDTO> updateCultureAccess(Long userid, Long cultureid, String cultureaccess);

}
