package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ChatDTO;
import com.odos.smartaqua.dto.ResponseDTO;

public interface ChatService {

	ResponseEntity<ResponseDTO> save(ChatDTO chatDTO);

	ResponseEntity<ResponseDTO> findTankByUser(String userid);

}
