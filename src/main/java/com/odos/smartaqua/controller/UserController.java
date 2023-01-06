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

	@PostMapping(value = "/save")
	public ResponseEntity<ResponseDTO> saveUser(@RequestBody UserDTO userdto) {
		return userService.saveUser(userdto);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<ResponseDTO> authenticateUser(@RequestBody UserDTO userdto) {
		return userService.authenticateUser(userdto);
	}

}
