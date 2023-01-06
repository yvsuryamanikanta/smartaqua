package com.odos.smartaqua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odos.smartaqua.dto.ResponseDTO;
import com.odos.smartaqua.dto.RolesDTO;
import com.odos.smartaqua.service.RolesService;

@RequestMapping(value = "/api/roles")
@RestController
public class RolesController {

	@Autowired
	private RolesService rolesService;

	@GetMapping(value = "/list")
	public ResponseEntity<ResponseDTO> findRoles() {
		return rolesService.findRoles();
	}

	@PostMapping(value = "/save")
	public ResponseEntity<ResponseDTO> saveRole(@RequestBody RolesDTO rolesdto) {
		return rolesService.saveRole(rolesdto);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteRole(@PathVariable("id") Long id) {
		return rolesService.deleteRole(id);
	}

}
