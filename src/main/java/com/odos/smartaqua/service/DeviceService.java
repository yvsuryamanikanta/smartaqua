package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.DeviceDTO;
import com.odos.smartaqua.dto.ResponseDTO;



public interface DeviceService {

	ResponseEntity<ResponseDTO> save(DeviceDTO deviceDto);
	
	String findByUniqueId(String id);
	
	Integer updateFcmID(String uniqueid,String notificationid);
	
}
