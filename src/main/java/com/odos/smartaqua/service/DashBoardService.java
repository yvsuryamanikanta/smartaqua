package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ResponseDTO;

public interface DashBoardService {

	ResponseEntity<ResponseDTO> findAllGroups(Long userid,Long cultureid,Long tankid);

}
