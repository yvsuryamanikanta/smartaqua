package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.UserDTO;
import com.odos.smartaqua.service.UserService;

@RequestMapping(value = "/api/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/list/{roleid}/{createdby}")
	public ResponseEntity<ResponseDTO> getUsers(@PathVariable("roleid") Long roleid,
			@PathVariable("createdby") String createdby) {
		return userService.getFeedBoysList(roleid, createdby);
	}
	
	@GetMapping(value = "/list/{userid}")
	public ResponseEntity<ResponseDTO> getUsersById(@PathVariable("userid") Long userid) {
		return userService.getUserDetails(userid);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<ResponseDTO> saveUser(@RequestBody UserDTO userdto) {
		return userService.saveUser(userdto);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<ResponseDTO> authenticateUser(@RequestBody UserDTO userdto) {
		return userService.authenticateUser(userdto);
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO userdto) {
		return userService.updateUser(userdto);
	}
	
	@PostMapping(value = "/updatePassword")
	public ResponseEntity<ResponseDTO> updatePassword(@RequestBody UserDTO userdto) {
		return userService.updatePassword(userdto);
	}


}
