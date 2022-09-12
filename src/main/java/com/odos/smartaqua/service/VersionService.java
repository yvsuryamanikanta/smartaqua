package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.VersionDTO;


public interface VersionService {

	ResponseEntity<ResponseDTO> saveVersion(VersionDTO versiondto);
	ResponseEntity<ResponseDTO> findByIsActive(boolean value);
	
}
