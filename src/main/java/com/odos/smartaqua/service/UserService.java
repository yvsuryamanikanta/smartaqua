package com.odos.smartaqua.service;

import org.springframework.http.ResponseEntity;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.UserDTO;


public interface UserService {

	ResponseEntity<ResponseDTO> saveUser(UserDTO userdto);

	ResponseEntity<ResponseDTO> getFeedBoysList(Long roleid, String createdby);

	ResponseEntity<ResponseDTO> authenticateUser(UserDTO userdto);
	
	ResponseEntity<ResponseDTO> updatePassword(UserDTO userdto);

}
